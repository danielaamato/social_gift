package Persistance;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.socialsgift.SignUpActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import Bussiness.Gift;
import Bussiness.User;

public class ApiConnector {
    private static ApiConnector instance;
    private RequestQueue requestQueue;
    private String baseUrl = "https://balandrau.salle.url.edu/i3/socialgift/api/v1";
    private String accessToken;
    private String baseImage = "https://balandrau.salle.url.edu/i3/repositoryimages/photo/47601a8b-dc7f-41a2-a53b-19d2e8f54cd0.png";
    private Context context;

    public ApiConnector(Context context)
    {
        this.context = context.getApplicationContext();
        requestQueue = Volley.newRequestQueue(this.context);
    }

    public void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
    }

    public String getAccessToken()
    {
        return accessToken;
    }

    public static synchronized ApiConnector getInstance(Context context)
    {
        if (instance == null)
        {
            instance = new ApiConnector(context);
        }
        return instance;
    }

    public void login(String email, String password, final ApiResponseCallback callback)
    {
        String url = baseUrl + "/users/login";

        JSONObject jsonBody = new JSONObject();
        try
        {
            jsonBody.put("email", email);
            jsonBody.put("password", password);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        callback.onError(error);
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }

    public void register(String name, String lastName, String email, String password, final ApiResponseCallback callback)
    {
        String url = baseUrl + "/users";

        JSONObject jsonBody = new JSONObject();
        try
        {
            jsonBody.put("name", name);
            jsonBody.put("last_name", lastName);
            jsonBody.put("email", email);
            jsonBody.put("password", password);
            jsonBody.put("image", baseImage);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        // Si la respuesta contiene los campos esperados, asumimos que la operación fue exitosa
                        if (response.has("name") && response.has("last_name") && response.has("email") && response.has("password") && response.has("image"))
                        {
                            callback.onSuccess(response);
                        }
                        else
                        {
                            callback.onError(new VolleyError("Error: Unexpected response JSON"));
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        if (error.networkResponse != null)
                        {
                            int statusCode = error.networkResponse.statusCode;

                            // Puedes manejar diferentes códigos de error aquí, si es necesario
                            switch (statusCode)
                            {
                                case 400:
                                    callback.onError(new VolleyError("Error 400: Bad Request"));
                                    showToast("ERROR: Bad Request");
                                    break;
                                case 401:
                                    callback.onError(new VolleyError("Error 401: User not created"));
                                    showToast("ERROR: User not created");
                                    break;
                                case 406:
                                    callback.onError(new VolleyError("Error 406: Missing parameters"));
                                    showToast("ERROR: Missing parameters");
                                    break;
                                case 409:
                                    callback.onError(new VolleyError("Error 409: Email address already registered"));
                                    showToast("ERROR: Email address already registered");
                                    break;
                                case 500:
                                    callback.onError(new VolleyError("Error 500: User not created"));
                                    showToast("ERROR: User not created");
                                    break;
                                case 502:
                                    callback.onError(new VolleyError("Error 502: Internal Server Error"));
                                    showToast("ERROR: Internal Server Error");
                                    break;
                                default:
                                    callback.onError(new VolleyError("Error: Unknown status code"));
                                    showToast("ERROR: Unknown status code");
                                    break;
                            }
                        }
                        else
                        {
                            callback.onError(new VolleyError("Error: No network response"));
                            showToast("ERROR: No network response");
                        }
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }

    public void getGifts(final GiftsResponseCallback callback)
    {
        String url = baseUrl + "/gifts";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response)
                    {
                        final List<Gift> giftList = new ArrayList<>();
                        final CountDownLatch latch = new CountDownLatch(response.length());

                        for (int i = 0; i < response.length(); i++)
                        {
                            try
                            {
                                JSONObject item = response.getJSONObject(i);

                                final Integer id = item.has("id") && !item.isNull("id") ? item.getInt("id") : null;
                                final Integer wishlistId = item.has("wishlist_id") && !item.isNull("wishlist_id") ? item.getInt("wishlist_id") : null;
                                final String productUrl = item.has("product_url") && !item.isNull("product_url") ? item.getString("product_url") : null;
                                final Integer priority = item.has("priority") && !item.isNull("priority") ? item.getInt("priority") : null;
                                final Integer booked = item.has("booked") && !item.isNull("booked") ? item.getInt("booked") : null;

                                if (id != null && wishlistId != null && productUrl != null && !productUrl.trim().isEmpty() && priority != null && booked != null)
                                {
                                    Gift gift = new Gift(id, wishlistId, productUrl, priority, booked);
                                    giftList.add(gift);
                                }

                                latch.countDown();
                            }
                            catch (JSONException e)
                            {
                                e.printStackTrace();
                                latch.countDown();
                            }
                        }

                        try
                        {
                            latch.await();
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }

                        callback.onSuccess(giftList);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        callback.onError(error);
                    }
                }) {
            @Override
            public Map<String, String> getHeaders()
            {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + accessToken);
                return headers;
            }
        };

        requestQueue.add(jsonArrayRequest);
    }

    public void getUserByMail(String email, final ApiResponseCallback callback) {
        String url = baseUrl + "/users/search";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            int id = response.getInt("id");
                            String name = response.getString("name");
                            String lastName = response.getString("last_name");
                            String userEmail = response.getString("email");
                            String imageUrl = response.getString("image");

                            // Crear un objeto User con los datos obtenidos
                            User user = new User(id, name, lastName, userEmail, imageUrl);

                            // Llamar al método onSuccess y pasar el objeto User
                            //callback.onSuccess(user);
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                            callback.onError(new VolleyError("Error: Failed to parse user data"));
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error);
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }


    public interface ApiResponseCallback
    {
        void onSuccess(JSONObject response);
        void onError(VolleyError error);
    }

    public interface GiftsResponseCallback
    {
        void onSuccess(List<Gift> giftList);

        void onError(VolleyError error);
    }


    public void showToast(String message)
    {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}

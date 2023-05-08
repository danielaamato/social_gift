package com.example.socialgiftapp;
import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class ApiConnector {

    private static ApiConnector instance;
    private RequestQueue requestQueue;
    private String baseUrl = "https://balandrau.salle.url.edu/i3/socialgift/api/v1";
    private String accessToken;
    private String baseImage = "https://balandrau.salle.url.edu/i3/repositoryimages/photo/47601a8b-dc7f-41a2-a53b-19d2e8f54cd0.png";
    ApiConnector(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized ApiConnector getInstance(Context context) {
        if (instance == null) {
            instance = new ApiConnector(context);
        }
        return instance;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void login(String email, String password, final ApiResponseCallback callback) {
        String url = baseUrl + "/users/login"; // Asegúrate de reemplazar esto con el endpoint correcto de tu API para iniciar sesión

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("email", email);
            jsonBody.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccess(response);
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

    public void register(String name, String lastName, String email, String password, final ApiResponseCallback callback) {
        String url = baseUrl + "/users";

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("name", name);
            jsonBody.put("last_name", lastName);
            jsonBody.put("email", email);
            jsonBody.put("password", password);
            jsonBody.put("image", baseImage);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Si la respuesta contiene los campos esperados, asumimos que la operación fue exitosa
                        if (response.has("name") && response.has("last_name") && response.has("email") && response.has("password") && response.has("image")) {
                            callback.onSuccess(response);
                        } else {
                            callback.onError(new VolleyError("Error: Unexpected response JSON"));
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error.networkResponse != null) {
                            int statusCode = error.networkResponse.statusCode;

                            // Puedes manejar diferentes códigos de error aquí, si es necesario
                            switch (statusCode) {
                                case 400:
                                    callback.onError(new VolleyError("Error 400: Bad Request"));
                                    break;
                                case 406:
                                    callback.onError(new VolleyError("Error 406: Missing parameters"));
                                    break;
                                case 409:
                                    callback.onError(new VolleyError("Error 409: Email address already registered"));
                                    break;
                                case 500:
                                    callback.onError(new VolleyError("Error 500: User not created"));
                                    break;
                                case 502:
                                    callback.onError(new VolleyError("Error 502: Internal Server Error"));
                                    break;
                                default:
                                    callback.onError(new VolleyError("Error: Unknown status code"));
                                    break;
                            }
                        } else {
                            callback.onError(new VolleyError("Error: No network response"));
                        }
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }

    public interface ApiResponseCallback {
        void onSuccess(JSONObject response);
        void onError(VolleyError error);
    }
}

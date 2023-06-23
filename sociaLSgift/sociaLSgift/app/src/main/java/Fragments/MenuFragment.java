package Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.socialsgift.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Bussiness.Gift;
import Persistance.ApiConnector;

public class MenuFragment extends Fragment
{

    private LinearLayout giftsLinearLayout;
    private RequestQueue requestQueue;
    private ApiConnector apiConnector;
    private String accessToken;

    public static MenuFragment newInstance(String accessToken)
    {
        MenuFragment fragment = new MenuFragment();
        fragment.accessToken = accessToken;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        giftsLinearLayout = view.findViewById(R.id.giftsLinearLayout);
        requestQueue = Volley.newRequestQueue(getContext());

        apiConnector = new ApiConnector(getContext());
        apiConnector.setAccessToken(accessToken);

        apiConnector.getGifts(new ApiConnector.GiftsResponseCallback()
        {
            @Override
            public void onSuccess(List<Gift> giftList)
            {
                for (Gift gift : giftList)
                {
                    addGiftView(gift);
                }
            }

            @Override
            public void onError(VolleyError error)
            {
                error.printStackTrace();
                Toast.makeText(getContext(), "Sorry! We are having troubles with the API connection.", Toast.LENGTH_SHORT).show();
                addGenericGiftViews();
            }
        });
    }

    private void addGenericGiftViews()
    {
        int numGifts = 10;

        for (int i = 0; i < numGifts; i++)
        {
            View giftView = getLayoutInflater().inflate(R.layout.gift_item_layout, giftsLinearLayout, false);
            TextView giftNameTextView = giftView.findViewById(R.id.giftNameTextView);
            TextView giftDescriptionTextView = giftView.findViewById(R.id.giftDescriptionTextView);
            ImageView giftImageView = giftView.findViewById(R.id.giftImageView);

            giftNameTextView.setText("Generic Name");
            giftDescriptionTextView.setText("Generic Description");
            giftImageView.setImageResource(R.drawable.logo);

            int backgroundColor = (i % 2 == 0) ? ContextCompat.getColor(getContext(), R.color.light_gray) : ContextCompat.getColor(getContext(), R.color.dark_gray);
            giftView.setBackgroundColor(backgroundColor);

            giftsLinearLayout.addView(giftView);
        }
    }

    private void addGiftView(Gift gift)
    {
        View giftView = getLayoutInflater().inflate(R.layout.gift_item_layout, giftsLinearLayout, false);
        ImageView giftImageView = giftView.findViewById(R.id.giftImageView);

        try
        {
            String productPhotoUrl = gift.getUrl();
            if (isValidUrl(productPhotoUrl)) {
                // Realiza la solicitud solo si la URL tiene un formato válido
                Picasso.get()
                        .load(productPhotoUrl)
                        .error(R.drawable.logo) // Imagen de reserva en caso de error
                        .into(giftImageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                giftsLinearLayout.addView(giftView);
                            }

                            @Override
                            public void onError(Exception e) {
                                giftsLinearLayout.addView(giftView);
                            }
                        });
            }
            else
            {
                // Si la URL no tiene un formato válido, muestra la imagen de reserva.
                giftImageView.setImageResource(R.drawable.logo);
                giftsLinearLayout.addView(giftView);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getContext(), "Problem with getting gifts", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidUrl(String urlString)
    {
        try
        {
            new URL(urlString);
            return true;
        }
        catch (MalformedURLException e)
        {
            return false;
        }
    }

}

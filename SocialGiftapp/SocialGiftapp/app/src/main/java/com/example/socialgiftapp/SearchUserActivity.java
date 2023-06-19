package com.example.socialgiftapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

public class SearchUserActivity  extends AppCompatActivity {
    private TextView emailTextView;
    private Button search;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_users);


        emailTextView =findViewById(R.id.name_input_search);
        search = findViewById(R.id.btnSearchUser);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the text from the TextInputEditText views and convert them to strings
                String email = emailTextView.getText().toString();
                if (!email.isEmpty()) {
                    ApiConnector apiConnector = ApiConnector.getInstance(SearchUserActivity.this);
                    //TODO api get user by email
                    /*  apiConnector.register(firstName, lastName, email, password, new ApiConnector.ApiResponseCallback() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        try {
                            //int id = response.getInt("id");
                            String name = response.getString("name");
                            String last_name = response.getString("last_name");
                            String email = response.getString("email");
                            String imageUrl = "https://balandrau.salle.url.edu/i3/repositoryimages/photo/47601a8b-dc7f-41a2-a53b-19d2e8f54cd0.png";

                            User user = new User(1, name, last_name, email, imageUrl);

                            Toast.makeText(SearchUserActivity.this,R.string.registered_message,Toast.LENGTH_SHORT).show();
                            //System.out.println("REGISTERED!");REGISTERED registered_message
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(VolleyError error) {
                        Toast.makeText(SearchUserActivity.this, R.string.message_api_error, Toast.LENGTH_LONG).show();
                    }
                });*/
                }
                else{
                    Toast.makeText(view.getContext(), R.string.message_email_empty, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}


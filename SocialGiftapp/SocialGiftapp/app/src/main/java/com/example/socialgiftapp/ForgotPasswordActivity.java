package com.example.socialgiftapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class ForgotPasswordActivity extends AppCompatActivity
{
    private TextInputEditText edtPass;
    private Button savePassword;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_forgot_password);

        edtPass = findViewById(R.id.edtPass);
        savePassword = findViewById(R.id.btnSavePass);

        savePassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String password = edtPass.getText().toString();
                ApiConnector apiConnector = ApiConnector.getInstance(ForgotPasswordActivity.this);
                finish();
                /*
                apiConnector.register(firstName, lastName, email, password, new ApiConnector.ApiResponseCallback() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        try {
                            //int id = response.getInt("id");
                            String name = response.getString("name");
                            String last_name = response.getString("last_name");
                            String email = response.getString("email");
                            String imageUrl = "https://balandrau.salle.url.edu/i3/repositoryimages/photo/47601a8b-dc7f-41a2-a53b-19d2e8f54cd0.png";

                            User user = new User(1, name, last_name, email, imageUrl);


                            System.out.println("REGISTERED!");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(VolleyError error) {
                        System.out.println("PROBLEM WITH THE USER!");
                        error.printStackTrace();
                    }
                });

                 */
            }
        });
    }
}

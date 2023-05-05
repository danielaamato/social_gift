package com.example.socialgiftapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.VolleyError;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {
    private TextInputEditText edtFirstName;
    private TextInputEditText edtLastName;
    private TextInputEditText edtSignUpEmail;
    private TextInputEditText edtSignUpPassword;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize the TextInputEditText variables
        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        edtSignUpEmail = findViewById(R.id.edtSignUpEmail);
        edtSignUpPassword = findViewById(R.id.edtSignUpPassword);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the text from the TextInputEditText views and convert them to strings
                String firstName = edtFirstName.getText().toString();
                String lastName = edtLastName.getText().toString();
                String email = edtSignUpEmail.getText().toString();
                String password = edtSignUpPassword.getText().toString();

                ApiConnector apiConnector = ApiConnector.getInstance(SignUpActivity.this);
                apiConnector.register(firstName, lastName, email, password, new ApiConnector.ApiResponseCallback() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        try {
                            int id = response.getInt("id");
                            String name = response.getString("name");
                            String last_name = response.getString("last_name");
                            String email = response.getString("email");
                            String imageUrl = response.getString("imageUrl");

                            User user = new User(id, name, last_name, email, imageUrl);


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
            }
        });
    }
}

package com.example.socialgiftapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView txtSignUp;
    private ApiConnector apiConnector;
    private Button btnSignIn;
    private Button btnForgotPass;
    private EditText edtEmail;
    private EditText edtPassword;
    private static final int SIGNUP = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiConnector = new ApiConnector(MainActivity.this);

        edtEmail = findViewById(R.id.edtSignInEmail);
        edtPassword = findViewById(R.id.edtSignInPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnForgotPass = findViewById(R.id.buttonForgot);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                apiConnector.login(email, password, new ApiConnector.ApiResponseCallback() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        try {
                            String accessToken = response.getString("accessToken");
                            // Establece el accessToken en la instancia de ApiConnector
                            apiConnector.setAccessToken(accessToken);

                            System.out.println("CONNECTED! ");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(VolleyError error) {
                        System.out.println("Error con la cuenta! Prueba de nuevo.");
                        error.printStackTrace();
                    }
                });
            }
        });

        txtSignUp = findViewById(R.id.txtSignUp);
        txtSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }
}

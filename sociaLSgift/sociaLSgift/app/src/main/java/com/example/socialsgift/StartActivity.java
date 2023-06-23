package com.example.socialsgift;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.socialsgift.R;

import org.json.JSONException;
import org.json.JSONObject;

import Persistance.ApiConnector;

public class StartActivity extends AppCompatActivity
{
    private TextView txtSignUp;
    private ApiConnector apiConnector;
    private Button btnSignIn;
    private Button btnForgotPass;
    private EditText edtEmail;
    private EditText edtPassword;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        apiConnector = new ApiConnector(StartActivity.this);

        edtEmail = findViewById(R.id.edtSignInEmail);
        edtPassword = findViewById(R.id.edtSignInPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnForgotPass = findViewById(R.id.buttonForgot);

        btnSignIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                apiConnector.login(email, password, new ApiConnector.ApiResponseCallback()
                {
                    @Override
                    public void onSuccess(JSONObject response)
                    {
                        try
                        {
                            String accessToken = response.getString("accessToken");

                            // Cambiar StartActivity.this, StartActivity.class a StartActivity.this, MainActivity.class
                            Intent intent = new Intent(StartActivity.this, MainActivity.class);

                            intent.putExtra("accessToken", accessToken); // Pasar el accessToken como dato extra
                            startActivity(intent);
                            finish(); // Finalizar la actividad actual para que no se pueda volver atrás a la pantalla de inicio de sesión

                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                            Toast.makeText(StartActivity.this, "ERROR: Wrong info format received from api.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(VolleyError error)
                    {
                        Toast.makeText(StartActivity.this, "ERROR: Please use an existing user", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });
            }
        });

        btnForgotPass.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(StartActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        txtSignUp = findViewById(R.id.txtSignUp);
        txtSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(StartActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }
}

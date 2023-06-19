package com.example.socialgiftapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class ProfileActivity extends AppCompatActivity
{
    private Button buttonEdit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_profile_activity);

        User user = new User(8, "dani", "amato", "dan@gmail.com", "https://balandrau.salle.url.edu/i3/repositoryimages/photo/47601a8b-dc7f-41a2-a53b-19d2e8f54cd0.png");

        TextView edtFirstName = findViewById(R.id.textView);
        TextView edtLastName = findViewById(R.id.textView2);
        TextView edtEmail = findViewById(R.id.textView3);

        edtFirstName.setText(user.getName());
        edtLastName.setText(user.getLast_name());
        edtEmail.setText(user.getEmail());
        buttonEdit = findViewById(R.id.buttonEdit);

        buttonEdit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}

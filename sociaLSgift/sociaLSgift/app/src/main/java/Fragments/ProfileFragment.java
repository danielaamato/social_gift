package Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.socialsgift.R;

import Bussiness.User;

public class ProfileFragment extends Fragment
{
    private Button buttonEdit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        User user = new User(8, "dani", "amato", "dan@gmail.com", "https://balandrau.salle.url.edu/i3/repositoryimages/photo/47601a8b-dc7f-41a2-a53b-19d2e8f54cd0.png");

        TextView edtFirstName = view.findViewById(R.id.textView);
        TextView edtLastName = view.findViewById(R.id.textView2);
        TextView edtEmail = view.findViewById(R.id.textView3);

        edtFirstName.setText(user.getName());
        edtLastName.setText(user.getLast_name());
        edtEmail.setText(user.getEmail());
        buttonEdit = view.findViewById(R.id.buttonEdit);

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Puedes adaptar esto para abrir otro fragmento o una actividad según tus necesidades
                //Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                //startActivity(intent);
            }
        });

        return view;
    }
}

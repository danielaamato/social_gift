package Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.socialsgift.MainActivity;
import com.example.socialsgift.R;
import com.example.socialsgift.StartActivity;

import Bussiness.User;

public class ProfileFragment extends Fragment
{
    private Button buttonEdit;
    private EditText edtFirstName;
    private EditText edtLastName;
    private EditText edtEmail;
    private ImageView imageView;
    private Button logOut;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        User user = new User(8, "dani", "amato", "dan@gmail.com", "https://balandrau.salle.url.edu/i3/repositoryimages/photo/47601a8b-dc7f-41a2-a53b-19d2e8f54cd0.png");

        edtFirstName = view.findViewById(R.id.textView);
        edtLastName = view.findViewById(R.id.textView2);
        edtEmail = view.findViewById(R.id.textView3);
        imageView = view.findViewById(R.id.imageView2);

        edtFirstName.setText(user.getName());
        edtLastName.setText(user.getLast_name());
        edtEmail.setText(user.getEmail());
        buttonEdit = view.findViewById(R.id.buttonEdit);
        logOut = view.findViewById(R.id.buttonLogOut);

        buttonEdit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String newFirstName = edtFirstName.getText().toString();
                String newLastName = edtLastName.getText().toString();
                String newEmail = edtEmail.getText().toString();

                // Actualizar los datos del objeto User
                user.setName(newFirstName);
                user.setLast_name(newLastName);
                user.setEmail(newEmail);

                // GUARDAR EN LA API CON PUT DEL USER

                // Actualizar los campos de texto con los nuevos valores
                edtFirstName.setText(user.getName());
                edtLastName.setText(user.getLast_name());
                edtEmail.setText(user.getEmail());
            }
        });

        logOut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Realizar acciones de cierre de sesión aquí, como limpiar datos de sesión o cerrar sesión en el backend

                Intent intent = new Intent(getActivity(), StartActivity.class);
                startActivity(intent);
                getActivity().finish(); // Opcionalmente, puedes finalizar la actividad actual para evitar volver atrás mediante el botón de retroceso
            }
        });

        return view;
    }
}

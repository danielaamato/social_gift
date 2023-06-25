package Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialsgift.FriendAdapter;
import com.example.socialsgift.FriendsRequest;
import com.example.socialsgift.R;
import com.example.socialsgift.StartActivity;

import java.util.ArrayList;
import java.util.List;

import Bussiness.User;

public class SocialFragment extends Fragment
{
    private RecyclerView recyclerViewFriends;
    private List<User> friendList;
    //private FriendAdapter friendAdapter;
    private SearchView searchViewFriends;
    private Button solicitud;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_social, container, false);

        solicitud = view.findViewById(R.id.btnFriendRequests);

        friendList = new ArrayList<>(); // Lista de amigos (puedes cargarla con tus datos)
        User user = new User(8, "dani", "amato", "dan@gmail.com", "https://balandrau.salle.url.edu/i3/repositoryimages/photo/47601a8b-dc7f-41a2-a53b-19d2e8f54cd0.png");
        friendList.add(user);

        recyclerViewFriends = view.findViewById(R.id.recyclerViewFriends);
        recyclerViewFriends.setLayoutManager(new LinearLayoutManager(getContext()));
        //friendAdapter = new FriendAdapter(friendList);
        //recyclerViewFriends.setAdapter(friendAdapter);

        searchViewFriends = view.findViewById(R.id.searchViewFriends);

        solicitud.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), FriendsRequest.class);
                startActivity(intent);
            }
        });

        searchViewFriends.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                //friendAdapter.getFilter().filter(newText); // Filtrar la lista de amigos según el texto ingresado en la búsqueda
                return true;
            }
        });

        return view;
    }
}


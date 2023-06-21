package com.example.socialgiftapp.FriendList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.socialgiftapp.Entities.Friend;
import com.example.socialgiftapp.R;

import java.util.ArrayList;

public class RequestFriendsActivity extends AppCompatActivity {

        private RecyclerView myFriendsRequestsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_friends);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        myFriendsRequestsRecyclerView=findViewById(R.id.myFriendsRequestsRecyclerView);
        myFriendsRequestsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        ArrayList<Friend> listFR=new ArrayList<>();
        listFR.add(new Friend(0,"paco","https://pluspng.com/img-png/user-png-icon-young-user-icon-2400.png"));
        listFR.add(new Friend(1,"paco2","https://pluspng.com/img-png/user-png-icon-young-user-icon-2400.png"));
        listFR.add(new Friend(2,"paco3","https://pluspng.com/img-png/user-png-icon-young-user-icon-2400.png"));
        AdapterRecyclerRequestFriend adapterRecyclerRequestFriend=new AdapterRecyclerRequestFriend(listFR);
        myFriendsRequestsRecyclerView.setAdapter(adapterRecyclerRequestFriend);
    }
}
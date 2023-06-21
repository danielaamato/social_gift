package com.example.socialgiftapp.FriendList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.socialgiftapp.Entities.Friend;
import com.example.socialgiftapp.R;

import java.util.List;

public class AdapterRecyclerRequestFriend extends RecyclerView.Adapter<AdapterRecyclerRequestFriend.ViewHolder> {
    private List<Friend> listFR;

    public AdapterRecyclerRequestFriend(List<Friend> listFR) {
        this.listFR=listFR;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_see_friends,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Friend friend=listFR.get(position);
        holder.name_user_view.setText(friend.getName());
        holder.buttonRowChatFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "has pulsado el: " + friend.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        Glide.with(holder.itemView.getContext()).load(friend.getImage()).into(holder.image_friend_see_friend);//Para la imagen

    }

    @Override
    public int getItemCount() {
        return listFR.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private Button buttonRowChatFriend;
        private TextView name_user_view;
        private ImageView image_friend_see_friend;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            buttonRowChatFriend=itemView.findViewById(R.id.buttonRowChatFriend);
            name_user_view=itemView.findViewById(R.id.name_user_view);
            image_friend_see_friend=itemView.findViewById(R.id.image_friend_see_friend);
        }
    }
}

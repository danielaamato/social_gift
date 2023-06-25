package com.example.socialsgift;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Bussiness.User;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> implements Filterable
{
    private List<User> friendList;
    private List<User> friendListFiltered;

    public FriendAdapter(List<User> friendList)
    {
        this.friendList = friendList;
        this.friendListFiltered = friendList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_social, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        User friend = friendListFiltered.get(position);
        holder.txtFriendName.setText(friend.getName());
        // Configura cualquier otro campo necesario
    }

    @Override
    public int getItemCount() {
        return friendListFiltered.size();
    }

    @Override
    public Filter getFilter()
    {
        return new Filter()
        {
            @Override
            protected FilterResults performFiltering(CharSequence constraint)
            {
                String filterPattern = constraint.toString().toLowerCase().trim();

                List<User> filteredList = new ArrayList<>();

                if (filterPattern.isEmpty())
                {
                    filteredList = friendList;
                }
                else
                {
                    for (User friend : friendList)
                    {
                        if (friend.getName().toLowerCase().contains(filterPattern))
                        {
                            filteredList.add(friend);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results)
            {
                friendListFiltered = (List<User>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtFriendName;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            txtFriendName = itemView.findViewById(R.id.txtFriendName);
        }
    }
}


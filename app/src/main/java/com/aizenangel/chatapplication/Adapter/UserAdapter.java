package com.aizenangel.chatapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aizenangel.chatapplication.MessageActivity;
import com.aizenangel.chatapplication.Model.User;
import com.aizenangel.chatapplication.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context myContext;
    private List<User> myUsers;

    public UserAdapter(Context myContext, List<User> myUsers) {
        this.myContext = myContext;
        this.myUsers = myUsers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(myContext).inflate(R.layout.user_item, parent, false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       final User user = myUsers.get(position);

       holder.username.setText(user.getUsername());
       if(user.getImageURL().equals("default")){
           holder.profile_image.setImageResource(R.mipmap.ic_launcher);
       }else{
           Glide.with(myContext).load(user.getImageURL()).into(holder.profile_image);
       }

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(myContext, MessageActivity.class);
               intent.putExtra("userid", user.getId());
               myContext.startActivity(intent);
           }
       });

    }

    @Override
    public int getItemCount() {
        return myUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView username;
        public ImageView profile_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            profile_image = itemView.findViewById(R.id.profile_image);
        }
    }

}

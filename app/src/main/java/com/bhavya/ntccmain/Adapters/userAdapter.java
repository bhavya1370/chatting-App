package com.bhavya.ntccmain.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bhavya.ntccmain.R;
import com.bhavya.ntccmain.modles.User;
import com.bhavya.ntccmain.texting;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class userAdapter extends RecyclerView.Adapter<userAdapter.viewHolder>{


    ArrayList<User> list;
    Context context;

    public userAdapter(ArrayList<User> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.show_user_sample,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        User users = list.get(position);
        Picasso.get().load(users.getPfPic()).placeholder(R.drawable.ic_pawprint).into(holder.imag);
        holder.userName.setText(users.getUserName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, texting.class);
                intent.putExtra("userId", users.getuId());
                intent.putExtra("ProfilePic", users.getPfPic());
                intent.putExtra("userName", users.getUserName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView imag;
        TextView userName, lastMesg;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            imag = itemView.findViewById(R.id.profile_image);
            userName = itemView.findViewById(R.id.Username);
            lastMesg = itemView.findViewById(R.id.lastMessage);
        }
    }
}

package com.bhavya.ntccmain.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bhavya.ntccmain.Adapters.userAdapter;
import com.bhavya.ntccmain.databinding.FragmentChatsBinding;
import com.bhavya.ntccmain.modles.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class chats extends Fragment {

    public chats() {
        // Required empty public constructor
    }

    FragmentChatsBinding binding;
    ArrayList<User> list = new ArrayList<>();
    FirebaseDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChatsBinding.inflate(inflater, container, false);

        userAdapter adapter = new userAdapter(list, getContext());
        binding.chatRCview.setAdapter(adapter);

        database = FirebaseDatabase.getInstance();

        LinearLayoutManager LyManager = new LinearLayoutManager(getContext());
        binding.chatRCview.setLayoutManager(LyManager);

        database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot dataSnap : snapshot.getChildren()){
                    User User = dataSnap.getValue(com.bhavya.ntccmain.modles.User.class);
                    User.setuId(dataSnap.getKey());
                    list.add(User);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return binding.getRoot();
    }
}
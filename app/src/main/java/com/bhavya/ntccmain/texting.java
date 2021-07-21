package com.bhavya.ntccmain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bhavya.ntccmain.Adapters.textingAdapter;
import com.bhavya.ntccmain.databinding.ActivityTextingBinding;
import com.bhavya.ntccmain.modles.msgModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class texting extends AppCompatActivity {

    ActivityTextingBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTextingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        final String senderID = auth.getUid();
        String reciverID = getIntent().getStringExtra("userID");
        String userName = getIntent().getStringExtra("userName");
        String pfPic = getIntent().getStringExtra("profilePicture");

        binding.profileName.setText(userName);
        Picasso.get().load(pfPic).placeholder(R.drawable.ic_pawprint).into(binding.profileImage);

        binding.backArrowIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(texting.this, MainActivity.class);
                startActivity(intent);
            }
        });

        final ArrayList<msgModel> mgM = new ArrayList<>();

        final textingAdapter textAdp = new textingAdapter(mgM, this);
        binding.chatRCview.setAdapter(textAdp);

        LinearLayoutManager llayout = new LinearLayoutManager( this);
        binding.chatRCview.setLayoutManager(llayout);

        final String senderRoom = senderID + reciverID;
        final String reciverRoom = reciverID + senderID;
        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = binding.etSendMsg.getText().toString();
                final msgModel model = new msgModel(senderID, msg);
                model.setTimestamp(new Date().getTime());
                binding.etSendMsg.setText("");

                database.getReference().child("chats")
                        .child(senderRoom)
                         .push().setValue(model)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                database.getReference().child("chats")
                                        .child(reciverRoom)
                                        .push().setValue(model)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                    }
                                });
                            }
                        });

            }
        });

    }
}
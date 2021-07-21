package com.bhavya.ntccmain.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bhavya.ntccmain.R;
import com.bhavya.ntccmain.modles.msgModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class textingAdapter extends RecyclerView.Adapter{

    ArrayList<msgModel> msgModel;
    Context context;

    int SVT=1;
    int RVT=2;

    public textingAdapter(ArrayList<com.bhavya.ntccmain.modles.msgModel> msgModel, Context context) {
        this.msgModel = msgModel;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == SVT){
            View view = LayoutInflater.from(context).inflate(R.layout.sample_sender_msg, parent, false);
            return new senderViewHolder(view);
        }
       else{
            View view = LayoutInflater.from(context).inflate(R.layout.sample_reciver_msg, parent, false);
            return new reciverViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(msgModel.get(position).getuID().equals(FirebaseAuth.getInstance().getUid())){
            return SVT;
        }
        else{
            return RVT;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        msgModel msM = msgModel.get(position);

        if(holder.getClass() == senderViewHolder.class){
            ((senderViewHolder)holder).senderMsg.setText(msM.getMsg());
        }
        else{
            ((reciverViewHolder)holder).reciverMsg.setText(msM.getMsg());
        }
    }

    @Override
    public int getItemCount() {
        return msgModel.size();
    }

    public class reciverViewHolder extends RecyclerView.ViewHolder {

        TextView reciverMsg;
        TextView recMsgTime;

        public reciverViewHolder(@NonNull View itemView) {
            super(itemView);

            reciverMsg = itemView.findViewById(R.id.reciverText);
            recMsgTime = itemView.findViewById(R.id.recMsgTime);
        }
    }

    public class senderViewHolder extends RecyclerView.ViewHolder {

        TextView senderMsg;
        TextView senMsgTime;

        public senderViewHolder(@NonNull View itemView) {
            super(itemView);

            senderMsg = itemView.findViewById(R.id.senderMsg);
            senMsgTime = itemView.findViewById(R.id.senMsgTime);
        }
    }

}

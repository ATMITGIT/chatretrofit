package com.example.diplomprogect.adapters;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diplomprogect.R;


import com.example.diplomprogect.models.Chat;
import com.example.diplomprogect.models.Message;

import java.util.List;


public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ChatViewHolder>{
    private Context mCtx;
    private List<Message> chatList;
private Chat nickname;
    public MessageAdapter(Context mCtx, List<Message> chatList,Chat nickname) {
        this.mCtx = mCtx;
        this.chatList = chatList;
        this.nickname=nickname;
    }

    @NonNull
    @Override
    public MessageAdapter.ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.chatitem, parent, false);

        return new MessageAdapter.ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ChatViewHolder holder, int position) {
        final Message chat = chatList.get(position);


if(nickname.getId().equals(chat.getReciver())) {
    holder.t1.setText("вы");
}
else
{
    holder.t1.setText(nickname.getNickname());
}
        holder.t2.setText(chat.getCreatedAt());
String text=chat.getMessage();

        if(!chat.getFiles().isEmpty()) {
        text+="\n"+chat.getFiles().get(0).getOriginalName()+" size "+chat.getFiles().get(0).getSize();

        }
        holder.t3.setText(text);
      /*  if(!chat.getFiles().isEmpty()) {
            TextView t  = new TextView(mCtx);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

           t.setText(chat.getFiles().get(0).getOriginalName()+" size "+chat.getFiles().get(0).getSize());
           t.setBackgroundColor(Color.GREEN);
           holder.l.addView(t,lp);

        }*/
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    class ChatViewHolder extends RecyclerView.ViewHolder{

        TextView t1, t2,t3;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);

            t1 = itemView.findViewById(R.id.textView3);
            t2 = itemView.findViewById(R.id.textView4);
            t3 = itemView.findViewById(R.id.textView5);


        }
    }
}

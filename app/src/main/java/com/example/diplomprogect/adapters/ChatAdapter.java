package com.example.diplomprogect.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diplomprogect.R;
import com.example.diplomprogect.activity.ListChatActivity;
import com.example.diplomprogect.models.Chat;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private Context mCtx;
    private List<Chat> chatList;

    public ChatAdapter(Context mCtx, List<Chat> chatList) {
        this.mCtx = mCtx;
        this.chatList = chatList;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_chats, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        final Chat chat = chatList.get(position);

        holder.textViewChatName.setText(chat.getNickname());
        holder.textViewChatLogin.setText(chat.getLogin());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent i=new Intent(mCtx, ListChatActivity.class);
              i.putExtra("id",String.valueOf(chat.getId()));
              mCtx.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    class ChatViewHolder extends RecyclerView.ViewHolder{

        TextView textViewChatName, textViewChatLogin;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewChatName = itemView.findViewById(R.id.textViewChatName);
            textViewChatLogin = itemView.findViewById(R.id.textViewChatLogin);
        }
    }

}

package com.example.diplomprogect.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diplomprogect.R;
import com.example.diplomprogect.activity.ChatListActivity;
import com.example.diplomprogect.activity.LoginActivity;
import com.example.diplomprogect.activity.MainActivity;
import com.example.diplomprogect.api.RetrofitClient;
import com.example.diplomprogect.fragments.UsersFragment;
import com.example.diplomprogect.models.ChResponse;
import com.example.diplomprogect.models.Data;
import com.example.diplomprogect.models.User;
import com.example.diplomprogect.storage.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_SHORT;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {

    private Context mCtx;
    private List<User> userList;

    public UsersAdapter(Context mCtx, List<User> userList) {
        this.mCtx = mCtx;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_users, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
   final User user = userList.get(position);

    holder.textViewName.setText(user.getNickname());
    holder.textViewLogin.setText(user.getLogin());
    holder.btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            final EditText input = new EditText(mCtx);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            input.setLayoutParams(lp);

          AlertDialog.Builder dialog = new AlertDialog.Builder(mCtx);
            dialog.setTitle( "Hello" )
                  .setTitle("собшения для "+user.getNickname())

                   .setView(input)

                    .setNegativeButton("Закрить", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setPositiveButton("Отправить", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                         Call<ChResponse> call = RetrofitClient
                                .getInstance().getApi().getResponse(String.valueOf(user.getId()),SharedPrefManager.getInstance().getToken(),input.getText().toString());

                            call.enqueue(new Callback<ChResponse>() {
                                @Override
                                public void onResponse(Call<ChResponse> call, Response<ChResponse> response) {
                                    ChResponse data = response.body();


                                   Toast.makeText(mCtx,"message is sended", LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<ChResponse> call, Throwable t) {
                                    Log.d("teatimes","fals5443e");
                                }



                            });
                        }
                    }).show();



        }
    });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder{

        TextView textViewName, textViewLogin;
        Button btn;


        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewLogin = itemView.findViewById(R.id.textViewLogin);
            btn=itemView.findViewById(R.id.messagebtn);

        }

    }
}

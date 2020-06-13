package com.example.diplomprogect.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diplomprogect.R;
import com.example.diplomprogect.adapters.ChatAdapter;
import com.example.diplomprogect.adapters.UsersAdapter;
import com.example.diplomprogect.api.RetrofitClient;
import com.example.diplomprogect.models.Chat;
import com.example.diplomprogect.models.ChatResponse;
import com.example.diplomprogect.storage.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private ChatAdapter adapter;
    private List<Chat> chatList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerViewChat);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        SharedPrefManager s=SharedPrefManager.getInstance(getContext());
        Toast.makeText(getContext(), s.getToken(),Toast.LENGTH_SHORT).show();
        Call<ChatResponse> call = RetrofitClient.getInstance().getApi().getChatData(s.getToken());

        call.enqueue(new Callback<ChatResponse>() {
            @Override
            public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
                chatList = response.body().getChatData();
                adapter = new ChatAdapter(getActivity(), chatList);
             recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<ChatResponse> call, Throwable t) {

            }
        });

    }
}

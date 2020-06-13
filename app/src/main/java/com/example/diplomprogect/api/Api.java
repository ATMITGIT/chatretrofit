package com.example.diplomprogect.api;

import com.example.diplomprogect.models.CallChat;
import com.example.diplomprogect.models.ChResponse;
import com.example.diplomprogect.models.ChatResponse;
import com.example.diplomprogect.models.Data;
import com.example.diplomprogect.models.UsersResponse;
import com.example.diplomprogect.storage.SharedPrefManager;


import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {


    @FormUrlEncoded
    @POST("reg")
    Call<ResponseBody> reg(
            @Field("login") String login,
            @Field("pass") String pass,
            @Field("nickname") String nickname,
            @Field("secret") String secret

    );

    @FormUrlEncoded
    @POST("login")
    Call<Data> userLogin(
            @Field("login") String login,
            @Field("pass") String pass
    );



    @GET("users")
    Call<SharedPrefManager>getToken(@Query("token") String token);

    @GET("users")
    Call<UsersResponse> getData(@Query("token") String token);

    @GET("chats")
    Call<ChatResponse> getChatData(@Query("token") String token);

@Multipart
    @POST("chats/{id}")
        Call<ChResponse>
        getResponse(@Path("id") String id,
       @Query("token") String token,

     @Part MultipartBody.Part documents,
                    @Part("message") String message
);
    @Multipart
    @POST("chats/{id}")
    Call<ChResponse>
    getResponse(@Path("id") String id,
                @Query("token") String token,


                @Part("message") String message
    );
    @GET("chats/{id}")
    Call<CallChat>
    getR(@Path("id") String id,
                @Query("token") String token,
          @Query("page") String page
             );
}
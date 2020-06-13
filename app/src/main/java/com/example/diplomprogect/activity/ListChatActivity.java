package com.example.diplomprogect.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.Toast;

import com.example.diplomprogect.R;
import com.example.diplomprogect.adapters.ChatAdapter;
import com.example.diplomprogect.adapters.MessageAdapter;
import com.example.diplomprogect.api.RetrofitClient;
import com.example.diplomprogect.models.CallChat;

import com.example.diplomprogect.models.ChResponse;
import com.example.diplomprogect.models.Message;
import com.example.diplomprogect.storage.SharedPrefManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_SHORT;

public class ListChatActivity extends AppCompatActivity {
    RecyclerView r;
    EditText e;
String id;
List<Message> list;
    List<Message> list1;
    MessageAdapter m;
    int c=0;
    InputStream inputStream;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_chat);
        e=findViewById(R.id.post);
        Intent i=getIntent();
        id=i.getStringExtra("id");
        r=findViewById(R.id.chlist);
        r.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();



       new Timer().scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                createList();

            }
        },0,1000);

    //    createList();
    }

    public void createList() {

        Call<CallChat> call = RetrofitClient
                .getInstance().getApi().getR(id, SharedPrefManager.getInstance().getToken(),"1");

        call.enqueue(new Callback<CallChat>() {
            @Override
            public void onResponse(Call<CallChat> call, Response<CallChat> response) {
                CallChat data = response.body();

                int count=Integer.parseInt(data.getPages());
                //get pages


                for (int i = 1; i <=count; i++) {
                    Call<CallChat> call1 = RetrofitClient
                            .getInstance().getApi().getR(id, SharedPrefManager.getInstance().getToken(), String.valueOf(i));

                    call1.enqueue(new Callback<CallChat>() {
                        @Override
                        public void onResponse(Call<CallChat> call, Response<CallChat> response) {
                            CallChat data1 = response.body();

                            list.addAll(data1.getList());


                        }

                        @Override
                        public void onFailure(Call<CallChat> call, Throwable t) {
                            Log.d("error", "response");
                        }
                    });
                }
                if(c==0) {
                    list1 = new ArrayList<>(list);
                    c++;
                    m = new MessageAdapter(ListChatActivity.this, list1, data.getChat());

                    r.setAdapter(m);
                }else if(c>0 && list1.size()!=list.size())
                {
                    list1 = new ArrayList<>(list);
                    Collections.sort(list1, new Comparator<Message>() {
                        @Override
                        public int compare(Message u1, Message u2) {
                            return u2.getCreatedAt().compareTo(u1.getCreatedAt());
                        }
                    });

                    m = new MessageAdapter(ListChatActivity.this, list1, data.getChat());
                    final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    linearLayoutManager.setReverseLayout(true);
                    r.setLayoutManager(linearLayoutManager);
                    r.setAdapter(m);

                }


    //list1=new ArrayList<>(list);

             list.clear();

















            }
            @Override
            public void onFailure(Call<CallChat> call, Throwable t) {
                Log.d("error","response");
            }
        });



      //  Toast.makeText(this,String.valueOf(data1.getPages()), LENGTH_SHORT).show();
      /*  int count=Integer.parseInt(data1.getPages());
        //get pages
        for (int i = 1; i <= count; i++) {
            Call<CallChat> call = RetrofitClient
                    .getInstance().getApi().getR(id, SharedPrefManager.getInstance().getToken(), String.valueOf(i));

            call.enqueue(new Callback<CallChat>() {
                @Override
                public void onResponse(Call<CallChat> call, Response<CallChat> response) {
                    CallChat data = response.body();

                    list.addAll(data.getList());



                }

                @Override
                public void onFailure(Call<CallChat> call, Throwable t) {
                    Log.d("error", "response");
                }
            });
        }
        m = new MessageAdapter(ListChatActivity.this, list, data1.getChat());

        r.setAdapter(m);
     list.clear();*/
    }



public void upload(View v)
{
    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
    intent.setType("image/*");
    try {
        startActivityForResult(intent, 10);
    } catch (ActivityNotFoundException e) {
        e.printStackTrace();
    }
}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            if (resultCode == RESULT_OK) {
                try {
                 inputStream = getContentResolver().openInputStream(Objects.requireNonNull(data.getData()));
                   type = getFileExtension(data.getData());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private byte[] getBytes(InputStream is) throws IOException {
        ByteArrayOutputStream byteBuff = new ByteArrayOutputStream();

        int buffSize = 1024;
        byte[] buff = new byte[buffSize];

        int len = 0;
        while ((len = is.read(buff)) != -1) {
            byteBuff.write(buff, 0, len);
        }

        return byteBuff.toByteArray();
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cr = this.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    private void sendUploadRequest(byte[] bytes , final String type) {


        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"),bytes);

        final MultipartBody.Part file = MultipartBody.Part.createFormData("documents" , "myImage." + type ,requestFile);

        Call<ChResponse> call = RetrofitClient
                .getInstance().getApi().getResponse(String.valueOf(id),SharedPrefManager.getInstance().getToken(),file,e.getText().toString());

        call.enqueue(new Callback<ChResponse>() {
            @Override
            public void onResponse(Call<ChResponse> call, Response<ChResponse> response) {
                ChResponse data = response.body();

                    Toast.makeText(getApplicationContext(),String.valueOf(data.isStatus()), LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ChResponse> call, Throwable t) {
                Log.d("teatimes","fals5443e");
            }



        });



        e.setText("");

    }

    public void send(View v) throws IOException {



            sendUploadRequest(getBytes(inputStream), type);


    }
}

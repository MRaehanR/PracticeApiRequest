package com.example.practiceapirequest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FanActivity extends AppCompatActivity {

    TextView tv_data;
    Button btn_getData, btn_pindah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan);

        getSupportActionBar().setTitle("Fast Android Networking");

        tv_data = findViewById(R.id.tv_data);
        btn_getData = findViewById(R.id.btn_getData);
        btn_pindah = findViewById(R.id.btn_pindah);

        btn_getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_data.setText("");

                AndroidNetworking.get("https://jsonplaceholder.typicode.com/users")
                        .build()
                        .getAsJSONArray(new JSONArrayRequestListener() {
                            @Override
                            public void onResponse(JSONArray response) {
                                try {
                                    for (int i = 0; i <= 7; i++) {
                                        JSONObject jsonObject = response.getJSONObject(i);

                                        String name = jsonObject.getString("name");
                                        String username = jsonObject.getString("username");
                                        String email = jsonObject.getString("email");

                                        tv_data.append("Name: " + name + "\nUsername: " + username + "\nEmail: " + email);
                                        tv_data.append("\n\n");
                                    }

                                    System.out.println("Halo");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(ANError anError) {

                            }
                        });
            }
        });
    }
}
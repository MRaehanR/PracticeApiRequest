package com.example.practiceapirequest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VolleyActivity extends AppCompatActivity {

    TextView tv_data;
    Button btn_getData, btn_pindah;
    RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        getSupportActionBar().setTitle("Volley");

        tv_data = findViewById(R.id.tv_data);
        btn_getData = findViewById(R.id.btn_getData);
        btn_pindah = findViewById(R.id.btn_pindah);

        mQueue = Volley.newRequestQueue(getApplicationContext());

        btn_getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_data.setText("");

                String url = "https://jsonplaceholder.typicode.com/users";

                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for (int i = 0; i < 7; i++) {
                                    try {
                                        JSONObject jsonObject = response.getJSONObject(i);

                                        String name = jsonObject.getString("name");
                                        String username = jsonObject.getString("username");
                                        String email = jsonObject.getString("email");

                                        System.out.println(name);

                                        tv_data.append("Name: " + name + "\nUsername: " + username + "\nEmail: " + email);
                                        tv_data.append("\n\n");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                mQueue.add(request);
            }
        });

        btn_pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FanActivity.class);
                startActivity(intent);
            }
        });
    }
}
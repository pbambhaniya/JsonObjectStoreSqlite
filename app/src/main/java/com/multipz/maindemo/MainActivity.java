package com.multipz.maindemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements ItemClickListener {
    RecyclerView rv_view;
    String param;
    Database database;
    ArrayList<StatusModel> list;
    StatusAdapter adapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
list=new ArrayList<>();
        database = new Database(this);
        ref();
        init();
        database.getWritableDatabase();
        getApicall();
    }

    private void ref() {
        rv_view = (RecyclerView) findViewById(R.id.rv_view);

    }

    private void init() {

    }

    private void getApicall() {
        String tag_string_req = "string_req";
        String url = Config.BASE_URL;
        StringRequest strReq = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String msg = "", status = "";
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    status = jsonObject.getString("Status");
                    msg = jsonObject.getString("Message");
                    if (status.contentEquals("1")) {
                        JSONArray data = jsonObject.getJSONArray("Response");
                        database.deleteAll();
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject j = data.getJSONObject(i);
                            String StatusId = j.getString("StatusId");
                            String Descriptions = j.getString("Descriptions");
                            database.insertData(StatusId, Descriptions);

                        }

                        list = database.fetchData();
                        adapter = new StatusAdapter(list, context);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                        rv_view.setLayoutManager(mLayoutManager);
                        rv_view.setItemAnimator(new DefaultItemAnimator());
                        rv_view.setAdapter(adapter);


                        adapter.setClickListener(MainActivity.this);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
//                dialog.dismiss();
                Log.d("error", "" + error);

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                try {
                    JSONObject object = new JSONObject();
                    object.put("Category_Id", "3");

                    param = object.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                String param = "{\"action\":\"" + Config.getcountry + "\"}";
                params.put("json", param);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    @Override
    public void itemClicked(View View, int position) {
        Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
    }
}

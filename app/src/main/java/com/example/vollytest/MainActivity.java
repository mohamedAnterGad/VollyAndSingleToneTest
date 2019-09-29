package com.example.vollytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView ;
    ArrayList<Data> dataArrayList = new ArrayList<>();

    SingleTone s ;
    RequestQueue r ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);

        s = SingleTone.getObject(this);
        r = s.getQueue() ;

        VollyTest();



    }



    void VollyTest (){

        String url = "https://api.myjson.com/bins/130t4t" ;
        JsonArrayRequest task = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            for (int i = 0; i < response.length(); i++) {


                                    JSONObject object = response.getJSONObject(i);
                                    Data data = new Data();

                                    data.setId(object.getString("id"));
                                    data.setName(object.getString("name"));
                                    JSONArray jsonArray = object.getJSONArray("branches");

                                    ArrayList<Branches> branchesArrayList = new ArrayList<>();
                                    for(int j=0;j<jsonArray.length() ;j++){

                                        JSONObject b = jsonArray.getJSONObject(j);
                                        Branches branches = new Branches();
                                        branches.setAddress(b.getString("address"));
                                        branches.setLng(b.getDouble("lng"));
                                        branches.setLat(b.getDouble("lat"));

                                        branchesArrayList.add(branches);
                                    }

                                    data.setBranches(branchesArrayList);
                                    dataArrayList.add(data);
                            }

                            DataAdapter adapter = new DataAdapter(MainActivity.this,R.layout.row_design,dataArrayList);

                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        //Volley.newRequestQueue(this).add(task);

        r.add(task);

    }



}

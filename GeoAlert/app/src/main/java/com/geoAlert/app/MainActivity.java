package com.geoAlert.app;

import android.content.Context;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TabHost;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    private String USER_BASE_URL = "https://api.mongolab.com/api/1/databases/geoalert/collections/geopoint?apiKey=WmCmHvlH4oGWeBU2R6DjC06jJdnD8zdx";
    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabHost=(TabHost)findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec spec1=tabHost.newTabSpec("TAB 1");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator("TAB 1");


        TabHost.TabSpec spec2=tabHost.newTabSpec("TAB 2");
        spec2.setIndicator("TAB 2");
        spec2.setContent(R.id.tab2);


        TabHost.TabSpec spec3=tabHost.newTabSpec("TAB 3");
        spec3.setContent(R.id.tab3);
        spec3.setIndicator("TAB 3");

        tabHost.addTab(spec1);
        tabHost.addTab(spec2);
        tabHost.addTab(spec3);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        ListView lv = (ListView)findViewById(R.id.listView);


        try {

            JSONArray data = new JSONArray(getJSONUrl(USER_BASE_URL));

            if (data!=null) Log.e("errorJSON", data.toString());

            final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;

            for(int i = 0; i < data.length(); i++){

                  JSONObject c = data.getJSONObject(i);

                  map = new HashMap<String, String>();
                  map.put("name", c.getString("name"));
                  Log.e("inpectJSON", c.getString("name"));

                  map.put("direction", c.getString("direction"));
                  Log.e("inpectJSON", c.getString("direction"));

                  map.put("latitud", c.getString("latitud"));
                  Log.e("inpectJSON", c.getString("latitud"));

                  map.put("longitud", c.getString("longitud"));
                  Log.e("inpectJSON", c.getString("longitud"));

                  map.put("type", c.getString("type"));
                  Log.e("inpectJSON", c.getString("type"));

                  map.put("priority", c.getString("priority"));
                  Log.e("inpectJSON", c.getString("priority"));

                  MyArrList.add(map);
           }


            for (int i=0;i<MyArrList.size();i++){
                Log.e("inpectJSON","Nueva GEO");
                Log.e("inpectJSON", MyArrList.get(i).toString());

            }

            ArrayList<ItemGeo> itemsGeo = obtenerItems(MyArrList);
            ItemGeoAdapter adapter = new ItemGeoAdapter(this, itemsGeo);
            lv.setAdapter(adapter);



        } catch (JSONException e) {

            e.printStackTrace();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private ArrayList<ItemGeo> obtenerItems(ArrayList<HashMap<String, String>> Listaitem) {
        ArrayList<ItemGeo> items = new ArrayList<ItemGeo>();

        for (int i=0;i<Listaitem.size();i++){

            items.add(new ItemGeo(i,Listaitem.get(i).get("name"),Listaitem.get(i).get("direction"),Listaitem.get(i).get("latitud"),Listaitem.get(i).get("longitud"),
            Listaitem.get(i).get("type"), Listaitem.get(i).get("priotity") ));

        }


        return items;
    }


    public String getJSONUrl(String url) {
        StringBuilder str = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = client.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    str.append(line);
                }
            } else {
                Log.e("Log", "Failed to download result..");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }




}

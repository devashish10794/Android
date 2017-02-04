package com.example.jaind.instaveritas;

import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDexApplication;
import android.util.Log;
import android.widget.Toast;

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


/**
 * Created by Devashish Jain on 17-05-2016.
 */
public class Appcontroller extends MultiDexApplication {

    public static final String TAG = Appcontroller.class.getSimpleName();
    public static final ArrayList<SearchData> searchDataList = new ArrayList<>();
    private static Appcontroller mInstance;

    private RequestQueue mRequestQueue;


    public static synchronized Appcontroller getInstance() {
        return mInstance;
    }

    public static void search(final Context context, String name, String searchType) {
        String urlJsonObj = "";
        if (searchType.equals("Actor")) {
            urlJsonObj = "http://netflixroulette.net/api/api.php?actor=" + name;
        } else if (searchType.equals("Director")) {
            urlJsonObj = "http://netflixroulette.net/api/api.php?actor=" + name;
        }


        JsonArrayRequest req = new JsonArrayRequest(urlJsonObj,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray array) {
                        Log.v("Search data", String.valueOf(array));

                        Log.d("ARRAY : ", String.valueOf(array));
                        try {

                            if (array != null) {
                                searchDataList.clear();

                                for (int i = 0; i < array.length(); i++) {
                                    SearchData searchData = new SearchData();
                                    JSONObject data = array.getJSONObject(i);
                                    searchData.setShow_id(data.getString("show_id"));
                                    searchData.setUnit(data.getString("unit"));
                                    searchData.setShow_title(data.getString("show_title"));
                                    searchData.setRelease_year(data.getString("release_year"));

                                    searchData.setRating(data.getString("rating"));
                                    searchData.setCategory(data.getString("category"));
                                    searchData.setShow_cast(data.getString("show_cast"));
                                    searchData.setDirector(data.getString("director"));

                                    searchData.setSummary(data.getString("summary"));
                                    searchData.setPoster(data.getString("poster"));
                                    searchData.setMediatype(data.getString("mediatype"));
                                    searchData.setRuntime(data.getString("runtime"));

                                    searchDataList.add(searchData);

                                }

                                Intent i = new Intent(context, SearchResult.class);
                                context.startActivity(i);

                            } else {
                                Toast.makeText(context, "No Data Found ! Try Again", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Toast.makeText(context, "No Data Found ! Try Again", Toast.LENGTH_SHORT).show();
            }
        });
        Appcontroller ins = Appcontroller.getInstance();
        ins.addToRequestQueue(req);


    }


    public static void searchTitle(final Context context, String title) {
        String urlJsonObj = "http://netflixroulette.net/api/api.php?title=" + title;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlJsonObj, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.v("Search data", String.valueOf(jsonObject));


                Log.v("Search data", String.valueOf(jsonObject));


                try {

                    if (jsonObject != null) {
                        searchDataList.clear();

                        SearchData searchData = new SearchData();
                        JSONObject data = jsonObject;
                        searchData.setShow_id(data.getString("show_id"));
                        searchData.setUnit(data.getString("unit"));
                        searchData.setShow_title(data.getString("show_title"));
                        searchData.setRelease_year(data.getString("release_year"));

                        searchData.setRating(data.getString("rating"));
                        searchData.setCategory(data.getString("category"));
                        searchData.setShow_cast(data.getString("show_cast"));
                        searchData.setDirector(data.getString("director"));

                        searchData.setSummary(data.getString("summary"));
                        searchData.setPoster(data.getString("poster"));
                        searchData.setMediatype(data.getString("mediatype"));
                        searchData.setRuntime(data.getString("runtime"));

                        searchDataList.add(searchData);

                        Intent i = new Intent(context, SearchResult.class);
                        context.startActivity(i);

                    } else {
                        Toast.makeText(context, "No Data Found ! Try Again", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Toast.makeText(context, "No Data Found ! Try Again", Toast.LENGTH_SHORT).show();
            }
        });
        Appcontroller.getInstance().addToRequestQueue(request);


    }


    public static void searchShow(final Context context, String name, String searchType) {
        String urlJsonObj = "";
        if (searchType.equals("Actor")) {
            urlJsonObj = "http://netflixroulette.net/api/api.php?actor=" + name;
        } else if (searchType.equals("Director")) {
            urlJsonObj = "http://netflixroulette.net/api/api.php?actor=" + name;
        }


        JsonArrayRequest req = new JsonArrayRequest(urlJsonObj,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray array) {
                        Log.v("Search data", String.valueOf(array));

                        Log.d("ARRAY : ", String.valueOf(array));
                        try {

                            if (array != null) {
                                searchDataList.clear();

                                for (int i = 0; i < array.length(); i++) {
                                    SearchData searchData = new SearchData();
                                    JSONObject data = array.getJSONObject(i);
                                    searchData.setShow_id(data.getString("show_id"));
                                    searchData.setUnit(data.getString("unit"));
                                    searchData.setShow_title(data.getString("show_title"));
                                    searchData.setRelease_year(data.getString("release_year"));

                                    searchData.setRating(data.getString("rating"));
                                    searchData.setCategory(data.getString("category"));
                                    searchData.setShow_cast(data.getString("show_cast"));
                                    searchData.setDirector(data.getString("director"));

                                    searchData.setSummary(data.getString("summary"));
                                    searchData.setPoster(data.getString("poster"));
                                    searchData.setMediatype(data.getString("mediatype"));
                                    searchData.setRuntime(data.getString("runtime"));

                                    searchDataList.add(searchData);

                                }

                                Intent i = new Intent(context, SearchShowResult.class);
                                context.startActivity(i);

                            } else {
                                Toast.makeText(context, "No Data Found ! Try Again", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Toast.makeText(context, "No Data Found ! Try Again", Toast.LENGTH_SHORT).show();
            }
        });
        Appcontroller ins = Appcontroller.getInstance();
        ins.addToRequestQueue(req);


    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

}




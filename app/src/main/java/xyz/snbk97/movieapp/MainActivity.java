package xyz.snbk97.movieapp;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import xyz.snbk97.movieapp.adapter.MovieAdapter;
import xyz.snbk97.movieapp.dataProvider.SingletonRequestQueue;
import xyz.snbk97.movieapp.models.MovieModel;

public class MainActivity extends AppCompatActivity {
    private List<MovieModel> MovieListItems;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.MovieList);
        GridLayoutManager GLM = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(GLM);
        MovieListItems = new ArrayList<>();
        fetchData(); // Fetch API Data
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager GLM = new GridLayoutManager(this, 4);
            recyclerView.setLayoutManager(GLM);
        } else {
            GridLayoutManager GLM = new GridLayoutManager(this, 2);
            recyclerView.setLayoutManager(GLM);
        }
    }

    public void fetchData() {
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading Data ...");
        pDialog.show();

        String TMDB_KEY = BuildConfig.TMBD_KEY;
        String baseUrl = "http://api.themoviedb.org/3/discover/movie?"; // TODO: 16-04-2018 Use UrlBuilder Class instead
        String finalUrl = baseUrl + "&api_key=" + TMDB_KEY + "&primary_release_date.lte=2017-01-01";
        final String imageUrl = "https://image.tmdb.org/t/p/w780";

        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(Request.Method.GET, finalUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray mJsonArray = response.getJSONArray("results");
                            for (int i = 0; i < mJsonArray.length(); i++) {
                                JSONObject mJsonObject = mJsonArray.getJSONObject(i);
                                MovieModel mMovie = new MovieModel(
                                        mJsonObject.getString("id"),
                                        mJsonObject.getString("title"),
                                        mJsonObject.getString("overview"),
                                        mJsonObject.getString("original_language"),
                                        mJsonObject.getString("release_date"),
                                        imageUrl + mJsonObject.getString("backdrop_path"),
                                        imageUrl + mJsonObject.getString("poster_path")
                                );
                                MovieListItems.add(mMovie);
                            }

                            adapter = new MovieAdapter(MovieListItems, getApplicationContext());
                            recyclerView.setAdapter(adapter);
                            pDialog.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue queue = SingletonRequestQueue.getInstance(this).getRequestQueue();
        queue.add(mJsonObjectRequest);

    }

}

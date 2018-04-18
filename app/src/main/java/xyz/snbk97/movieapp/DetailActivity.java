package xyz.snbk97.movieapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import xyz.snbk97.movieapp.dataProvider.SingletonRequestQueue;
import xyz.snbk97.movieapp.models.MovieModel;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.ClearTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle data = getIntent().getExtras();
        MovieModel currentMovie = data.getParcelable("Movie");


        TextView d_title = (TextView) findViewById(R.id.detail_title);
        TextView d_lang = (TextView) findViewById(R.id.detail_language);
        TextView d_overview = (TextView) findViewById(R.id.detail_overview);
        TextView d_date = (TextView) findViewById(R.id.detail_releasedate);
        ImageView d_img = (ImageView) findViewById(R.id.detail_img);
        Button d_trailer = (Button) findViewById(R.id.detail_trailer);

        Glide.with(this)
                .load(currentMovie.getBackdropPath())
                .asBitmap()
                .into(d_img);

        d_title.setText(currentMovie.getTitle());
        d_lang.setText(currentMovie.getOriginalLanguage());
        d_overview.setText(currentMovie.getOverview());
        d_date.setText(currentMovie.getReleaseDate());

        fetchTrailer(d_trailer, Integer.parseInt(currentMovie.getId()));

    }

    public void fetchTrailer(final Button b, int movieId) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data ...");
        progressDialog.show();

        String TMDB_KEY = BuildConfig.TMBD_KEY;
        String baseUrl = "http://api.themoviedb.org/3/movie/";
        String finalUrl = baseUrl + movieId + "/videos?api_key=" + TMDB_KEY;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, finalUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray mJsonArray = response.getJSONArray("results");
                            JSONObject mJsonObject = mJsonArray.getJSONObject(0);
                            final String yt_key = mJsonObject.getString("key");
                            b.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(v.getContext(), yt_key, Toast.LENGTH_SHORT).show();
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        RequestQueue requestQueue = SingletonRequestQueue.getInstance(this).getRequestQueue();
        requestQueue.add(jsonObjectRequest);


    }
}

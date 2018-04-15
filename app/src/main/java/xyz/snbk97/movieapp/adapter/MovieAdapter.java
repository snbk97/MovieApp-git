package xyz.snbk97.movieapp.adapter;

import android.content.Context;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import xyz.snbk97.movieapp.DetailActivity;
import xyz.snbk97.movieapp.R;
import xyz.snbk97.movieapp.models.MovieModel;

/**
 * Created by sayan on 15-04-2018.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    List<MovieModel> movieListItems;
    Context context;

    public MovieAdapter(List<MovieModel> movieListItems, Context context) {
        this.movieListItems = movieListItems;
        this.context = context;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_layout, parent, false);
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        MovieModel mCurrentMovie = movieListItems.get(position);
        String mTitle, mOverview, mReleaseDate, mImg;
        mTitle = mCurrentMovie.getTitle();
        mOverview = mCurrentMovie.getOverview();
        mReleaseDate = mCurrentMovie.getReleaseDate();
        mImg = mCurrentMovie.getPosterPath();

        holder.mTitle.setText(mTitle);
        holder.mReleaseDate.setText(mReleaseDate);
        Glide.with(context)
                .load(mImg)
                .asBitmap()
                .into(holder.mImg);
    }

    @Override
    public int getItemCount() {
        return movieListItems.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView mImg;
        TextView mTitle;
        TextView mReleaseDate;
//        TextView mOverview;
        public MovieViewHolder(final View itemView) {
            super(itemView);
            mImg = (ImageView) itemView.findViewById(R.id.mImg);
            mTitle = (TextView) itemView.findViewById(R.id.mTitle);
            mReleaseDate = (TextView) itemView.findViewById(R.id.mReleaseDate);
//            mOverview = (TextView) itemView.findViewById(R.id.mOverview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, DetailActivity.class);
                    context.startActivity(i);
                }
            });

        }
    }
}

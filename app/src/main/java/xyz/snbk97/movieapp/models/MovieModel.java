package xyz.snbk97.movieapp.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sayan on 18-04-2018.
 */
public class MovieModel implements Parcelable {
    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };
    private String id;
    private String backdropPath;
    private String originalLanguage;
    private String posterPath;
    private String title;
    private String overview;
    private String releaseDate;

    public MovieModel(String id, String title, String overview, String originalLanguage, String releaseDate, String backdropPath, String posterPath) {
        this.id = id;
        this.backdropPath = backdropPath;
        this.originalLanguage = originalLanguage;
        this.posterPath = posterPath;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    protected MovieModel(Parcel in) {
        id = in.readString();
        backdropPath = in.readString();
        originalLanguage = in.readString();
        posterPath = in.readString();
        title = in.readString();
        overview = in.readString();
        releaseDate = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(backdropPath);
        dest.writeString(originalLanguage);
        dest.writeString(posterPath);
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeString(releaseDate);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}

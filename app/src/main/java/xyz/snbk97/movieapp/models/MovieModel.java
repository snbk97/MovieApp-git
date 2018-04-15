package xyz.snbk97.movieapp.models;
/**
 * Created by sayan on 15-04-2018.
 */
public class MovieModel {
    //    TMBD model
    private String posterPath;
    private String title;
    private String overview;
    private String releaseDate;



    public MovieModel(String title, String overview, String releaseDate, String posterPath) {
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
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

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}

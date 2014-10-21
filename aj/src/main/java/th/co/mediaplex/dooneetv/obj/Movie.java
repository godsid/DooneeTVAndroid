package th.co.mediaplex.dooneetv.obj;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Banpot.S on 10/16/14 AD.
 */
public class Movie {

    final static public int SCORE_ONE = 1;
    final static public int SCORE_TWO = 2;
    final static public int SCORE_THREE = 3;
    final static public int SCORE_FOUR = 4;
    final static public int SCORE_FIVE = 5;

    final static public String RATING_G = "G";
    final static public String RATING_PG = "PG";
    final static public String RATING_PG13 = "PG-13";
    final static public String RATING_R = "R";
    final static public String RATING_NC17 = "NC-17";

    private String title, title_en, cover, summary, path, trailer, description, rating, cast, director, audio, subtitle;
    private int movie_id, score, year, length, view;
    private boolean is_free, is_hd, is_hot, is_3d, is_series, is_soon, is_18;

    public  Movie(int movie_id,String cover){
        this.movie_id = movie_id;
        this.cover = cover;
    }
    public Movie(JSONObject movieJSON){
        try {
            this.title = movieJSON.getString("title");
            this.title_en = movieJSON.getString("title_en");
            this.cover = movieJSON.getString("cover");
            this.summary = movieJSON.getString("summary");
            this.path = movieJSON.getString("path");
            this.trailer = movieJSON.getString("trailer");
            this.description = movieJSON.getString("description");
            this.rating = movieJSON.getString("rating");
            this.cast = movieJSON.getString("cast");
            this.director = movieJSON.getString("director");
            this.audio = movieJSON.getString("audio");
            this.subtitle = movieJSON.getString("subtitle");

            this.movie_id = movieJSON.getInt("movie_id");
            this.score = movieJSON.getInt("score");
            this.year = movieJSON.getInt("year");
            this.length = movieJSON.getInt("length");
            this.view = movieJSON.getInt("view");

            this.is_free = movieJSON.getString("is_free").equals("YES");
            this.is_hd = movieJSON.getString("is_hd").equals("YES");
            this.is_hot = movieJSON.getString("is_hot").equals("YES");
            this.is_3d = movieJSON.getString("is_3d").equals("YES");
            this.is_series = movieJSON.getString("is_series").equals("YES");
            this.is_soon = movieJSON.getString("is_soon").equals("YES");
            this.is_18 = movieJSON.getString("is_18").equals("YES");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_en() {
        return title_en;
    }

    public void setTitle_en(String title_en) {
        this.title_en = title_en;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public boolean isIs_free() {
        return is_free;
    }

    public void setIs_free(boolean is_free) {
        this.is_free = is_free;
    }

    public boolean isIs_hd() {
        return is_hd;
    }

    public void setIs_hd(boolean is_hd) {
        this.is_hd = is_hd;
    }

    public boolean isIs_hot() {
        return is_hot;
    }

    public void setIs_hot(boolean is_hot) {
        this.is_hot = is_hot;
    }

    public boolean isIs_3d() {
        return is_3d;
    }

    public void setIs_3d(boolean is_3d) {
        this.is_3d = is_3d;
    }

    public boolean isIs_series() {
        return is_series;
    }

    public void setIs_series(boolean is_series) {
        this.is_series = is_series;
    }

    public boolean isIs_soon() {
        return is_soon;
    }

    public void setIs_soon(boolean is_soon) {
        this.is_soon = is_soon;
    }

    public boolean isIs_18() {
        return is_18;
    }

    public void setIs_18(boolean is_18) {
        this.is_18 = is_18;
    }

}
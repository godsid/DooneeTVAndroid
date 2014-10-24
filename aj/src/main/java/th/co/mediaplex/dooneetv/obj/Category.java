package th.co.mediaplex.dooneetv.obj;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by silk on 22-Oct-14.
 */
public class Category {
    private String title, status;
    private int category_id, parent_id, sort, movie_item;

    public Category(JSONObject categoryJSON) {
        try {
            this.title = categoryJSON.getString("title");
            this.status = categoryJSON.getString("status");

            this.category_id = categoryJSON.getInt("category_id");
            this.parent_id = categoryJSON.getInt("parent_id");
            this.movie_item = categoryJSON.getInt("movie_item");
            this.sort = categoryJSON.getInt("sort");
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getMovie_item() {
        return movie_item;
    }

    public void setMovie_item(int movie_item) {
        this.movie_item = movie_item;
    }
}

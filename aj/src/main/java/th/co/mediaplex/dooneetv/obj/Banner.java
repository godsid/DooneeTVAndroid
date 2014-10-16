package th.co.mediaplex.dooneetv.obj;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Banpot.S on 10/16/14 AD.
 */
public class Banner {
    private String title, link, cover, description;
    private int banner_id, sort;

    public Banner(JSONObject bannerJSON){
        try {
            this.title = bannerJSON.getString("title");
            this.link = bannerJSON.getString("link");
            this.cover = bannerJSON.getString("cover");
            this.description = bannerJSON.getString("description");

            this.banner_id = bannerJSON.getInt("banner_id");
            this.sort = bannerJSON.getInt("sort");
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBanner_id() {
        return banner_id;
    }

    public void setBanner_id(int banner_id) {
        this.banner_id = banner_id;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}

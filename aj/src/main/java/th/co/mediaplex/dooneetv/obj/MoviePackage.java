package th.co.mediaplex.dooneetv.obj;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Banpot.S on 10/16/14 AD.
 */
public class MoviePackage {
    private String title, banner, name, detail, conditions, partner;
    private int package_id, dayleft, price;

    public MoviePackage(int package_id){
        this.package_id = package_id;
    }
    public MoviePackage(JSONObject packageJSON){
        try {
            this.title = packageJSON.getString("title");
            this.banner = packageJSON.getString("banner");
            this.name = packageJSON.getString("name");
            this.detail = packageJSON.getString("detail");
            this.conditions = packageJSON.getString("conditions");
            this.partner = packageJSON.getString("partner");

            this.package_id = packageJSON.getInt("package_id");
            this.dayleft = packageJSON.getInt("dayleft");
            this.price = packageJSON.getInt("price");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public MoviePackage(int package_id, String title, String banner, int dayleft, int price) {
        this.package_id = package_id;
        this.title = title;
        this.banner = banner;
        this.dayleft = dayleft;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public int getPackage_id() {
        return package_id;
    }

    public void setPackage_id(int package_id) {
        this.package_id = package_id;
    }

    public int getDayleft() {
        return dayleft;
    }

    public void setDayleft(int dayleft) {
        this.dayleft = dayleft;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

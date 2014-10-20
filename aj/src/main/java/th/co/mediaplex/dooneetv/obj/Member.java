package th.co.mediaplex.dooneetv.obj;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by Banpot.S on 10/16/14 AD.
 */
public class Member {
    final static public String GENDER_MAIL = "MAIL";
    final static public String GENDER_FEMAIL = "FEMAIL";

    private int user_id,dayleft;
    private String email, avatar, firstname, lastname, idcard, phone, facebook_id,gender;

    private Date birthdate, expire_date;

    public Member(int user_id, String email, String avatar) {
        this.user_id = user_id;
        this.email = email;
        this.avatar = avatar;
    }

    public Member(JSONObject memberJSON){
        try {
            this.user_id = memberJSON.getInt("user_id");
            this.dayleft = memberJSON.getInt("dayleft");

            this.email = memberJSON.getString("email");
            this.avatar = memberJSON.getString("avatar");
            this.firstname = memberJSON.getString("firstname");
            this.lastname = memberJSON.getString("lastname");
            this.idcard = memberJSON.getString("idcard");
            this.phone = memberJSON.getString("phone");
            this.facebook_id = memberJSON.getString("facebook_id");
            this.gender = memberJSON.getString("gender");
            //this.birthdate = memberJSON.getString("birthdate");
            //this.expire_date = memberJSON.getString("expire_date");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public JSONObject getJSONObject(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("user_id",user_id);
            jsonObject.put("dayleft",dayleft);
            jsonObject.put("email",email);
            jsonObject.put("avatar",avatar);
            jsonObject.put("firstname",firstname);
            jsonObject.put("lastname",lastname);
            jsonObject.put("idcard",idcard);
            jsonObject.put("phone",phone);
            jsonObject.put("facebook_id",facebook_id);
            jsonObject.put("gender",gender);
            jsonObject.put("dayleft",dayleft);
            jsonObject.put("expire_date",expire_date);
            jsonObject.put("expire_date",birthdate);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public String getJSONString(){
        return getJSONObject().toString();
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getDayleft() {
        return dayleft;
    }

    public void setDayleft(int dayleft) {
        this.dayleft = dayleft;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFacebook_id() {
        return facebook_id;
    }

    public void setFacebook_id(String facebook_id) {
        this.facebook_id = facebook_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(Date expire_date) {
        this.expire_date = expire_date;
    }
}

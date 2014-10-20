package th.co.mediaplex.dooneetv;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import th.co.mediaplex.dooneetv.obj.Member;

/**
 * Created by Banpot.S on 10/16/14 AD.
 */
public class MyApplication extends Application{

    private static final String MEMBER_PREFERENCES = "member";

    public Member member;
    public String versionName,packageName;
    public int versionCode;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(Config.TAG, "Application Create");
        try {
            PackageInfo packageInfo = getPackageManager()
                    .getPackageInfo(getPackageName(), 0);
            versionName = packageInfo.versionName;
            versionCode = packageInfo.versionCode;
            packageName = packageInfo.packageName;

        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
        //Check Member Login
        getSharedPreferencesData();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public boolean isLogin(){
        if(this.member!=null){
            return true;
        }else{
            return false;
        }

    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
        this.setMemberPreferences();

    }

    private void setMemberPreferences(){
        SharedPreferences sharedpreferences = getSharedPreferences(MEMBER_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("memberLogin",member.getJSONString());
        editor.commit();
    }
    private void getSharedPreferencesData(){
        SharedPreferences sharedpreferences = getSharedPreferences(MEMBER_PREFERENCES, Context.MODE_PRIVATE);
        String memberLogin = sharedpreferences.getString("memberLogin","");
        if(!memberLogin.equals("")){
            try {
                member = new Member(new JSONObject(memberLogin));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public void setSharedPreferencesData(){
        SharedPreferences sharedpreferences = getSharedPreferences(MEMBER_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        //editor.putString("memberLogin",member.getJSONString());
        editor.commit();
    }
}

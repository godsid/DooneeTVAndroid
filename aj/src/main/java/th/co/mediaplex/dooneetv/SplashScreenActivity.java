package th.co.mediaplex.dooneetv;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;


public class SplashScreenActivity extends Activity {

    protected  MyApplication myApplication;
    Handler handler;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        myApplication = (MyApplication)getApplication();
        Log.d("tui", "SplashScreen create");
        TextView versionTextView = (TextView)findViewById(R.id.versionTextView);
        handler = new Handler();
        if(BuildConfig.DEBUG){
            versionTextView.setText(getString(R.string.version)+": "+myApplication.versionName+" Development");
        }else{
            versionTextView.setText(getString(R.string.version)+": "+myApplication.versionName);
        }

        runnable = new Runnable() {
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
    }
    public void onResume() {
        super.onResume();
        Log.d(Config.TAG,String.valueOf(BuildConfig.DEBUG));
        if(BuildConfig.DEBUG){
            handler.postDelayed(runnable, 500);
        }else {
            handler.postDelayed(runnable, Config.SPLASH_SCREEN_DELAY);
        }
    }

    public void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);
    }
    @Override
    public void onBackPressed() {
    }
}

package th.co.mediaplex.dooneetv;

import android.app.Application;

import th.co.mediaplex.dooneetv.obj.Member;

/**
 * Created by Banpot.S on 10/16/14 AD.
 */
public class App extends Application{

    public Member member;

    public App() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}

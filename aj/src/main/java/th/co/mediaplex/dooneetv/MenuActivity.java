package th.co.mediaplex.dooneetv;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by silk on 20-Oct-14.
 */
public class MenuActivity extends Activity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_category:
                return true;
            case R.id.action_search:
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

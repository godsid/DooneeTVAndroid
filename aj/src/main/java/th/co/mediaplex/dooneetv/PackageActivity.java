package th.co.mediaplex.dooneetv;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import th.co.mediaplex.dooneetv.adapter.PackageAdapter;
import th.co.mediaplex.dooneetv.obj.MoviePackage;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class PackageActivity extends Activity {

    public AQuery aq;
    private GridView packageGridView;
    private ArrayList<MoviePackage> moviePackageArrayList;
    private PackageAdapter packageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);
        packageGridView = (GridView)findViewById(R.id.packageGridView);

        moviePackageArrayList = new ArrayList<MoviePackage>();
        packageAdapter = new PackageAdapter(this, moviePackageArrayList);
        packageGridView.setAdapter(packageAdapter);

        packageGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),PackageDetailActivity.class);
                MoviePackage selectedMoviePackage = moviePackageArrayList.get(position);
                intent.putExtra("package_id", selectedMoviePackage.getPackage_id());
                intent.putExtra("conditions", selectedMoviePackage.getConditions());
                intent.putExtra("detail", selectedMoviePackage.getDetail());
                intent.putExtra("dayleft", selectedMoviePackage.getDayleft());
                intent.putExtra("price", selectedMoviePackage.getPrice());
                intent.putExtra("title", selectedMoviePackage.getTitle());
                startActivity(intent);
            }
        });

        aq = new AQuery(getApplicationContext());
        getPackage();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.movie_package, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getPackage(){
        String url = Config.urlApiInit;
        Log.d(Config.TAG,url);
        aq
          .progress(new Dialog(getApplicationContext()))
          .ajax(url, JSONObject.class, Config.defaultCache, new AjaxCallback<JSONObject>() {
              @Override
              public void callback(String url, JSONObject object, AjaxStatus status) {
                  super.callback(url, object, status);
                  if (object != null) {
                      try {
                          JSONArray moviePackages = object.getJSONObject("package").getJSONArray("items");
                          for (int i = 0, j = moviePackages.length(); i < j; i++) {
                              moviePackageArrayList.add(new MoviePackage(moviePackages.getJSONObject(i)));
                          }
                          packageAdapter.notifyDataSetChanged();

                      } catch (JSONException e) {
                          e.printStackTrace();
                      }
                  } else {
                      Toast.makeText(getApplicationContext(), getString(R.string.error_json_null), Toast.LENGTH_LONG).show();
                  }
              }
          });
    }
}

package th.co.mediaplex.dooneetv;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import th.co.mediaplex.dooneetv.adapter.MovieAdapter;
import th.co.mediaplex.dooneetv.obj.Movie;


public class MainActivity extends Activity {
    private SliderLayout bannerSlider;
    private GridView movieHotGridView,movieAllGridView;
    private String url = Config.urlApiHome;
    private AQuery aq;
    private int i, j;
    private JSONArray objectArray;

   public ArrayList<Movie> movieHotArrayList,movieAllArrayList;
   public MovieAdapter movieHotAdapter,movieAllAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aq = new AQuery(this);
        bannerSlider = (SliderLayout)findViewById(R.id.bannerSlider);
        movieHotGridView = (GridView)findViewById(R.id.movieHotGridView);
        movieAllGridView = (GridView)findViewById(R.id.movieAllGridView);
        movieAllArrayList = new ArrayList<Movie>();
        movieHotArrayList = new ArrayList<Movie>();
        movieHotAdapter = new MovieAdapter(this, movieHotArrayList);
        movieAllAdapter = new MovieAdapter(this, movieAllArrayList);
        movieHotGridView.setAdapter(movieHotAdapter);
        movieAllGridView.setAdapter(movieAllAdapter);

        aq.ajax(url, JSONObject.class, 3600, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                if (object != null) {
                    try {
                        objectArray = object.getJSONObject("movieHot").getJSONArray("items");
                        for (i = 0, j = objectArray.length(); i < j; i++) {
                            movieHotArrayList.add(new Movie(objectArray.getJSONObject(i)));
                        }
                        objectArray = object.getJSONObject("movies").getJSONArray("items");
                        for (i = 0, j = objectArray.length(); i < j; i++) {
                            movieAllArrayList.add(new Movie(objectArray.getJSONObject(i)));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    movieHotAdapter.notifyDataSetChanged();
                    movieAllAdapter.notifyDataSetChanged();
                }
            }
        });
        createBannerSlider();
        createMovieHot();
        createMovieAll();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    private void createBannerSlider(){
        aq.ajax(url, JSONObject.class, 3600, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                if (object != null) {
                    try {
                        objectArray = object.getJSONObject("banner").getJSONArray("items");
                        for (int a = 0, b = objectArray.length(); a < b; a++) {
                            TextSliderView textSliderView = new TextSliderView(getBaseContext());
                                textSliderView
                                        .description(objectArray.getJSONObject(a).getString("title"))
                                        .image(objectArray.getJSONObject(a).getString("cover"))
                                        .setScaleType(BaseSliderView.ScaleType.Fit);
                                textSliderView.getBundle()
                                        .putString("extra", objectArray.getJSONObject(a).getString("title"));
                            bannerSlider.addSlider(textSliderView);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //.setOnSliderClickListener((BaseSliderView.OnSliderClickListener) this);
        //add your extra information
        bannerSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        bannerSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        bannerSlider.setCustomAnimation(new DescriptionAnimation());
        bannerSlider.setDuration(4000);
    }
    private void createMovieHot(){

    }
    private void createMovieAll(){

    }
}

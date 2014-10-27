package th.co.mediaplex.dooneetv;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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


public class MainActivity extends MenuActivity {
    private SliderLayout bannerSlider;
    private GridView movieAllGridView;
    private String url = Config.urlApiHome;
    private AQuery aq;
    public ArrayList<Movie> movieAllArrayList;
    public MovieAdapter movieAllAdapter;
    private JSONArray objectArray;
    protected MyApplication myApplication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myApplication = (MyApplication)getApplication();
        if(!myApplication.isLogin()){
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        }

        aq = new AQuery(this);
        bannerSlider = (SliderLayout)findViewById(R.id.bannerSlider);
        movieAllGridView = (GridView)findViewById(R.id.movieAllGridView);
        movieAllArrayList = new ArrayList<Movie>();
        movieAllAdapter = new MovieAdapter(this, movieAllArrayList);
        movieAllGridView.setAdapter(movieAllAdapter);

        movieAllGridView.requestFocus();

        movieAllGridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if(movieAllArrayList.get(position).isIs_18()) {
                    showDialog(MainActivity.this, getString(R.string.title_is_18),getString(R.string.message_is_18), movieAllArrayList.get(position));
                }else{
                    Intent intent = new Intent(getBaseContext(), MovieDetailActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    int movie_id = movieAllArrayList.get(position).getMovie_id();
                    intent.putExtra("movie_id", movie_id);
                    startActivity(intent);
                }
            }
        });
        String movieUrl = Config.urlApiMovies
                                .replace("{page}", "1")
                                .replace("{limit}", "30");
        Log.d(Config.TAG, movieUrl);
        aq.ajax(movieUrl, JSONObject.class, 3600, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                if (object != null) {
                    Log.d(Config.TAG,object.toString());
                    try {
                        objectArray = object.getJSONArray("items");
                        for (int i = 0, j = objectArray.length(); i < j; i++) {
                            movieAllArrayList.add(new Movie(objectArray.getJSONObject(i)));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    movieAllAdapter.notifyDataSetChanged();
                    movieAllGridView.setSelection(0);
                }
            }
        });
        createBannerSlider();
        createMovieHot();
        createMovieAll();
    }

    private void createBannerSlider(){
        aq.ajax(url, JSONObject.class, 3600, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                if (object != null) {
                    try {
                        objectArray = object.getJSONObject("banner").getJSONArray("items");
                        for (int i = 0, j = objectArray.length(); i < j; i++) {
                            TextSliderView textSliderView = new TextSliderView(getBaseContext());
                                textSliderView
                                        .description(objectArray.getJSONObject(i).getString("title"))
                                        .image(objectArray.getJSONObject(i).getString("cover"))
                                        .setScaleType(BaseSliderView.ScaleType.Fit);
                                textSliderView.getBundle()
                                        .putString("extra", objectArray.getJSONObject(i).getString("title"));
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

    public static void showDialog(final Context context, String title, String message, final Movie movie) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(title);
        dialog.setIcon(R.drawable.ic_launcher);
        dialog.setCancelable(true);
        dialog.setMessage(message);
        dialog.setPositiveButton(context.getString(R.string.alert_yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(context, MovieDetailActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                int movie_id = movie.getMovie_id();
                intent.putExtra("movie_id", movie_id);
                context.startActivity(intent);
            }
        });
        dialog.setNegativeButton(context.getString(R.string.alert_no), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
}

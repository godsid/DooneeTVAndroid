package th.co.mediaplex.dooneetv;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;

import th.co.mediaplex.dooneetv.adapter.MovieAdapter;
import th.co.mediaplex.dooneetv.obj.Movie;


public class MainActivity extends Activity {
    private SliderLayout bannerSlider;
    private GridView movieHotGridView,movieAllGridView;

   public ArrayList<Movie> movieHotArrayList,movieAllArrayList;
   public MovieAdapter movieHotAdapter,movieAllAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bannerSlider = (SliderLayout)findViewById(R.id.bannerSlider);
        movieHotGridView = (GridView)findViewById(R.id.movieHotGridView);
        movieAllGridView = (GridView)findViewById(R.id.movieAllGridView);
        movieAllArrayList = new ArrayList<Movie>();
        movieHotArrayList = new ArrayList<Movie>();
        movieHotAdapter = new MovieAdapter(this,movieHotArrayList);
        movieAllAdapter = new MovieAdapter(this,movieAllArrayList);
        movieHotGridView.setAdapter(movieHotAdapter);
        movieAllGridView.setAdapter(movieAllAdapter);

        movieAllArrayList.add(new Movie(1,"http://www.dooneetv.com/assets/files/2014/d1a34.jpg"));
        movieAllArrayList.add(new Movie(1,"http://www.dooneetv.com/assets/files/2014/d1a34.jpg"));
        movieAllArrayList.add(new Movie(1,"http://www.dooneetv.com/assets/files/2014/d1a34.jpg"));
        movieAllArrayList.add(new Movie(1,"http://www.dooneetv.com/assets/files/2014/d1a34.jpg"));
        movieAllArrayList.add(new Movie(1,"http://www.dooneetv.com/assets/files/2014/d1a34.jpg"));
        movieAllArrayList.add(new Movie(1,"http://www.dooneetv.com/assets/files/2014/d1a34.jpg"));
        movieAllArrayList.add(new Movie(1,"http://www.dooneetv.com/assets/files/2014/d1a34.jpg"));
        movieAllArrayList.add(new Movie(1,"http://www.dooneetv.com/assets/files/2014/d1a34.jpg"));

        movieHotArrayList.add(new Movie(1,"http://www.dooneetv.com/assets/files/2014/d1a34.jpg"));
        movieHotArrayList.add(new Movie(1,"http://www.dooneetv.com/assets/files/2014/d1a34.jpg"));
        movieHotArrayList.add(new Movie(1,"http://www.dooneetv.com/assets/files/2014/d1a34.jpg"));
        movieHotArrayList.add(new Movie(1,"http://www.dooneetv.com/assets/files/2014/d1a34.jpg"));
        movieHotArrayList.add(new Movie(1,"http://www.dooneetv.com/assets/files/2014/d1a34.jpg"));
        movieHotArrayList.add(new Movie(1,"http://www.dooneetv.com/assets/files/2014/d1a34.jpg"));
        movieHotArrayList.add(new Movie(1,"http://www.dooneetv.com/assets/files/2014/d1a34.jpg"));
        movieHotArrayList.add(new Movie(1,"http://www.dooneetv.com/assets/files/2014/d1a34.jpg"));



        movieAllAdapter.notifyDataSetChanged();
        movieHotAdapter.notifyDataSetChanged();

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
        TextSliderView textSliderView = new TextSliderView(this);
        // initialize a SliderLayout
        textSliderView
                .description("test")
                .image(R.drawable.banner_cover)
                .setScaleType(BaseSliderView.ScaleType.Fit);
        //.setOnSliderClickListener((BaseSliderView.OnSliderClickListener) this);
        //add your extra information
        textSliderView.getBundle()
                .putString("extra","test");
        bannerSlider.addSlider(textSliderView);
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

package th.co.mediaplex.dooneetv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONException;
import org.json.JSONObject;


public class MovieDetailActivity extends Activity {
    private ImageView coverImageView;
    private TextView txt_movie_title, txt_movie_description, txt_movie_score, txt_movie_rating, txt_movie_cast, txt_movie_director, txt_movie_year, txt_movie_length;
    private ImageButton playImageButton, subscribeImageButton;
    private AQuery aq;
    private int movie_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        coverImageView = (ImageView)findViewById(R.id.bannerImageView);
        txt_movie_title = (TextView)findViewById(R.id.descriptionTextView);
        txt_movie_description = (TextView)findViewById(R.id.movieDescTextView);
        txt_movie_score = (TextView)findViewById(R.id.scoreTextView);
        txt_movie_rating = (TextView)findViewById(R.id.ratingTextView);
        txt_movie_cast = (TextView)findViewById(R.id.castTextView);
        txt_movie_director = (TextView)findViewById(R.id.directorTextView);
        txt_movie_year = (TextView)findViewById(R.id.yearTextView);
        txt_movie_length = (TextView)findViewById(R.id.lengthTextView);
        playImageButton = (ImageButton)findViewById(R.id.playImageButton);
        playImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), PlayerActivity.class);
                startActivity(intent);
            }
        });
        subscribeImageButton = (ImageButton)findViewById(R.id.subscribeImageButton);
        subscribeImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), PackageActivity.class);
                startActivity(intent);
            }
        });
        aq = new AQuery(this);
        
        Intent intent = getIntent();
        movie_id = intent.getIntExtra("movie_id", 0);

        String url = Config.urlApiMovie.replace("{movie_id}", String.valueOf(movie_id));
        aq.ajax(url, JSONObject.class, 3600, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                if (object != null) {
                    try {
                        aq.id(txt_movie_title).text(Html.fromHtml("<b>Title: </b>" + object.getString("title")));
                        aq.id(txt_movie_description).text(Html.fromHtml("<b>Description: </b>" + object.getString("description")));
                        aq.id(txt_movie_score).text(Html.fromHtml("<b>Score: </b>" + object.getString("score")));
                        aq.id(txt_movie_rating).text(Html.fromHtml("<b>Rating: </b>" + object.getString("rating")));
                        aq.id(txt_movie_cast).text(Html.fromHtml("<b>Cast: </b>" + object.getString("cast")));
                        aq.id(txt_movie_director).text(Html.fromHtml("<b>Director: </b>" + object.getString("director")));
                        aq.id(txt_movie_year).text(Html.fromHtml("<b>Year: </b>" + object.getString("year")));
                        aq.id(txt_movie_length).text(Html.fromHtml("<b>Length: </b>" + object.getString("length")));
                        aq.id(coverImageView).image(object.getString("cover"), true, true, 0, 0, null, AQuery.FADE_IN_NETWORK, AQuery.RATIO_PRESERVE);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.movie_detail, menu);
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
}

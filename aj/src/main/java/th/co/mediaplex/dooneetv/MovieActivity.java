package th.co.mediaplex.dooneetv;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import th.co.mediaplex.dooneetv.adapter.MovieAdapter;
import th.co.mediaplex.dooneetv.obj.Movie;


public class MovieActivity extends Activity {
    private AQuery aq;
    private int page;
    private GridView movieGridView;
    private ArrayList<Movie> movieArrayList;
    private MovieAdapter movieAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        aq = new AQuery(this);
        movieGridView = (GridView)findViewById(R.id.movieGridView);
        movieArrayList = new ArrayList<Movie>();
        movieAdapter = new MovieAdapter(this, movieArrayList);
        movieGridView.setAdapter(movieAdapter);
        Intent intent = getIntent();
        String q = null;

        String url = Config.urlApiMovieSearch
                .replace("{q}", q)
                .replace("{page}", String.valueOf(page));
        aq.ajax(url, JSONObject.class, 3600, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                if (object != null) {
                    try {
                        page = page + 1;
                        JSONArray objectArray = object.getJSONArray("items");
                        for (int i = 0, j = objectArray.length(); i < j; i++) {
                            movieArrayList.add(new Movie(objectArray.getJSONObject(i)));
                            movieGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                                    if(movieArrayList.get(position).isIs_18()) {
                                        showDialog(MovieActivity.this, getString(R.string.title_is_18),getString(R.string.message_is_18), movieArrayList.get(position));
                                    }else{
                                        Intent intent = new Intent(getBaseContext(), MovieDetailActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        int movie_id = movieArrayList.get(position).getMovie_id();
                                        intent.putExtra("movie_id", movie_id);
                                        startActivity(intent);
                                    }
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    movieAdapter.notifyDataSetChanged();
                }
            }
        });
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

package th.co.mediaplex.dooneetv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.androidquery.AQuery;

import java.util.ArrayList;

import th.co.mediaplex.dooneetv.R;
import th.co.mediaplex.dooneetv.obj.Movie;

/**
 * Created by Banpot.S on 10/16/14 AD.
 */
public class MovieAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<Movie> movieArrayList;
    private LayoutInflater mInflater;
    private AQuery aq;

    public MovieAdapter(Context context,ArrayList<Movie> movieArrayList) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.movieArrayList = movieArrayList;
        aq = new AQuery(context);
    }

    @Override
    public int getCount() {
        return movieArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return movieArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieItemView movieItemView;
        Movie movie = movieArrayList.get(position);
        if(convertView==null){
            convertView = mInflater.inflate(R.layout.item_movie,null);
            movieItemView = new MovieItemView();
            movieItemView.coverImageView =  (ImageView) convertView.findViewById(R.id.coverImageView);
            movieItemView.progressBar =  (ProgressBar) convertView.findViewById(R.id.progressBar);
            convertView.setTag(movieItemView);
        }else{
            movieItemView = (MovieItemView) convertView.getTag();
        }

        aq.id(movieItemView.coverImageView)
          .progress(movieItemView.progressBar)
          .image(movie.getCover(),true,true,0,0,null,AQuery.FADE_IN_NETWORK,AQuery.RATIO_PRESERVE);

        return convertView;
    }
}

class MovieItemView{
    ImageView coverImageView;
    ProgressBar progressBar;
}
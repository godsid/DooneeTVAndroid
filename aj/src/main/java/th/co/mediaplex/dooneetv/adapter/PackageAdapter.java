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
import th.co.mediaplex.dooneetv.obj.MoviePackage;


/**
 * Created by Banpot.S on 10/16/14 AD.
 */
public class PackageAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<MoviePackage> moviePackageArrayList;
    private LayoutInflater mInflater;
    private AQuery aq;

    public PackageAdapter(Context context, ArrayList<MoviePackage> moviePackageArrayList) {
        this.context = context;
        this.moviePackageArrayList = moviePackageArrayList;
        this.mInflater = LayoutInflater.from(context);
        aq = new AQuery(context);
    }

    @Override

    public int getCount() {
        return moviePackageArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return moviePackageArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PackageItemView packageItemView;
        MoviePackage movieMoviePackage = moviePackageArrayList.get(position);
        if(convertView==null){
            convertView = mInflater.inflate(R.layout.item_package,null);
            packageItemView = new PackageItemView();
            packageItemView.bannerImageView =  (ImageView) convertView.findViewById(R.id.bannerImageView);
            packageItemView.progressBar =  (ProgressBar) convertView.findViewById(R.id.progressBar);
            convertView.setTag(packageItemView);
        }else{
            packageItemView = (PackageItemView) convertView.getTag();
        }

        aq.id(packageItemView.bannerImageView)
                .progress(packageItemView.progressBar)
                .image(movieMoviePackage.getBanner(), true, true, 0, 0, null,AQuery.FADE_IN, AQuery.RATIO_PRESERVE);
        return convertView;
    }
}

class PackageItemView{
    ImageView bannerImageView;
    ProgressBar progressBar;
}


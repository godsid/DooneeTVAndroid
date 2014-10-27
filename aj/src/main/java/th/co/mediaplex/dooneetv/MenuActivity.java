package th.co.mediaplex.dooneetv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import th.co.mediaplex.dooneetv.obj.Category;

/**
 * Created by silk on 20-Oct-14.
 */
//public class MenuActivity extends Activity{
public class MenuActivity extends Activity implements SearchView.OnQueryTextListener {
    private SearchView mSearchView;
    private AQuery aq;
    private ArrayAdapter<String> adapter;
    private ArrayList<Category> categoryArray = new ArrayList<Category>();
    private ArrayList<String> categoryTitle = new ArrayList<String>();
    private MenuItem categorySpinner;
    private TextView searchTextView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        aq = new AQuery(this);
        Category();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        categorySpinner = menu.findItem(R.id.action_category);
        setupSpinner(categorySpinner);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
    switch(item.getItemId()){
        case R.id.action_search:
            return true;
        case R.id.action_profile:
            return true;
        case R.id.action_category:
            return true;
    }
    return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    //The following callbacks are called for the SearchView.OnQueryChangeListener
    public boolean onQueryTextChange(String newText) {
        return true;
    }

    public boolean onQueryTextSubmit(String query) {
        Toast.makeText(this, "Searching for: " + query + "...", Toast.LENGTH_SHORT).show();
        Intent intentSearch = new Intent(this, MovieActivity.class);
        intentSearch.putExtra("Search", query);
        startActivity(intentSearch);
        return true;
    }

    public void Category(){
        String url = Config.urlApiInit;
        aq.ajax(url, JSONObject.class, 3600, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                if (object != null) {
                    try {
                        JSONArray objectArray = object.getJSONArray("categories");
                        categoryArray.add(null);
                        for (int i = 0; i < objectArray.length(); i++) {
                            categoryArray.add(new Category(objectArray.getJSONObject(i)));
                        }
                        categoryTitle.add("---Category---");
                        for (int i = 1; i < categoryArray.size(); i++){
                            categoryTitle.add(categoryArray.get(i).getTitle());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void setupSpinner(MenuItem item) {

        View view = item.getActionView();
        if (view instanceof Spinner) {
            final Spinner spinner = (Spinner) view;
            adapter = new ArrayAdapter<String>(getActionBar().getThemedContext(), android.R.layout.simple_spinner_item, categoryTitle);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(position != 0) {
                        Intent intent = new Intent(getBaseContext(), CategoryActivity.class);
                        int category_id = categoryArray.get(position).getCategory_id();
                        intent.putExtra("category_id", category_id);
                        startActivity(intent);
                    }
                }
                @Override
                public void onNothingSelected(AdapterView <?> parent) {
                }
            });
        }
    }
 }


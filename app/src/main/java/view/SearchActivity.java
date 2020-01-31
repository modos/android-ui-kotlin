package view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.modos.ehsan.R;

import java.util.ArrayList;
import java.util.Random;

import controller.SearchAdapter;
import model.LatestModel;
import model.MyData;

public class SearchActivity extends AppCompatActivity {

    public static RecyclerView recyclerView;
    private ArrayList<LatestModel> data;
    private SearchAdapter adapter;
    private SearchView searchView;
    private CheckBox checkBox_name, checkBox_address, checkBox_price;
    public static TextView empty;

    public static boolean CHECK_BOX_NAME_IS_CHECKED = true;
    public static boolean CHECK_BOX_ADDRESS_IS_CHECKED = false;
    public static boolean CHECK_BOX_PRICE_IS_CHECKED = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        checkBox_name = findViewById(R.id.checkbox_name);
        checkBox_address = findViewById(R.id.checkbox_address);
        checkBox_price = findViewById(R.id.price_checkbox);

        empty = findViewById(R.id.empty_text);

        checkingCheckBoxes();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle(R.string.search);

        recyclerView = findViewById(R.id.search_recycler);

        data = new ArrayList<LatestModel>();

        for (int i = 0; i < MyData.names.length; i++){

            Random random = new Random();

            data.add(new LatestModel(
                    MyData.names[i],
                    MyData.addresses[i],
                    MyData.prices[i].toString(),
                    MyData.images[random.nextInt(10)]
            ));
        }

        adapter = new SearchAdapter(data);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });


        return true;

    }

    private void checkingCheckBoxes(){

        checkBox_name.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBox_name.isChecked()){
                    CHECK_BOX_NAME_IS_CHECKED = true;
                }else {
                    CHECK_BOX_NAME_IS_CHECKED = false;
                }
            }
        });

        checkBox_address.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBox_address.isChecked()){
                    CHECK_BOX_ADDRESS_IS_CHECKED = true;
                }else{
                    CHECK_BOX_ADDRESS_IS_CHECKED = false;
                }
            }
        });

        checkBox_price.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBox_address.isChecked()){
                    CHECK_BOX_PRICE_IS_CHECKED = true;
                }else{
                    CHECK_BOX_PRICE_IS_CHECKED = false;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

        overridePendingTransition(R.transition.exit,R.transition.entry);
    }

}

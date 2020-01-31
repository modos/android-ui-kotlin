package view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.modos.ehsan.R;

import java.util.ArrayList;
import java.util.Random;

import controller.LatestAdapter;
import model.LatestModel;
import model.MyData;

public class LatestActivty extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<LatestModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest);

        recyclerView = findViewById(R.id.latest_recycler);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

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

        adapter = new LatestAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        overridePendingTransition(R.transition.exit,R.transition.entry);
    }
}

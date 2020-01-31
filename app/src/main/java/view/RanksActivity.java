package view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.modos.ehsan.R;

import java.util.ArrayList;

import controller.RanksAdapter;
import model.MyData;
import model.RanksModel;

public class RanksActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<RanksModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranks);

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<RanksModel>();

        for (int i = 0;  i < MyData.names.length; i++){
            data.add(new RanksModel(
                    MyData.names[i],
                    MyData.addresses[i],
                    MyData.prices[i].toString()
            ));
        }

        adapter = new RanksAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        overridePendingTransition(R.transition.exit,R.transition.entry);
    }
}

package view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.modos.ehsan.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToRanksActivty(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RanksActivity.class);
                startActivity(intent);

                overridePendingTransition(R.transition.exit,R.transition.entry);

            }
        });
    }

    public void goToLatestActivty(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LatestActivty.class);
                startActivity(intent);

                overridePendingTransition(R.transition.exit,R.transition.entry);

            }
        });
    }

    public void exit(View view) {
        finish();
        System.exit(0);
    }

    public void gotoSearchActivity(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);

                overridePendingTransition(R.transition.exit,R.transition.entry);

            }
        });
    }
}

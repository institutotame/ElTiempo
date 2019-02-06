package com.nacho.tame.eltiempo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Controller.ServerResponse {

    ForecastAdapter adapter;
    TextView txtTitle;
    TextView txtMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Controller controller = new Controller(this);
        controller.start();

        adapter = new ForecastAdapter(this);

        RecyclerView recycler = findViewById(R.id.recyclerView);
        recycler.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);

        txtTitle = findViewById(R.id.txtTitle);
    }

    @Override
    public void onResponse(Weather weather) {
        adapter.setData(weather.getConsolidated_weather());
        txtTitle.setText(weather.getTitle());
    }
}

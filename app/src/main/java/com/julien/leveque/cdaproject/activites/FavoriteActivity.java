package com.julien.leveque.cdaproject.activites;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.julien.leveque.cdaproject.R;
import com.julien.leveque.cdaproject.adapters.FavoriteAdapter;
import com.julien.leveque.cdaproject.databinding.ActivityFavoriteBinding;
import com.julien.leveque.cdaproject.models.City;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {

    private ActivityFavoriteBinding binding;

    private TextView mTextViewMessage;
    private ArrayList<City> mCities;
    private RecyclerView mRecyclerView;
    private FavoriteAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        binding = ActivityFavoriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                View v = LayoutInflater.from(mContext).inflate(R.layout.dialog_add_favorite, null);
                final EditText editTextCity = (EditText) v.findViewById(R.id.edit_text_dialog_city);
                builder.setView(v);
                builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (editTextCity.getText().toString().length() > 0) {
                            updateWeatherDataCityName(editTextCity.getText().toString());
                        }
                    }
                });

                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

                builder.create().show();
            }
        });
        Log.d("TAGG", "FavoriteActivity: onCreate()");
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String message = bundle.getString("key","message");
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            mTextViewMessage = findViewById(R.id.text_view_message);
            mTextViewMessage.setText("Message : " + message);
        }

        mCities = new ArrayList<>();

        City city1 = new City("Montréal", "Légères pluies", "22°C", R.drawable.weather_rainy_grey);
        City city2 = new City("New York", "Ensoleillé", "22°C", R.drawable.weather_sunny_grey);
        City city3 = new City("Tours", "Nuageux", "24°C", R.drawable.weather_foggy_grey);
        City city4 = new City("Toulouse", "Pluies modérées", "20°C", R.drawable.weather_rainy_grey);
        mCities.add(city1);
        mCities.add(city2);
        mCities.add(city3);
        mCities.add(city4);
        mCities.add(city1);
        mCities.add(city2);
        mCities.add(city3);
        mCities.add(city4);
        mCities.add(city1);
        mCities.add(city2);
        mCities.add(city3);
        mCities.add(city4);
        mCities.add(city1);
        mCities.add(city2);
        mCities.add(city3);
        mCities.add(city4);
        mCities.add(city1);
        mCities.add(city2);
        mCities.add(city3);
        mCities.add(city4);
        mCities.add(city1);
        mCities.add(city2);
        mCities.add(city3);
        mCities.add(city4);
        mCities.add(city1);
        mCities.add(city2);
        mCities.add(city3);
        mCities.add(city4);
        mCities.add(city1);
        mCities.add(city2);
        mCities.add(city3);
        mCities.add(city4);
        mCities.add(city1);
        mCities.add(city2);
        mCities.add(city3);
        mCities.add(city4);
        mCities.add(city1);
        mCities.add(city2);
        mCities.add(city3);
        mCities.add(city4);



        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_favorite);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new FavoriteAdapter(this, mCities);
        mRecyclerView.setAdapter(mAdapter);
        Log.d("TAG", "onCreate: ");

    }
    public void updateWeatherDataCityName(final String cityName) {
        City city = new City(cityName, "Ensoleillé", "28°C", R.drawable.weather_sunny_grey);
        mCities.add(0, city);
        mAdapter.notifyDataSetChanged();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        Log.d("TAGG", "FavoriteActivity: onDestroy()");
    }
}
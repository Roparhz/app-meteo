package com.julien.leveque.cdaproject.activites;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
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

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class FavoriteActivity extends AppCompatActivity {

    private ActivityFavoriteBinding binding;

    private TextView mTextViewMessage;
    private ArrayList<City> mCities;
    private RecyclerView mRecyclerView;
    private FavoriteAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context mContext;
    private City mCurrentCity;
    private TextView mTextViewCityName;
    private TextView mTextViewCityDescription;
    private TextView mTextViewCityTemperature;

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
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String message = bundle.getString("key","message");
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            mTextViewMessage = findViewById(R.id.text_view_message);
            mTextViewMessage.setText("Message : " + message);
        }

        mCities = new ArrayList<>();






        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_favorite);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new FavoriteAdapter(this, mCities);
        mRecyclerView.setAdapter(mAdapter);
        Log.d("TAG", "onCreate: ");

    }

    public void updateUi(String strJson){
        try {
            City city = new City(strJson);
            mCities.add(city);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


    }
    public void updateWeatherDataCityName(final String cityName) {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=01897e497239c8aff78d9b8538fb24ea&units=metric&lang=fr")
                .build();
        client.newCall(request).enqueue(new Callback() {
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String strJson = response.body().string();
                runOnUiThread(new Runnable() {
                    public void run() {
                        updateUi(strJson);
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }
        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        Log.d("TAGG", "FavoriteActivity: onDestroy()");
    }
}
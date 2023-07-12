package com.julien.leveque.cdaproject.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.julien.leveque.cdaproject.R;
import com.julien.leveque.cdaproject.models.City;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewCityName;
    private TextView mTextViewCityDescription;
    private TextView mTextViewCityTemperature;
    private EditText mTextViewMessage;
    private FloatingActionButton mFloatingButtonFavorite;
    private Context mContext;
    private City mCurrentCity;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        RelativeLayout relativeLayout = findViewById(R.id.relative_layout);

        if (networkInfo != null && networkInfo.isConnected()) {
            relativeLayout.setVisibility(RelativeLayout.VISIBLE);
            Log.d("NIKTAM", "internet ok");
        } else {
            relativeLayout.setVisibility(RelativeLayout.INVISIBLE);
            Log.d("NIKTAM", "internet ko");
        }

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.openweathermap.org/data/2.5/weather?lat=47.390026&lon=0.688891&appid=01897e4\n" +
                        "97239c8aff78d9b8538fb24ea&units=metric&lang=fr")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String strJson = response.body().string();
                runOnUiThread(new Runnable() {
                    public void run() {
                        updateUi(strJson);
                    }
                });
                Log.d("TAG", "onResponse: " + strJson);
            }
        });



        mFloatingButtonFavorite = (FloatingActionButton) findViewById(R.id.floating_action_button_favorite);
        mTextViewCityName =  findViewById(R.id.text_view_city_name);
        mTextViewCityName.setText(R.string.city_name);

        mTextViewCityDescription = findViewById(R.id.text_view_city_description);
        mTextViewCityDescription.setText(R.string.city_desc);

        mTextViewCityTemperature = findViewById(R.id.text_view_city_temperature);
        mTextViewCityTemperature.setText(R.string.city_temp);
        mFloatingButtonFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, FavoriteActivity.class);
                startActivity(intent);
            }
        });

        Log.d("TAGG", "MainActivity: onCreate()");

        mTextViewMessage = findViewById(R.id.edit_text_message);

    }
    public void updateUi(String strJson){
        try {
            mCurrentCity = new City(strJson);
            mTextViewCityName.setText(mCurrentCity.mName);
            mTextViewCityDescription.setText(mCurrentCity.mDescription);
            mTextViewCityTemperature.setText(mCurrentCity.mTemperature);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAGG", "MainActivity: onDestroy()");
    }
}
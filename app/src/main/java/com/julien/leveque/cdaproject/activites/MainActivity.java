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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.julien.leveque.cdaproject.R;
import com.julien.leveque.cdaproject.activites.FavoriteActivity;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewCityName;
    private EditText mTextViewMessage;
    private FloatingActionButton mFloatingButtonFavorite;
    private Context mContext;

    private OkHttpClient mOkHttpClient;


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
            Log.d("TAG", "internet ok");
        } else {
            relativeLayout.setVisibility(RelativeLayout.INVISIBLE);
            Log.d("TEST", "internet ko");
        }

        mFloatingButtonFavorite = (FloatingActionButton) findViewById(R.id.floating_action_button_favorite);
        mTextViewCityName =  findViewById(R.id.text_view_city_name);
        mTextViewCityName.setText(R.string.city_name);
        Toast.makeText(this, "New York", Toast.LENGTH_SHORT).show();

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAGG", "MainActivity: onDestroy()");
    }
}
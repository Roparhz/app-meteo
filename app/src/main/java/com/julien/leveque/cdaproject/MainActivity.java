package com.julien.leveque.cdaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewCityName;
    private EditText mTextViewMessage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        mTextViewCityName =  findViewById(R.id.text_view_city_name);
        mTextViewCityName.setText(R.string.city_name);
        Toast.makeText(this, "New York", Toast.LENGTH_SHORT).show();


        Button button = findViewById(R.id.button_fav2);
        button.setOnClickListener( view -> {
            Toast.makeText(this, "clic sur le bouton 2", Toast.LENGTH_SHORT).show();
        });
        Log.d("TAGG", "MainActivity: onCreate()");

        mTextViewMessage = findViewById(R.id.edit_text_message);
    }


    public void clickBtn1(View view){
        Button button = (Button) view;

        String message;
        if (button.getId() == R.id.button_fav1) {
            message = "Clic sur Bouton 1";
        } else if (button.getId() == R.id.button_fav3) {
            message = "Clic sur Bouton 3";
            Intent intent = new Intent(this, FavoriteActivity.class);
            intent.putExtra("key", mTextViewMessage.getText().toString());
            startActivity(intent);
        } else {
            message = "Clic sur un bouton inconnu";
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAGG", "MainActivity: onDestroy()");
    }
}
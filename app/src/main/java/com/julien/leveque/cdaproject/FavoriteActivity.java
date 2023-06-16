package com.julien.leveque.cdaproject;

import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.julien.leveque.cdaproject.databinding.ActivityFavoriteBinding;

public class FavoriteActivity extends AppCompatActivity {

    private ActivityFavoriteBinding binding;

    private TextView mTextViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFavoriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        Log.d("TAGG", "FavoriteActivity: onDestroy()");
    }
}
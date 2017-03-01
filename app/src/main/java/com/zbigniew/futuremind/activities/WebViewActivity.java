package com.zbigniew.futuremind.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.zbigniew.futuremind.R;
import com.zbigniew.futuremind.fragments.WebViewActivityFragment;

public class WebViewActivity extends AppCompatActivity {

    private WebViewActivityFragment webViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        webViewFragment = (WebViewActivityFragment) getSupportFragmentManager().findFragmentById(R.id.web_view_fragment);
        Bundle extras = getIntent().getExtras();
        String url = null;

        if (extras != null) {
            url = extras.getString("url");
        }

        webViewFragment.loadWebsite(url);
    }

}

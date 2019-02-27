package com.example.nabab.sailorlondon;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private ProgressBar progressBar;
    private TextView noIntennetConnectionTv;
    private Button noInternetConnectionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webViewId);
        progressBar = findViewById(R.id.progressBarId);
        noIntennetConnectionTv = findViewById(R.id.noIntennetConnectionTvId);
        noInternetConnectionBtn = findViewById(R.id.noInternetConnectionBtnId);

        if (isNetworkConnected()){
            webView.loadUrl("http://www.sailorlondon.co.uk");
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setDisplayZoomControls(true);
            webView.getSettings().setLoadsImagesAutomatically(true);
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webView.setWebViewClient(new MyWebViewClient());

        } else {
            noIntennetConnectionTv.setVisibility(View.VISIBLE);
            noInternetConnectionBtn.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);

        }

        noInternetConnectionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });
    }

    public class MyWebViewClient extends WebViewClient{
        @Override
        public void onPageFinished(WebView view, String url) {
            progressBar.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}

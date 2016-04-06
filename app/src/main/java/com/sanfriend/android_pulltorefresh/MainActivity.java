package com.sanfriend.android_pulltorefresh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sanfriend.ptr.OnRefreshListener;
import com.sanfriend.ptr.PullToRefreshLayout;


public class MainActivity extends AppCompatActivity {
    private WebView mWebView;
    private PullToRefreshLayout mPtr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mPtr = (PullToRefreshLayout)findViewById(R.id.ptrLayout);
        mWebView = (WebView)findViewById(R.id.ptr_body);
        mWebView.loadUrl("https://sanfriend.com/ptr.html");
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                if (mPtr != null && mPtr.isRefreshing()) mPtr.setRefreshing(false);
            }
        });

        // first-time refresh
        mWebView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtr.setRefreshing(true);
            }
        }, 10);

        mPtr.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                mWebView.reload();
            }
        });
    }

}

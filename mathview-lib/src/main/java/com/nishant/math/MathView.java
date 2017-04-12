
package com.nishant.math;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.File;

public final class MathView extends WebView {
  private String text;
  private static final String TAG = MathView.class.getSimpleName();
  private volatile boolean pageLoaded;

  public MathView(Context context) {
    super(context);
    init(context);
  }

  public MathView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  @SuppressLint("SetJavaScriptEnabled")
  private void init(Context context) {
    setBackgroundColor(Color.TRANSPARENT);
    this.text = "";
    this.pageLoaded = false;

    // enable javascript
    getSettings().setLoadWithOverviewMode(true);
    getSettings().setJavaScriptEnabled(true);

    // caching
    File dir = context.getCacheDir();
    if (!dir.exists()) {
      Log.d(TAG, "directory does not exist");
      boolean mkdirsStatus = dir.mkdirs();
      if (!mkdirsStatus) {
        Log.e(TAG, "directory creation failed");
      }
    }
    getSettings().setAppCachePath(dir.getPath());
    getSettings().setAppCacheEnabled(true);
    getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

    // disable click
    setClickable(false);
    setLongClickable(false);
    getSettings().setUseWideViewPort(true);
    loadUrl("file:///android_asset/www/MathTemplate.html");
    setWebViewClient(new WebViewClient() {
      @Override
      public void onPageFinished(WebView view, String url) {
        pageLoaded = true;
        loadUrl("javascript:showFormula('" + MathView.this.text  + "')");
        super.onPageFinished(view, url);
      }
    });
  }

  public void setText(String text) {
    this.text = text;
    if (pageLoaded) {
      loadUrl("javascript:showFormula('" + MathView.this.text  + "')");
    } else {
      Log.e(TAG, "Page is not loaded yet.");
    }
  }

  public String getText() {
    return text.substring(1, text.length() - 1);
  }
}

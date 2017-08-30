package my.hhx.com.newpager.modules.ithome.mvp;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.nineoldandroids.view.ViewHelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import my.hhx.com.newpager.R;
import my.hhx.com.newpager.modules.WebPhotoActivity;
import my.hhx.com.newpager.util.ITHomeUtils;
import my.hhx.com.newpager.util.WebUtil;


public class ItArticleActivity extends AppCompatActivity implements ObservableScrollViewCallbacks, ItArticleContract.View {


    private View mFlexibleSpaceView;
    private View mToolbarView;
    private TextView mTitleView;
    private int mFlexibleSpaceHeight;
    private View mWebViewContainer;
    private String mTitle;
    private String mNewsId;
    private WebView mWebView;
    private ItArticlePresenter itArticlePresenter = new ItArticlePresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it_article);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setTitle(null);
        mTitle = getIntent().getStringExtra("title");
        mNewsId = getIntent().getStringExtra("newsId");
        mNewsId = ITHomeUtils.getSplitNewsId(mNewsId);
        initView();
        initweb();
        itArticlePresenter.loadArticle(mNewsId);


    }

    private void initweb() {
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setTextZoom(110);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.addJavascriptInterface(new ItJsInterface(), "itArticleActivity");
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView webView, String s) {
                Log.e("startweb","onPageFinished");
                mWebView.loadUrl("javascript:(function(){" +
                        "var objs = document.getElementsByTagName(\"img\"); " +
                        "for(var i=0;i<objs.length;i++)  " +
                        "{"
                        + "    objs[i].onclick=function()  " +
                        "    {  "
                        + "        window.itArticleActivity.startWebPhotoActivity(this.src);  " +
                        "    }  " +
                        "}" +
                        "})()");

                super.onPageFinished(webView, s);
            }
        });

    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        updateFlexibleSpaceText(scrollY);
    }

    @Override
    public void onDownMotionEvent() {
    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
    }

    private void updateFlexibleSpaceText(final int scrollY) {
        ViewHelper.setTranslationY(mFlexibleSpaceView, -scrollY);
        int adjustedScrollY = (int) ScrollUtils.getFloat(scrollY, 0, mFlexibleSpaceHeight);


        // Special logic for WebView.
        adjustTopMargin(mWebViewContainer, adjustedScrollY <= mFlexibleSpaceHeight ? 0 : mFlexibleSpaceHeight + getActionBarSize());

        float maxScale = (float) (mFlexibleSpaceHeight - mToolbarView.getHeight()) / mToolbarView.getHeight();
        float scale = maxScale * ((float) mFlexibleSpaceHeight - adjustedScrollY) / mFlexibleSpaceHeight;

        ViewHelper.setPivotX(mTitleView, 0);
        ViewHelper.setPivotY(mTitleView, 0);
        ViewHelper.setScaleX(mTitleView, 1);
        ViewHelper.setScaleY(mTitleView, 1);
        int maxTitleTranslationY = mToolbarView.getHeight() + mFlexibleSpaceHeight - (int) (mTitleView.getHeight() * (1 + scale));
        int titleTranslationY = (int) (maxTitleTranslationY * ((float) mFlexibleSpaceHeight - adjustedScrollY) / mFlexibleSpaceHeight);
        ViewHelper.setTranslationY(mTitleView, titleTranslationY);
    }

    private void adjustTopMargin(View view, int topMargin) {
        final FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();

        if (layoutParams.topMargin == topMargin) {
            return;
        }

        layoutParams.topMargin = topMargin;

        view.setLayoutParams(layoutParams);
    }

    protected int getActionBarSize() {
        TypedValue typedValue = new TypedValue();
        int[] textSizeAttr = new int[]{R.attr.actionBarSize};
        int indexOfAttrTextSize = 0;
        TypedArray a = obtainStyledAttributes(typedValue.data, textSizeAttr);
        int actionBarSize = a.getDimensionPixelSize(indexOfAttrTextSize, -1);
        a.recycle();
        return actionBarSize;
    }

    public void initView() {
        mFlexibleSpaceView = findViewById(R.id.flexible_space);
        mTitleView = (TextView) findViewById(R.id.it_article_title);
        mTitleView.setText(mTitle);

        mToolbarView = findViewById(R.id.toolbar);

        mWebViewContainer = findViewById(R.id.webViewContainer);

        final ObservableScrollView scrollView = (ObservableScrollView) findViewById(R.id.scroll);
        scrollView.setScrollViewCallbacks(this);
        mWebView = (WebView) findViewById(R.id.it_article_webView);

        mFlexibleSpaceHeight = getResources().getDimensionPixelSize(R.dimen.flexible_space_height);

        int flexibleSpaceAndToolbarHeight = mFlexibleSpaceHeight + getActionBarSize();

        final FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mWebView.getLayoutParams();
        layoutParams.topMargin = flexibleSpaceAndToolbarHeight;
        mWebView.setLayoutParams(layoutParams);

        mFlexibleSpaceView.getLayoutParams().height = flexibleSpaceAndToolbarHeight;

        ScrollUtils.addOnGlobalLayoutListener(mTitleView, new Runnable() {
            @Override
            public void run() {
                updateFlexibleSpaceText(scrollView.getCurrentScrollY());
            }
        });


    }


    @Override
    public void loadFail() {

    }

    private String readJS() {
        try {
            InputStream inStream = getAssets().open("web.js");
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = inStream.read(bytes)) > 0) {
                outStream.write(bytes, 0, len);
            }
            return outStream.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void loadSucess(IthomeContentItem ithomeContentItem) {
        String data = WebUtil.buildHtmlForIt(ithomeContentItem.getDetail(), false);
        mWebView.loadDataWithBaseURL(WebUtil.BASE_URL, data, WebUtil.MIME_TYPE, WebUtil.ENCODING, WebUtil.IT_FAIL_URL);
    }

    public class ItJsInterface {
        @JavascriptInterface
        public void startWebPhotoActivity(String imageUrl) {
            Log.e("startweb","sucdess");
            Intent intent = new Intent(ItArticleActivity.this, WebPhotoActivity.class);
            intent.putExtra("image_url", imageUrl);
            startActivity(intent);
        }
    }
}

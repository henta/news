package my.hhx.com.newpager.modules.zhihu.mvp;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ObservableWebView;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.melnykov.fab.FloatingActionButton;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

import butterknife.BindView;
import butterknife.ButterKnife;
import my.hhx.com.newpager.R;
import my.hhx.com.newpager.util.WebUtil;

public class ZhihuArticleActivity extends AppCompatActivity implements ZhihuArticleContract.View, ObservableScrollViewCallbacks {


    @BindView(R.id.zhihu_article_iv)
    ImageView zhihuArticleIv;
    @BindView(R.id.zhihu_article_overlay)
    View zhihuArticleOverlay;
    @BindView(R.id.zhihu_article_webView)
    ObservableWebView zhihuArticleWebView;
    @BindView(R.id.zhihu_article_scroll)
    ObservableScrollView zhihuArticleScroll;
    @BindView(R.id.zhihu_article_fab)
    FloatingActionButton zhihuArticleFab;
    @BindView(R.id.zhihu_article_title_tv)
    TextView zhihuArticleTitleTv;
   
    private int mActionBarSize;
    private int mFlexibleSpaceShowFabOffset;
    private int mFlexibleSpaceImageHeight;
    private int zhihuArticleFabMargin;
    private boolean zhihuArticleFabIsShown;
    private ZhihuArticlePresenter zhihuArticlePresenter = new ZhihuArticlePresenter(this);
    private static final float MAX_TEXT_SCALE_DELTA = 0.3f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ButterKnife.bind(this);
        mFlexibleSpaceImageHeight = getResources().getDimensionPixelSize(R.dimen.flexible_space_image_height);
        mFlexibleSpaceShowFabOffset = getResources().getDimensionPixelSize(R.dimen.flexible_space_show_fab_offset);
        mActionBarSize = getActionBarSize();
        zhihuArticleScroll.setScrollViewCallbacks(this);
        setTitle(null);
        zhihuArticleFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ZhihuArticleActivity.this, "FAB is clicked", Toast.LENGTH_SHORT).show();
            }
        });
        zhihuArticleFabMargin = getResources().getDimensionPixelSize(R.dimen.margin_standard);
        ViewHelper.setScaleX(zhihuArticleFab, 0);
        ViewHelper.setScaleY(zhihuArticleFab, 0);

        ScrollUtils.addOnGlobalLayoutListener(zhihuArticleWebView, new Runnable() {
            @Override
            public void run() {
                onScrollChanged(0,false,false);
                zhihuArticleWebView.scrollTo(0, mFlexibleSpaceImageHeight - mActionBarSize);

                // If you'd like to start from scrollY == 0, don't write like this:
                //zhihuArticleWebView.scrollTo(0, 0);
                // The initial scrollY is 0, so it won't invoke onScrollChanged().
                // To do this, use the following:
                //onScrollChanged(0, false, false);

                // You can also achieve it with the following codes.
                // This causes scroll change from 1 to 0.
                //zhihuArticleWebView.scrollTo(0, 1);
                //zhihuArticleWebView.scrollTo(0, 0);
            }
        });
        WebSettings webSettings = zhihuArticleWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        loadArticle();
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

    private void loadArticle() {
        ZhihuDaily.StoriesBean storiesBean = (ZhihuDaily.StoriesBean) getIntent().getSerializableExtra("article");
        zhihuArticleTitleTv.setText(storiesBean.getTitle());
        zhihuArticlePresenter.loadArticle(storiesBean);
    }


    @Override
    public void loadFail() {

    }

    @Override
    public void loadSucess(ZhihuArticle zhihuArticle) {
        Glide.with(this)
                .load(zhihuArticle.getImage())
                .into(zhihuArticleIv);
        String body = WebUtil.buildHtmlWithCss(zhihuArticle.getBody(), zhihuArticle.getCss(), false);
        zhihuArticleWebView.loadDataWithBaseURL(WebUtil.BASE_URL, body, WebUtil.MIME_TYPE, WebUtil.ENCODING, WebUtil.ZHIHU_FAIL_URL);

    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        // Translate overlay and image
        float flexibleRange = mFlexibleSpaceImageHeight - mActionBarSize;
        int minOverlayTransitionY = mActionBarSize - zhihuArticleOverlay.getHeight();
        ViewHelper.setTranslationY(zhihuArticleOverlay, ScrollUtils.getFloat(-scrollY, minOverlayTransitionY, 0));
        ViewHelper.setTranslationY(zhihuArticleIv, ScrollUtils.getFloat(-scrollY / 2, minOverlayTransitionY, 0));

        // Change alpha of overlay
        ViewHelper.setAlpha(zhihuArticleOverlay, ScrollUtils.getFloat((float) scrollY / flexibleRange, 0, 1));

        // Scale title text
        float scale = 1 + ScrollUtils.getFloat(0, 0, MAX_TEXT_SCALE_DELTA);
        ViewHelper.setPivotX(zhihuArticleTitleTv, 0);
        ViewHelper.setPivotY(zhihuArticleTitleTv, 0);
        ViewHelper.setScaleX(zhihuArticleTitleTv, scale);
        ViewHelper.setScaleY(zhihuArticleTitleTv, scale);

        // Translate title text
        int maxTitleTranslationY = (int) (mFlexibleSpaceImageHeight - zhihuArticleTitleTv.getHeight() * scale);
        int titleTranslationY = maxTitleTranslationY - scrollY;
        ViewHelper.setTranslationY(zhihuArticleTitleTv, titleTranslationY);

        // Translate FAB
        int maxFabTranslationY = mFlexibleSpaceImageHeight - zhihuArticleFab.getHeight() / 2;
        float fabTranslationY = ScrollUtils.getFloat(
                -scrollY + mFlexibleSpaceImageHeight - zhihuArticleFab.getHeight() / 2,
                mActionBarSize - zhihuArticleFab.getHeight() / 2,
                maxFabTranslationY);

        ViewHelper.setTranslationX(zhihuArticleFab, zhihuArticleOverlay.getWidth() - zhihuArticleFabMargin - zhihuArticleFab.getWidth());
        ViewHelper.setTranslationY(zhihuArticleFab, fabTranslationY);


        // Show/hide FAB
        if (fabTranslationY < mFlexibleSpaceShowFabOffset) {
            hideFab();
        } else {
            showFab();
        }
    }

    @Override
    public void onDownMotionEvent() {
    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
    }

    private void showFab() {
        if (!zhihuArticleFabIsShown) {
            ViewPropertyAnimator.animate(zhihuArticleFab).cancel();
            ViewPropertyAnimator.animate(zhihuArticleFab).scaleX(1).scaleY(1).setDuration(200).start();
            zhihuArticleFabIsShown = true;
        }
    }

    private void hideFab() {
        if (zhihuArticleFabIsShown) {
            ViewPropertyAnimator.animate(zhihuArticleFab).cancel();
            ViewPropertyAnimator.animate(zhihuArticleFab).scaleX(0).scaleY(0).setDuration(200).start();
            zhihuArticleFabIsShown = false;
        }
    }
}

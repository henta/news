package my.hhx.com.newpager.modules;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import my.hhx.com.newpager.R;
import my.hhx.com.newpager.util.GlideImageLoader;

public class WebPhotoActivity extends AppCompatActivity {

    @BindView(R.id.web_photo_view)
    PhotoView webPhotoView;
    private String imgUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_photo);
        ButterKnife.bind(this);
        imgUrl = getIntent().getStringExtra("image_url");
        Glide.with(this).load(imgUrl).into(webPhotoView);
    }
}

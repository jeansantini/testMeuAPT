package com.testmeuapt.ui.shotDetail;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.testmeuapt.R;
import com.testmeuapt.domain.model.Shot;
import com.testmeuapt.ui.BaseActivity;
import com.testmeuapt.ui.dialog.MessageDialog;
import com.testmeuapt.ui.shotDetail.useCase.GetShotData;
import com.testmeuapt.ui.shots.ShotsPresenter;
import com.testmeuapt.ui.shots.useCase.GetShots;
import com.testmeuapt.util.EspressoIdlingResource;
import com.testmeuapt.util.Utils;
import com.testmeuapt.util.ViewAnimationUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShotDetailActivity extends BaseActivity implements ShotDetailContract.View {

    public static final String EXTRA_ID_SHOT = "ID_SHOT";
    private ShotDetailContract.Presenter mPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.iv_image)
    ImageView ivImage;

    @BindView(R.id.tv_detail_title)
    TextView tvTitle;

    @BindView(R.id.tv_detail_description)
    TextView tvDescription;

    @BindView(R.id.tv_detail_created_at)
    TextView tvCreatedAt;

    @BindView(R.id.tv_detail_views_count)
    TextView tvViewsCount;

    @BindView(R.id.tv_detail_comments_count)
    TextView tvCommentsCount;

    @BindView(R.id.ll_content_views)
    LinearLayout llContentViews;

    @BindView(R.id.ll_content_comments)
    LinearLayout llContentComments;

    @BindView(R.id.content_shot_data)
    LinearLayout llContentShotData;


    public final static Intent getStartIntent(@NonNull final Context context,
                                              final long id) {
        Intent intent = new Intent(context, ShotDetailActivity.class);
        intent.putExtra(EXTRA_ID_SHOT, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shot_detail);
        mPresenter = new ShotDetailPresenter(this, new GetShotData(this), getIntent().getLongExtra(EXTRA_ID_SHOT,0));
        ButterKnife.bind(this);
        setupToolbar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle(R.string.shot_detail_title);
        ab.setDisplayHomeAsUpEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_48dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void setPresenter(@NonNull final ShotDetailContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showShotData(Shot shot) {
        Picasso.with(this)
                .load(shot.getImages().getNormal())
                .into(ivImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        ViewAnimationUtil.expand(ivImage);
                    }

                    @Override
                    public void onError() {

                    }
                });

        tvTitle.setText(shot.getTitle());
        tvDescription.setText(Html.fromHtml(shot.getDescription()).toString().replaceAll("\n", "").trim());
        tvCreatedAt.setText(Utils.formatDate(shot.getCreatedAt()));
        if(!Utils.stringIsEmpty(shot.getCommentsCount()+"")) {
            llContentComments.setVisibility(View.VISIBLE);
            tvViewsCount.setText(shot.getViewsCount()+ " " +getResources().getString(R.string.views));
        }
        if(!Utils.stringIsEmpty(shot.getViewsCount()+"")) {
            llContentViews.setVisibility(View.VISIBLE);
            tvCommentsCount.setText(shot.getCommentsCount()+ " " + getResources().getString(R.string.comments));
        }

        // Start animation
        ViewAnimationUtil.expand(llContentShotData);
    }

    @Override
    public void showLoadShotDataError() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        MessageDialog messageDialog = MessageDialog.newInstance(
                getResources().getString(R.string.msg_load_shot_detail_error), null, MessageDialog.TYPE_ERROR);
        messageDialog.setCancelable(false);
        messageDialog.show(ft, "dialog");
    }

    @Override
    public String getMsgLoadingShot() {
        return getResources().getString(R.string.msg_loading_shot);
    }

    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }
}

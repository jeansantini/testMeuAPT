package com.testmeuapt.ui.shots;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.testmeuapt.R;
import com.testmeuapt.domain.model.Shot;
import com.testmeuapt.ui.BaseActivity;
import com.testmeuapt.ui.dialog.MessageDialog;
import com.testmeuapt.ui.shotDetail.ShotDetailActivity;
import com.testmeuapt.ui.shots.adapter.ShotsAdapter;
import com.testmeuapt.ui.shots.useCase.GetShots;
import com.testmeuapt.util.EspressoIdlingResource;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ShotsActivity extends BaseActivity implements ShotsContract.View, ShotsAdapter.ShotClickListener {

    private ShotsContract.Presenter mPresenter;

    @BindView(R.id.rv_shots)
    RecyclerView rvShots;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private ShotsAdapter shotsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shots);
        ButterKnife.bind(this);
        mPresenter = new ShotsPresenter(this, new GetShots(this));
        setupToolbar();
        setupList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle(R.string.shots_title);
        ab.setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public void setPresenter(@NonNull final ShotsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    private void setupList() {
        shotsAdapter = new ShotsAdapter(this);
        rvShots.setLayoutManager(new LinearLayoutManager(this));
        rvShots.setAdapter(shotsAdapter);
        shotsAdapter.setmShotClickListener(this);
    }

    @Override
    public void showShots(final List<Shot> shots) {
        shotsAdapter.setShots(shots);
    }

    @Override
    public void showLoadShotsError() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        MessageDialog messageDialog = MessageDialog.newInstance(
                getResources().getString(R.string.msg_load_shots_error), null, MessageDialog.TYPE_ERROR);
        messageDialog.setCancelable(false);
        messageDialog.show(ft, "dialog");
    }

    @Override
    public String getMsgLoadingShots() {
        return getResources().getString(R.string.msg_loading_shots);
    }

    @Override
    public void shotClick(final long id) {
        startActivity(ShotDetailActivity.getStartIntent(this, id));
    }

    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }
}

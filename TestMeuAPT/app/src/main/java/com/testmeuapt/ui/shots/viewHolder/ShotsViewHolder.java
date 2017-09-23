package com.testmeuapt.ui.shots.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.testmeuapt.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jsantini on 22/09/17.
 */

public class ShotsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.rl_item_shot)
    public RelativeLayout rlItemShot;
    @BindView(R.id.tv_shot_title)
    public TextView tvShotTitle;
    @BindView(R.id.tv_shot_created_at)
    public TextView tvShotCreatedAt;
    @BindView(R.id.tv_shot_views_count)
    public TextView tvShotViewsCount;
    @BindView(R.id.iv_shot_teaser)
    public ImageView ivShotTeaser;

    public ShotsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}

package com.testmeuapt.ui.shots.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.testmeuapt.R;
import com.testmeuapt.domain.model.Shot;
import com.testmeuapt.ui.shots.viewHolder.ShotsViewHolder;
import com.testmeuapt.util.Utils;

import java.util.List;

/**
 * Created by jsantini on 22/09/17.
 */

public class ShotsAdapter extends RecyclerView.Adapter<ShotsViewHolder> {
    private List<Shot> shots;
    private Context mContext;
    private ShotClickListener mShotClickListener;

    public ShotsAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ShotsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shot_item_layout, parent, false);
        return new ShotsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ShotsViewHolder holder, final int position) {
        final Shot shot = shots.get(position);
        holder.tvShotTitle.setText(shot.getTitle());
        holder.tvShotViewsCount.setText(shot.getViewsCount() + " " + mContext.getResources().getString(R.string.views));
        holder.tvShotCreatedAt.setText(Utils.formatDate(shot.getCreatedAt()));
        if (mShotClickListener != null) {
            holder.rlItemShot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mShotClickListener.shotClick(shot.getId());
                }
            });
        }
        Picasso.with(mContext)
                .load(shot.getImages().getNormal())
                .into(holder.ivShotTeaser);
    }

    @Override
    public int getItemCount() {
        if(shots == null) {
            return 0;
        }
        return shots.size();
    }

    public void setShots(final List<Shot> shots) {
        this.shots = shots;
        notifyDataSetChanged();
    }

    public void setmShotClickListener(final ShotClickListener shotClickListener) {
        this.mShotClickListener = shotClickListener;
    }

    public interface ShotClickListener {
        void shotClick(long id);
    }
}

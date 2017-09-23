package com.testmeuapt.ui.shotDetail;

import android.support.annotation.NonNull;
import android.util.Log;

import com.testmeuapt.domain.model.Shot;
import com.testmeuapt.ui.UseCase;
import com.testmeuapt.ui.shotDetail.useCase.GetShotData;

import retrofit2.Response;

/**
 * Created by jsantini on 22/09/17.
 */

public class ShotDetailPresenter implements ShotDetailContract.Presenter {

    private final ShotDetailContract.View mShotDetailView;
    private final GetShotData mGetShotData;
    private final long idShot;

    public ShotDetailPresenter(@NonNull final ShotDetailContract.View shotDetailView,
                               @NonNull final GetShotData getShotData,
                               final long idShot) {
        this.mShotDetailView = shotDetailView;
        this.mGetShotData = getShotData;
        this.idShot = idShot;
    }

    @Override
    public void start() {
        getShotData();
    }

    private void getShotData() {
        mShotDetailView.showOrHideLoading(true, mShotDetailView.getMsgLoadingShot());
        mGetShotData.getShotData(idShot, new UseCase.UseCaseCallback() {
            @Override
            public void onSuccess(Response response) {
                Shot shot = (Shot) response.body();
                // Verifica se view a é ser capaz de receber atualizações
                if(!mShotDetailView.isActive()) {
                    return;
                }
                mShotDetailView.showOrHideLoading(false, null);
                mShotDetailView.showShotData(shot);
            }

            @Override
            public void onError() {
                mShotDetailView.showOrHideLoading(false, null);
                mShotDetailView.showLoadShotDataError();
            }

            @Override
            public void onGenericError() {
                mShotDetailView.showOrHideLoading(false, null);
                mShotDetailView.showGenericError();
            }

            @Override
            public void onConnectionError() {
                mShotDetailView.showConnectionError();
            }
        });
    }
}

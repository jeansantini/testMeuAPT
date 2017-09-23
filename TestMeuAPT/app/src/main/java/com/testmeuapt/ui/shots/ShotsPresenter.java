package com.testmeuapt.ui.shots;

import android.support.annotation.NonNull;
import android.util.Log;

import com.testmeuapt.domain.model.Shot;
import com.testmeuapt.ui.UseCase;
import com.testmeuapt.ui.shots.useCase.GetShots;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Response;

/**
 * Created by jsantini on 22/09/17.
 */

public class ShotsPresenter implements ShotsContract.Presenter {

    private final ShotsContract.View mShotsView;
    private final GetShots mGetShots;
    private final int LIMIT_SHOTS_RESULT = 30;

    public ShotsPresenter(@NonNull final ShotsContract.View shotsView,
                          @NonNull final GetShots getShots) {
        this.mShotsView = shotsView;
        this.mGetShots = getShots;
        mShotsView.setPresenter(null);
    }

    @Override
    public void start() {
        loadShots();
    }

    private void loadShots() {
        mShotsView.showOrHideLoading(true, mShotsView.getMsgLoadingShots());
        mGetShots.loadShots(LIMIT_SHOTS_RESULT, new UseCase.UseCaseCallback() {
            @Override
            public void onSuccess(Response response) {
                List<Shot> shots = (List<Shot>) response.body();
                Collections.sort(shots, Collections.reverseOrder(new Comparator<Shot>() {
                    @Override
                    public int compare(Shot c1, Shot c2) {
                        return c1.getCreatedAt().compareTo(c2.getCreatedAt());
                    }
                }));
                // Verifica se view a é ser capaz de receber atualizações
                if(!mShotsView.isActive()) {
                    return;
                }
                mShotsView.showOrHideLoading(false, null);
                mShotsView.showShots(shots);
            }

            @Override
            public void onError() {
                mShotsView.showLoadShotsError();
            }

            @Override
            public void onGenericError() {
                mShotsView.showGenericError();
            }

            @Override
            public void onConnectionError() {
                mShotsView.showConnectionError();
            }
        });
    }
}

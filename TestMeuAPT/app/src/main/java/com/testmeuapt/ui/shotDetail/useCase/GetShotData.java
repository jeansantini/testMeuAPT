package com.testmeuapt.ui.shotDetail.useCase;

import android.content.Context;
import android.support.annotation.NonNull;

import com.testmeuapt.domain.model.Shot;
import com.testmeuapt.service.DribbbleApi;
import com.testmeuapt.ui.UseCase;
import com.testmeuapt.util.EspressoIdlingResource;
import com.testmeuapt.util.NetworkUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jsantini on 22/09/17.
 */

public class GetShotData {

    private final Context mContext;

    public GetShotData(@NonNull final Context context) {
        this.mContext = context;
    }

    public void getShotData(final long idShot, final UseCase.UseCaseCallback useCaseCallback) {
        if(NetworkUtil.isConnected()) {
            EspressoIdlingResource.increment();
            DribbbleApi.DribbbleApiContract service = DribbbleApi.getApi(mContext);

            Call<Shot> call = service.getShotData(idShot);
            call.enqueue(new Callback<Shot>() {
                @Override
                public void onResponse(Call<Shot> call, Response<Shot> response) {
                    if(response.isSuccessful()) {
                        useCaseCallback.onSuccess(response);
                    } else {
                        useCaseCallback.onError();
                    }
                    if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                        EspressoIdlingResource.decrement(); // Set app as idle.
                    }
                }

                @Override
                public void onFailure(Call<Shot> call, Throwable t) {
                    if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                        EspressoIdlingResource.decrement(); // Set app as idle.
                    }
                    useCaseCallback.onGenericError();
                }
            });
        } else {
            useCaseCallback.onConnectionError();
        }
    }
}

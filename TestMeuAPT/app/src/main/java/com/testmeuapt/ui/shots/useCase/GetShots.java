package com.testmeuapt.ui.shots.useCase;

import android.content.Context;
import android.support.annotation.NonNull;

import com.testmeuapt.ui.UseCase;
import com.testmeuapt.service.DribbbleApi;
import com.testmeuapt.domain.model.Shot;
import com.testmeuapt.util.EspressoIdlingResource;
import com.testmeuapt.util.NetworkUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jsantini on 22/09/17.
 */



public class GetShots extends UseCase {

    private final Context mContext;

    public GetShots(@NonNull final Context context) {
        this.mContext = context;
    }

    public void loadShots(final int limit, final UseCaseCallback useCaseCallback) {
        if(NetworkUtil.isConnected()) {
            EspressoIdlingResource.increment();
            DribbbleApi.DribbbleApiContract service = DribbbleApi.getApi(mContext);

            Call<List<Shot>> call = service.getShots(limit);

            call.enqueue(new Callback<List<Shot>>() {
                @Override
                public void onResponse(Call<List<Shot>> call, Response<List<Shot>> response) {
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
                public void onFailure(Call<List<Shot>> call, Throwable t) {
                    useCaseCallback.onError();
                    if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                        EspressoIdlingResource.decrement(); // Set app as idle.
                    }
                }
            });
        } else {
            useCaseCallback.onError();
        }
    }
}
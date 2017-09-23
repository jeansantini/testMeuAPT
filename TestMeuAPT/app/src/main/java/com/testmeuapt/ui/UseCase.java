package com.testmeuapt.ui;

import retrofit2.Response;

/**
 * Created by jsantini on 22/09/17.
 */

public abstract class UseCase {

    public interface UseCaseCallback<R> {
        void onSuccess(Response response);
        void onError();
        void onGenericError();
        void onConnectionError();
    }
}

package com.testmeuapt.ui;

/**
 * Created by jsantini on 22/09/17.
 */

public interface BaseView<T> {

    void setPresenter(T presenter);

    void showOrHideLoading(boolean isShowLoading, String msg);

    boolean isActive();

    void showGenericError();

    void showConnectionError();
}

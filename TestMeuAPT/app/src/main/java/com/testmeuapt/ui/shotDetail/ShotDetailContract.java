package com.testmeuapt.ui.shotDetail;

import com.testmeuapt.domain.model.Shot;
import com.testmeuapt.ui.BasePresenter;
import com.testmeuapt.ui.BaseView;

/**
 * Created by jsantini on 22/09/17.
 */

public interface ShotDetailContract {

    interface View extends BaseView<Presenter> {
        void showShotData(Shot shot);

        void showLoadShotDataError();

        String getMsgLoadingShot();
    }

    interface Presenter extends BasePresenter {

    }
}

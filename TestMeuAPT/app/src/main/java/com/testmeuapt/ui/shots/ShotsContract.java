package com.testmeuapt.ui.shots;

import com.testmeuapt.ui.BasePresenter;
import com.testmeuapt.ui.BaseView;
import com.testmeuapt.domain.model.Shot;

import java.util.List;

/**
 * Created by jsantini on 22/09/17.
 */

public interface ShotsContract {

    interface View extends BaseView<Presenter> {

        void showShots(List<Shot> shots);

        void showLoadShotsError();

        String getMsgLoadingShots();
    }

    interface Presenter extends BasePresenter {

    }
}

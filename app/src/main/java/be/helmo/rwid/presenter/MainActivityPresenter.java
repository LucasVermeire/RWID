package be.helmo.rwid.presenter;


import android.os.Handler;

import be.helmo.rwid.view.IMainActivityView;

public class MainActivityPresenter implements IMainActivityPresenter {

    private IMainActivityView view;

    public MainActivityPresenter(IMainActivityView view){
        this.view = view;
    }

    @Override
    public void onCreate() {
        new Handler().postDelayed(() -> view.showList(), view.getTimeOut());
    }
}

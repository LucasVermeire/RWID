package be.helmo.rwid.presenter;


import java.util.List;
import be.helmo.rwid.adapter.AdapterPhotoView;
import be.helmo.rwid.model.PhotoData;
import be.helmo.rwid.view.ITimeLineView;
import be.helmo.rwid.view.MainActivity;

public class TimeLinePresenter implements ITimeLinePresenter {

    private ITimeLineView view;

    public TimeLinePresenter(ITimeLineView view) {
        this.view = view;
    }

    @Override
    public void onCreateTimeLine (int index){

        List<PhotoData> timeLines = MainActivity.getMyAppDataBase().photoDao().getPhotoList(index);

        AdapterPhotoView customAdapterTimeLine = new AdapterPhotoView(view.getContext(),timeLines);
        view.getListPhoto().setAdapter(customAdapterTimeLine);
    }
}


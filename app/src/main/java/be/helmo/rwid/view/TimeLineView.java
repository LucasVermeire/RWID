package be.helmo.rwid.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import be.helmo.rwid.R;
import be.helmo.rwid.presenter.ITimeLinePresenter;
import be.helmo.rwid.presenter.TimeLinePresenter;
import be.helmo.rwid.repository.EventRepo;

public class TimeLineView extends Fragment implements ITimeLineView {

    private ITimeLinePresenter presenter;
    private ListView list;

    public TimeLineView() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.time_line, container, false);

        list = view.findViewById(R.id.listTimeLine);
        TextView titleTL = view.findViewById(R.id.TitleTLEvent);

        EventRepo eventRepo = new EventRepo();

        String id = null;
        if (getArguments() != null) {
            id = getArguments().getString("KEY2");
        }

        presenter = new TimeLinePresenter(this);
        if (id != null) {
            presenter.onCreateTimeLine(Integer.parseInt(id));

            List<String> names = eventRepo.getNames(Integer.parseInt(id));
            titleTL.setText(names.get(0));
        }
        return view;
    }

    @Override
    public ListView getListPhoto(){
        return list;
    }
}

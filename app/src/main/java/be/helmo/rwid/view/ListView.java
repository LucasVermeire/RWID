package be.helmo.rwid.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import be.helmo.rwid.R;
import be.helmo.rwid.presenter.IListPresenter;
import be.helmo.rwid.presenter.ListPresenter;

public class ListView extends Fragment implements IListView {

    private android.widget.ListView list;
    private IListPresenter presenter;
    private View view;

    public ListView(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.listview_main, container, false);
        Button startEvent = view.findViewById(R.id.startEvent);
        list = view.findViewById(R.id.list);

        presenter = new ListPresenter(this);

        presenter.onCreate();

        startEvent.setOnClickListener(v -> {
            if (v.getId() == R.id.startEvent) {
                MainActivity.getFtManager().beginTransaction().replace(R.id.fragment_container, new AddEventView())
                        .addToBackStack(null).commit();
            }
        });

        return view;
    }

    @Override
    public android.widget.ListView getList() {
        return list;
    }

    @Override
    public Context getContext(){
        return view.getContext();
    }

    @Override
    public View getView() {return this.view;}


    public Fragment getMyFragment(){
        return this;
    }
}

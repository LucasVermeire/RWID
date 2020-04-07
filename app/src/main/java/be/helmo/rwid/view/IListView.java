package be.helmo.rwid.view;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

public interface IListView {
    ListView getList();
    Context getContext();
    View getView();
    Fragment getMyFragment();
}

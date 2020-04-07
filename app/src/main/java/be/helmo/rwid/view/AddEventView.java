package be.helmo.rwid.view;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import be.helmo.rwid.R;
import be.helmo.rwid.model.PartyStatut;
import be.helmo.rwid.presenter.AddEventPresenter;
import be.helmo.rwid.presenter.IAddEventPresenter;
import be.helmo.rwid.presenter.TimeLinePresenter;
import be.helmo.rwid.repository.PhotoRepo;

public class AddEventView extends Fragment implements IAddEventView {

    private IAddEventPresenter presenter;
    private EditText nameEvent;
    private TimePicker timeEnd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_event, container, false);

        presenter = new AddEventPresenter(this);

        nameEvent = view.findViewById(R.id.nameEvent);
        timeEnd = view.findViewById(R.id.TIME_END);
        Button addParty = view.findViewById(R.id.addParty);

        addParty.setOnClickListener(v -> {
            if (v.getId() == R.id.addParty) {
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.HOUR, timeEnd.getHour());
                cal.set(Calendar.MINUTE, timeEnd.getMinute());
                presenter.addEvent(nameEvent.getText().toString(), PartyStatut.ON_WAY.toString(), cal);
            }
        });
        return view;
    }

    @Override
    public void setToast(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }
}

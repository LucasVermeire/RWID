package be.helmo.rwid.view;

import android.app.Activity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;
import be.helmo.rwid.R;
import be.helmo.rwid.database.MyAppDataBase;
import be.helmo.rwid.presenter.IMainActivityPresenter;
import be.helmo.rwid.presenter.MainActivityPresenter;


public class MainActivity extends AppCompatActivity implements IMainActivityView {

    private final int SPLASH_SCREEN_TIMEOUT = 1500;
    private IMainActivityPresenter presenter = new MainActivityPresenter(this);
    private static FragmentManager fragmentManager;
    private static MyAppDataBase myAppDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        myAppDataBase = Room.databaseBuilder(getApplicationContext(), MyAppDataBase.class,"rwidDB")
                .allowMainThreadQueries().build();

        presenter.onCreate();
    }

    @Override
    public void showList() {
        if(findViewById(R.id.fragment_container) != null){
            fragmentManager.beginTransaction().add(R.id.fragment_container, new ListView()).commit();
        }
    }

    public int getTimeOut(){
        return SPLASH_SCREEN_TIMEOUT;
    }

    public static FragmentManager getFtManager(){
        return fragmentManager;
    }

    public static MyAppDataBase getMyAppDataBase(){
        return myAppDataBase;
    }
}

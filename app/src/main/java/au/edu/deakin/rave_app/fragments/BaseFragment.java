package au.edu.deakin.rave_app.fragments;

import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

import au.edu.deakin.rave_app.database.DatabaseHandler;
import au.edu.deakin.rave_app.database.SharedPreferencesManager;


@EFragment
public class BaseFragment extends Fragment {

    @Bean
    DatabaseHandler database;

    @Bean
    SharedPreferencesManager sharedPreferences;

    public BaseFragment()
    {
        database = new DatabaseHandler(getActivity());
    }

    @UiThread
    void showMessage(String message)
    {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }
}

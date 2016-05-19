package au.edu.deakin.rave_app.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;

import au.edu.deakin.rave_app.database.DatabaseHandler;
import au.edu.deakin.rave_app.database.SharedPreferencesManager;



@EActivity
public class BaseActivity extends Activity{

    @Bean
    DatabaseHandler database;

    @Bean
    SharedPreferencesManager sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = new DatabaseHandler(this);
    }

    @UiThread
    void showMessage(String message)
    {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

}

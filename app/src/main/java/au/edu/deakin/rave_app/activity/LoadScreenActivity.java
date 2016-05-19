package au.edu.deakin.rave_app.activity;

import android.content.Intent;
import android.os.Handler;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import au.edu.deakin.rave_app.R;


@EActivity(R.layout.loading_activity)
public class LoadScreenActivity extends BaseActivity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1500;

    @AfterViews
    void init()
    {
        new Handler().postDelayed(new Runnable() {
            // Showing splash screen with a timer. This will be useful when you
            // want to show case your app logo / company
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                if(sharedPreferences.getUser() == null) {
                    Intent i = new Intent(LoadScreenActivity.this, SignInActivity_.class);
                    startActivity(i);
                }
                else
                {
                    Intent i = new Intent(LoadScreenActivity.this, MainActivity.class);
                    startActivity(i);
                }
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}

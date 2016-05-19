package au.edu.deakin.rave_app.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import org.androidannotations.annotations.Bean;

import au.edu.deakin.rave_app.R;
import au.edu.deakin.rave_app.database.SharedPreferencesManager;
import au.edu.deakin.rave_app.fragments.FragmentOne;
import au.edu.deakin.rave_app.fragments.FragmentTwo;
import au.edu.deakin.rave_app.fragments.FragmentThree;

public class MainActivity extends AppCompatActivity {

    public static MainActivity instance;

    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private Fragment fragmentThree;
    private TabLayout allTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance=this;

        getAllWidgets();
        bindWidgetsWithAnEvent();
        setupTabLayout();
            }

    public static MainActivity getInstance() {
        return instance;
    }

    private void getAllWidgets() {
        allTabs = (TabLayout) findViewById(R.id.tabs);
    }

    private void setupTabLayout() {
        fragmentOne = new FragmentOne().newInstance();
        fragmentTwo = new FragmentTwo().newInstance();
        fragmentThree = new FragmentThree();

        allTabs.addTab(allTabs.newTab().setText("NEWSFEED"),true);
        allTabs.addTab(allTabs.newTab().setText("CONTACTS"));
        allTabs.addTab(allTabs.newTab().setText("UBER"));
    }

    private void bindWidgetsWithAnEvent()
    {
        allTabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentTabFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void setCurrentTabFragment(int tabPosition)
    {
        switch (tabPosition)
        {
            case 0 :
                replaceFragment(FragmentOne.newInstance());
                break;
            case 1 :
                replaceFragment(FragmentTwo.newInstance());
                break;
            case 2 :
                replaceFragment(FragmentThree.newInstance());
                break;

        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_container, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle item selection
        /*switch (item.getItemId()) {

        }*/
        SharedPreferences settings = getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("USER_TOKEN", "");
        editor.commit();
        Intent i = new Intent(this,SignInActivity_.class);
        startActivity(i);
        finish();
        return false;
    }
}

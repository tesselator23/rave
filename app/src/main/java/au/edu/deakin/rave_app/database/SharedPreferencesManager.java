package au.edu.deakin.rave_app.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import au.edu.deakin.rave_app.model.User;


@EBean(scope = EBean.Scope.Singleton)
public class SharedPreferencesManager {
    @RootContext
    Context context;

    static final String PREFS_NAME = "PREFERENCES";
    static final String PREFS_USER = "USER_TOKEN";

    // Set & Get String
    String getString(String key, String defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, defaultValue);
    }

    void setString(String key, String value)
    {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
    }

    // Functions
    public User getUser() {
        String userToken = getString(PREFS_USER, "");
        if(TextUtils.isEmpty(userToken))
            return null;
        User user = new Gson().fromJson(userToken, User.class);
        return user;
    }

    public void setUser(User user) {
        String  data = user == null?"" :new Gson().toJson(user);
        setString(PREFS_USER, data);
    }
}

package au.edu.deakin.rave_app.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import au.edu.deakin.rave_app.R;
import au.edu.deakin.rave_app.model.User;
import au.edu.deakin.rave_app.utils.Util;


@EActivity(R.layout.activity_signup)
public class SignUpActivity extends BaseActivity {

    @ViewById
    EditText edtUsername, edtPassword, edtEmail;

    @AfterViews
    void init()
    {
    }

    @Click
    void btnRegister()
    {
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        String email = edtEmail.getText().toString();
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password) ||TextUtils.isEmpty(username))
        {
            showMessage("Input missed");
            return;
        }

        if(password.length() < 6)
        {
            showMessage("Password must 6 charater.");
            return;
        }

        if(!Util.isValidEmail(email))
        {
            showMessage("Email invaid");
            return;
        }

        User user = database.getUser(username,password);
        if(user != null)
        {
            showMessage("User Already!");
            return;
        }

        user = new User();
        user.setUserName(username);
        user.setPassword(password);
        user.setEmail(email);
        database.addUser(user);
        showMessage("Succes");
        sharedPreferences.setUser(user);
        goHomeScreen();
    }

    void goHomeScreen()
    {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }

    @Click
    void tvSignin()
    {
        onBackPressed();
    }
}

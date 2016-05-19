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


@EActivity(R.layout.activity_signin)
public class SignInActivity extends BaseActivity {

    @ViewById
    EditText edtUsername, edtPassword;


    @AfterViews
    void init()
    {
        //edtUsername.setText("test");
        //edtPassword.setText("123456");
    }

    @Click
    void btnSignIn()
    {
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        if(TextUtils.isEmpty(password) ||TextUtils.isEmpty(username))
        {
            showMessage("Input missed");
            return;
        }

        User user = database.getUser(username,password);

        if(user == null)
        {
            showMessage("Username or password is incorrect.");
            return;
        }
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
    void tvRegister()
    {
        Intent i = new Intent(this,SignUpActivity_.class);
        startActivity(i);
    }
}

package au.edu.deakin.rave_app.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class User extends BaseEntity {

    public User() {    }

    @SerializedName("userName")
    private String userName;

    @SerializedName("password")
    private String password;

    @SerializedName("email")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}

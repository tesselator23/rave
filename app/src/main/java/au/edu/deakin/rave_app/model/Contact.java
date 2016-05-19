package au.edu.deakin.rave_app.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;


public class Contact extends BaseEntity {

    @SerializedName("userId")
    private String userId;

    @SerializedName("fullName")
    private String fullName;

    @SerializedName("birdthday")
    private Date birdthday;

    @SerializedName("onlineDay")
    private int onlineDay;

    @SerializedName("avatar")
    private int avatar;

    @SerializedName("phone")
    private String phone;

    @SerializedName("email")
    private String email;

    @SerializedName("address")
    private String address;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirdthday() {
        return birdthday;
    }

    public void setBirdthday(Date birdthday) {
        this.birdthday = birdthday;
    }

    public int getOnlineDay() {
        return onlineDay;
    }

    public void setOnlineDay(int onlineDay) {
        this.onlineDay = onlineDay;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

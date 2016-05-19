package au.edu.deakin.rave_app.utils;

import com.google.gson.annotations.SerializedName;

import java.util.Date;


public class ContantRave {

    // inspections table name
    public static final String TABLE_CONTACT = "TABLE_CONTACT";
    public static final String TABLE_EVENT = "TABLE_EVENT";
    public static final String TABLE_USER = "TABLE_USER";

    public static final String ID = "id";

    // CONTACT Table Columns names
    public static final String UserId = "userId";
    public static final String FullName = "fullName";
    public static final String Birdthday = "birdthday";
    public static final String OnlineDay = "onlineDay";
    public static final String Avatar = "avatar";
    public static final String Phone = "phone";
    public static final String Email = "email";
    public static final String Address = "address";

    // EVENT Table Columns names
    public static final String Image = "image";
    public static final String Title = "title";
    public static final String Description = "description";
    public static final String From = "fromDate";
    public static final String To = "toDate";
    public static final String CreateDate = "createDate";
    public static final String Time = "time";

    // User Table Columns names
    public static final String UserName = "userName";
    public static final String Password = "password";


}

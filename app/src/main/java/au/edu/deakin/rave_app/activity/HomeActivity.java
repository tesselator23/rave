package au.edu.deakin.rave_app.activity;

import android.app.Activity;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import au.edu.deakin.rave_app.R;
import au.edu.deakin.rave_app.model.Contact;
import au.edu.deakin.rave_app.model.Event;



@EActivity(R.layout.activity_home)
public class HomeActivity extends BaseActivity {

    @AfterViews
    void init()
    {
    }

    @Click
    void btnAddEvent()
    {
        /*Event event = new Event();
        event.setUserId("abc");
        event.setTitle("I test event");
        event.setImage(R.drawable.image1_a);
        event.setDescription("Hello, I using squuilte to check");
        event.setFrom(Calendar.getInstance().getTime());
        event.setTo(Calendar.getInstance().getTime());
        database.addEvent(event);*/

        Contact contact = new Contact();
        contact.setFullName("Tran Thien Hau");
        contact.setAddress("Binh Thuan");
        contact.setAvatar(R.drawable.cast_ic_notification_1);
        contact.setPhone("123455");
        contact.setBirdthday(Calendar.getInstance().getTime());
        contact.setOnlineDay(2);
        contact.setUserId("abc");
        contact.setEmail("tranthienhau@gmai.com");
        database.addContact(contact);
    }

    @Click
    void btnUpdateEvent()
    {
        /*Event event = new Event();
        event.setId("TABLE_EVENT_1463476951805");
        event.setUserId("abc");
        event.setTitle("I test event Update");
        event.setImage(R.drawable.image1_a);
        event.setDescription("Hello, I using squuilte to check Update");
        event.setFrom(Calendar.getInstance().getTime());
        event.setTo(Calendar.getInstance().getTime());
        database.updateEvent(event);*/

        Contact contact = new Contact();
        contact.setId("TABLE_CONTACT_1463477356123");
        contact.setFullName("Tran Thien Hau Update");
        contact.setAddress("Binh Thuan Update");
        contact.setAvatar(R.drawable.cast_ic_notification_1);
        contact.setPhone("123455 Update");
        contact.setBirdthday(Calendar.getInstance().getTime());
        contact.setOnlineDay(2);
        contact.setUserId("abc");
        contact.setEmail("tranthienhau@gmai.com");
        database.updateContact(contact);
    }

    @Click
    void btnDeleteEvent()
    {
        //database.deleteEvent("TABLE_EVENT_1463476951805");

        database.deleteContact("TABLE_CONTACT_1463477356123");
    }

    @Click
    void btnGetEvents()
    {
        /*List<Event> events = database.getEvents();
        int count = events.size();*/

        //List<Contact> events = database.getContacts(user);
       // int count = events.size();
    }
}


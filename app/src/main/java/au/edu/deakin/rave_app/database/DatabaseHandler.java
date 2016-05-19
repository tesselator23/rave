package au.edu.deakin.rave_app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import au.edu.deakin.rave_app.model.Contact;
import au.edu.deakin.rave_app.model.Event;
import au.edu.deakin.rave_app.model.User;
import au.edu.deakin.rave_app.utils.ContantRave;
import au.edu.deakin.rave_app.utils.Util;



@EBean(scope = EBean.Scope.Singleton)
public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version

    @RootContext
    Context mContext;

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "DATABASE_NAME";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + ContantRave.TABLE_CONTACT
                + "(" + ContantRave.ID + " TEXT PRIMARY KEY," + ContantRave.UserId
                + " TEXT," + ContantRave.FullName + " TEXT," + ContantRave.Birdthday
                + " TEXT," + ContantRave.OnlineDay + " INT," + ContantRave.Avatar
                + " INT," + ContantRave.Phone + " TEXT," + ContantRave.Email
                + " TEXT," + ContantRave.Address+ " TEXT"
                + ")";
        db.execSQL(CREATE_CONTACT_TABLE);

        String CREATE_EVENT_TABLE = "CREATE TABLE " + ContantRave.TABLE_EVENT
                + "(" + ContantRave.ID + " TEXT PRIMARY KEY," + ContantRave.UserId
                + " TEXT," + ContantRave.Image + " INT," + ContantRave.Title
                + " TEXT," + ContantRave.Description + " TEXT," + ContantRave.From
                + " TEXT," + ContantRave.To + " TEXT," + ContantRave.CreateDate
                + " TEXT," + ContantRave.Time+ " TEXT"
                + ")";
        db.execSQL(CREATE_EVENT_TABLE);

        String CREATE_USER_TABLE = "CREATE TABLE " + ContantRave.TABLE_USER
                + "(" + ContantRave.UserName + " TEXT PRIMARY KEY," + ContantRave.Password
                + " TEXT," + ContantRave.Email+ " TEXT"
                + ")";
        db.execSQL(CREATE_USER_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + ContantRave.TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + ContantRave.TABLE_CONTACT);
        db.execSQL("DROP TABLE IF EXISTS " + ContantRave.TABLE_EVENT);
        // Create tables again
        onCreate(db);
    }

    // User Table
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ContantRave.UserName, user.getUserName());
        values.put(ContantRave.Password, user.getPassword());
        values.put(ContantRave.Email, user.getEmail());
        // Inserting Row
        db.insert(ContantRave.TABLE_USER, null, values);
        db.close(); // Closing database connection
    }

    public User getUser(String userName, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(ContantRave.TABLE_USER, new String[] {
                ContantRave.UserName,ContantRave.Password,ContantRave.Email,}, ContantRave.UserName
                + "=? AND "+ ContantRave.Password+"=?", new String[] { userName, password }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        try {
            User user = new User();
            user.setUserName(cursor.getString(0));
            user.setPassword(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            // return contact
            return user;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }

    }

    // Event Table

    public void addEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ContantRave.ID, createId(ContantRave.TABLE_EVENT));
        values.put(ContantRave.UserId, event.getUserId());
        values.put(ContantRave.Image, event.getImage());
        values.put(ContantRave.Title, event.getTitle());
        values.put(ContantRave.Description, event.getDescription());
        String fromToString = Util.dateToString(event.getFrom());
        values.put(ContantRave.From, fromToString);
        String toToString = Util.dateToString(event.getTo());
        values.put(ContantRave.To, toToString);
        String createToString = Util.dateToString(createDate());
        values.put(ContantRave.CreateDate, createToString);
        values.put(ContantRave.Time, event.getTime());
        // Inserting Row
        db.insert(ContantRave.TABLE_EVENT, null, values);
        db.close(); // Closing database connection
    }

    public int updateEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ContantRave.Image, event.getImage());
        values.put(ContantRave.Title, event.getTitle());
        values.put(ContantRave.Description, event.getDescription());
        String fromToString = Util.dateToString(event.getFrom());
        values.put(ContantRave.From, fromToString);
        String toToString = Util.dateToString(event.getTo());
        values.put(ContantRave.To, toToString);
        values.put(ContantRave.Time, event.getTime());
        // updating row
        return db.update(ContantRave.TABLE_EVENT, values, ContantRave.ID + " = ?",
                new String[] { String.valueOf(event.getId()) });
    }

    public void deleteEvent(String eventId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ContantRave.TABLE_EVENT, ContantRave.ID + " = ?",
                new String[] { eventId });
        db.close();
    }

    public List<Event> getEvents(String username) {
        List<Event> events = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + ContantRave.TABLE_EVENT +" WHERE "+ ContantRave.UserId +"='"+ username +"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Event event = new Event();
               // event.setInspectionId(cursor.getString(0));
                event.setId(cursor.getString(0));
                event.setUserId(cursor.getString(1));
                event.setImage(cursor.getInt(2));
                event.setTitle(cursor.getString(3));
                event.setDescription(cursor.getString(4));
                Date from = Util.stringToDate(cursor.getString(5));
                event.setFrom(from);
                Date to = Util.stringToDate(cursor.getString(6));
                event.setTo(to);
                Date create = Util.stringToDate(cursor.getString(7));
                event.setCreateDate(create);
                event.setTime(cursor.getString(8));

                // Adding contact to list
                events.add(event);
            } while (cursor.moveToNext());
        }

        this.sortEvents(events);
        return events;
    }

    // Contact Table

    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ContantRave.ID, createId(ContantRave.TABLE_CONTACT));
        values.put(ContantRave.UserId, contact.getUserId());
        values.put(ContantRave.FullName, contact.getFullName());
        String day = Util.dateToString(contact.getBirdthday());
        values.put(ContantRave.Birdthday, day);
        values.put(ContantRave.OnlineDay, contact.getOnlineDay());
        values.put(ContantRave.Avatar, contact.getAvatar());
        values.put(ContantRave.Phone, contact.getPhone());
        values.put(ContantRave.Email, contact.getEmail());
        values.put(ContantRave.Address, contact.getAddress());
        // Inserting Row
        db.insert(ContantRave.TABLE_CONTACT, null, values);
        db.close(); // Closing database connection
    }

    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ContantRave.FullName, contact.getFullName());
        String day = Util.dateToString(contact.getBirdthday());
        values.put(ContantRave.Birdthday, day);
        values.put(ContantRave.OnlineDay, contact.getOnlineDay());
        values.put(ContantRave.Avatar, contact.getAvatar());
        values.put(ContantRave.Phone, contact.getPhone());
        values.put(ContantRave.Email, contact.getEmail());
        values.put(ContantRave.Address, contact.getAddress());
        // updating row
        return db.update(ContantRave.TABLE_CONTACT, values, ContantRave.ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
    }

    public void deleteContact(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ContantRave.TABLE_CONTACT, ContantRave.ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }

    public List<Contact> getContacts(String username) {
        List<Contact> contacts = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + ContantRave.TABLE_CONTACT +" WHERE "+ ContantRave.UserId +"='"+ username +"'" ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(cursor.getString(0));
                contact.setUserId(cursor.getString(1));
                contact.setFullName(cursor.getString(2));
                Date day = Util.stringToDate(cursor.getString(3));
                contact.setBirdthday(day);
                contact.setOnlineDay(cursor.getInt(4));
                contact.setAvatar(cursor.getInt(5));
                contact.setPhone(cursor.getString(6));
                contact.setEmail(cursor.getString(7));
                contact.setAddress(cursor.getString(8));

                // Adding contact to list
                contacts.add(contact);
            } while (cursor.moveToNext());
        }

        this.sortContacts(contacts);
        return contacts;
    }


    /*public FarmInspection getInspection(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_INSPECTION, new String[] {
                INSPECTION_ID,INSPECTION_INFO,INSPECTION_PHOTOS,INSPECTION_TIME,INDEX_LAYOUT,UUID,IS_SUBMIT }, INSPECTION_ID
                + "=?", new String[] { id }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        try {
            FarmInspection inspection = new FarmInspection();
            inspection.setInspectionId(cursor.getString(0));
            inspection.setInfo(cursor.getString(1));
            inspection.setPhotos(cursor.getString(2));
            inspection.setDateTime(cursor.getString(3));
            inspection.setIndex(cursor.getString(4));
            inspection.setUuid(cursor.getString(5));
            inspection.setSubmit((cursor.getString(6) == "1"));
            // return contact
            return inspection;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }

    }*/

    /*public FarmInspection getInspectionSubmit(String id, String isPast) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_INSPECTION, new String[] {
                INSPECTION_ID,INSPECTION_INFO,INSPECTION_PHOTOS,INSPECTION_TIME,INDEX_LAYOUT,UUID,IS_SUBMIT }, INSPECTION_ID
                + "=? AND "+ IS_SUBMIT+"=?", new String[] { id, isPast }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        try {
            FarmInspection inspection = new FarmInspection();
            inspection.setInspectionId(cursor.getString(0));
            inspection.setInfo(cursor.getString(1));
            inspection.setPhotos(cursor.getString(2));
            inspection.setDateTime(cursor.getString(3));
            inspection.setIndex(cursor.getString(4));
            inspection.setUuid(cursor.getString(5));
            inspection.setSubmit((cursor.getString(6) == "1"));
            // return contact
            return inspection;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }

    }*/



   /* public int getInspectionsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_INSPECTION;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }*/

    private String createId(String content)
    {
        Calendar calendar = Calendar.getInstance();
        return content + "_" + calendar.getTime().getTime();
    }

    private Date createDate()
    {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }


    private void sortEvents(List<Event> events)
    {
        Collections.sort(events ,new Comparator<Event>() {
            @Override
            public int compare(Event  model1, Event  model2)
            {
                return  (int) ((model2.getCreateDate().getTime()) - (model1.getCreateDate().getTime()));
            }
        });
    }

    private void sortContacts(List<Contact> Contact)
    {
        Collections.sort(Contact ,new Comparator<Contact>() {
            @Override
            public int compare(Contact  model1, Contact  model2)
            {
                return model1.getFullName().compareToIgnoreCase(model2.getFullName());
            }
        });
    }

}

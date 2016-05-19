package au.edu.deakin.rave_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import au.edu.deakin.rave_app.R;
import au.edu.deakin.rave_app.model.Contact;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ListViewAdapter extends BaseAdapter {

    private List<Contact> allContacts;
    private Context context;
    private LayoutInflater inflater;

    public ListViewAdapter(Context context, List<Contact> allContacts) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.allContacts = allContacts;
    }

    @Override
    public int getCount() {
        return allContacts.size();
    }

    @Override
    public Object getItem(int position) {
        return allContacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.contact_inflater, parent, false);
        }
        Contact contact = allContacts.get(position);

        TextView name = (TextView) view.findViewById(R.id.tvContactListName);
        name.setText(contact.getFullName());

        int day_of_week = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

        ImageView online = (ImageView) view.findViewById(R.id.imgOnline);
        online.setVisibility(contact.getOnlineDay() == day_of_week ? View.VISIBLE : View.INVISIBLE);

        return view;
    }
}


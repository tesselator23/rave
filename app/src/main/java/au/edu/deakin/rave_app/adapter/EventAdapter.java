package au.edu.deakin.rave_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import au.edu.deakin.rave_app.R;
import au.edu.deakin.rave_app.model.Event;
import au.edu.deakin.rave_app.utils.Util;


public class EventAdapter extends BaseAdapter {

    private List<Event> allContacts;
    private Context context;
    private LayoutInflater inflater;

    public EventAdapter(Context context, List<Event> allContacts) {
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
            view = inflater.inflate(R.layout.item_event, parent, false);
        }

        Event event = allContacts.get(position);

        ImageView imgIcon = (ImageView) view.findViewById(R.id.imgIcon);
        imgIcon.setImageResource(event.getImage());

        TextView title = (TextView) view.findViewById(R.id.tvTitle);
        title.setText(event.getTitle());

        TextView description = (TextView) view.findViewById(R.id.tvDescription);
        description.setText(event.getDescription());
        TextView time = (TextView) view.findViewById(R.id.tvTime);

        TextView tvFrom = (TextView) view.findViewById(R.id.tvFrom);
        String fromToString = Util.dateToString(event.getFrom(),"E, d MMM yyyy");
        tvFrom.setText(fromToString);

        TextView tvTo = (TextView) view.findViewById(R.id.tvTo);
       // String toToString = Util.dateToString(event.getTo(),"dd/MM/yyyy");
        tvTo.setText(event.getTime());

        return view;
    }
}

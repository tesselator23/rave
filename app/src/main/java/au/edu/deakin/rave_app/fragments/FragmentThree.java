package au.edu.deakin.rave_app.fragments;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import au.edu.deakin.rave_app.R;
import au.edu.deakin.rave_app.activity.MainActivity;
import au.edu.deakin.rave_app.adapter.ListViewAdapter;

import java.util.ArrayList;


/**
 * Created by Mehul on 03-Dec-2015.
 */
public class FragmentThree extends Fragment
{
    private ListView listView;
    TypedArray allContacts;
    ArrayList<String> allContactNames = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_three, null);
        getAllWidgets(rootView);
        setAdapter();
        return rootView;
    }

    private void getAllWidgets(View view) {
        listView = (ListView) view.findViewById(R.id.listFragmentThree);

        allContacts = getResources().obtainTypedArray(R.array.all_contacts);

    }

    private void setAdapter()
    {
        for (int i = 0; i < allContacts.length(); i++) {
            allContactNames.add(allContacts.getString(i));
        }

        ListViewAdapter listViewAdapter= new ListViewAdapter(MainActivity.getInstance(), allContactNames);
        listView.setAdapter(listViewAdapter);
    }
}

package au.edu.deakin.rave_app.fragments;

import android.app.Dialog;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ItemLongClick;
import org.androidannotations.annotations.ViewById;

import au.edu.deakin.rave_app.R;
import au.edu.deakin.rave_app.activity.MainActivity;
import au.edu.deakin.rave_app.adapter.ListViewAdapter;
import au.edu.deakin.rave_app.model.Contact;
import au.edu.deakin.rave_app.model.Event;
import au.edu.deakin.rave_app.model.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;


@EFragment(R.layout.fragment_two)
public class FragmentTwo extends BaseFragment
{
    TypedArray allContacts;
    List<Contact> allContactNames;
    List<String> days;
    ArrayAdapter<String> dataAdapter;

    @ViewById
    ListView listFragmentTwo;

    User user;
    boolean isEdit;
    boolean isEditContact;

    public static FragmentTwo newInstance() {
        FragmentTwo fr = new FragmentTwo_();
        return fr;
    }

    @AfterViews
    void init() {
        loadData();

        days = new ArrayList<String>();
        days.add("Sunday");
        days.add("Monday");
        days.add("Tusday");
        days.add("Wedday");
        days.add("Thusday");
        days.add("Friday");
        days.add("Saturday");
        // Creating adapter for spinner
        dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, days);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    void initData()
    {
        /*<item>Mehul Rughani</item>
        <item>Tejas Jasani</item>
        <item>Ravi Gadesha</item>*/
        if(allContactNames == null)
            allContactNames = new ArrayList<>();
        Contact data = new Contact();
        data.setFullName("Rughani");
        data.setEmail("Rughani@gmail.com");
        data.setPhone("");
        data.setAddress("");
        data.setOnlineDay(4);
        data.setBirdthday(Calendar.getInstance().getTime());
        allContactNames.add(data);

        data = new Contact();
        data.setFullName("Tejas Jasani");
        data.setEmail("Rughani@gmail.com");
        data.setPhone("");
        data.setAddress("");
        data.setOnlineDay(5);
        data.setBirdthday(Calendar.getInstance().getTime());
        allContactNames.add(data);

        data = new Contact();
        data.setFullName("Ravi Gadesha");
        data.setEmail("Rughani@gmail.com");
        data.setPhone("");
        data.setAddress("");
        data.setOnlineDay(6);
        data.setBirdthday(Calendar.getInstance().getTime());
        allContactNames.add(data);

    }

    void loadData()
    {
        user = sharedPreferences.getUser();
        allContactNames = database.getContacts(user.getUserName());
        initData();
        ListViewAdapter listViewAdapter= new ListViewAdapter(MainActivity.getInstance(), allContactNames);
        listFragmentTwo.setAdapter(listViewAdapter);
    }

    @ItemClick
    public void listFragmentTwoItemClicked(int position) {
        isEdit = false;
        isEditContact = false;
        addShowContact(allContactNames.get(position));
    }

    @ItemLongClick
    public void listFragmentTwoItemLongClicked(Contact contact) {
        deleteContact(contact);
    }

    @Click
    void btnAddContact()
    {
        isEditContact = true;
        addShowContact(new Contact());
    }

    void addShowContact(final Contact data)
    {
        // custom dialog
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.contact_dialog);

        final EditText edtFullName = (EditText) dialog.findViewById(R.id.edtFullname);
        final EditText edtEmail = (EditText) dialog.findViewById(R.id.edtEmail);
        final EditText edtPhone = (EditText) dialog.findViewById(R.id.edtPhone);
        final EditText edtAddress = (EditText) dialog.findViewById(R.id.edtAdress);
        final Spinner spinner = (Spinner) dialog.findViewById(R.id.spinner);
        spinner.setAdapter(dataAdapter);
        final ImageView dialogButton = (ImageView) dialog.findViewById(R.id.btnAddEvent);
        if(!TextUtils.isEmpty(data.getFullName()))
        {
            edtFullName.setText(data.getFullName());
            edtEmail.setText(data.getEmail());
            edtPhone.setText(data.getPhone());
            edtAddress.setText(data.getAddress());
            spinner.setSelection(data.getOnlineDay()-1);
            dialogButton.setImageResource(R.drawable.icn_edit);

            edtFullName.setEnabled(false);
            edtEmail.setEnabled(false);
            edtPhone.setEnabled(false);
            spinner.setEnabled(false);
            edtAddress.setEnabled(false);

        }


        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isEdit)
                {
                    isEdit = true;
                    dialogButton.setImageResource(R.drawable.btn_add);
                    edtFullName.setEnabled(true);
                    edtEmail.setEnabled(true);
                    edtPhone.setEnabled(true);
                    spinner.setEnabled(true);
                    edtAddress.setEnabled(true);
                    return;
                }
                if(TextUtils.isEmpty(edtFullName.getText().toString()))
                {
                    showMessage("missing input.");
                    return;
                }
                data.setFullName(edtFullName.getText().toString());
                data.setEmail(edtEmail.getText().toString());
                data.setPhone(edtPhone.getText().toString());
                data.setAddress(edtAddress.getText().toString());
                data.setOnlineDay(spinner.getSelectedItemPosition() + 1);
                data.setBirdthday(Calendar.getInstance().getTime());
               // event.setImage(getImage());
                if(user != null)
                {
                    data.setUserId(user.getUserName());
                }
                creatUpdateContact(data, isEditContact);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    void creatUpdateContact(Contact contact, boolean isAdd)
    {
        if(isAdd) {
            database.addContact(contact);
        }
        else
        {
            database.updateContact(contact);
        }
        loadData();
    }


    void deleteContact(final Contact contact)
    {
        // custom dialog
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.delete_layout);

        TextView tvEvent = (TextView) dialog.findViewById(R.id.tvEvent);
        tvEvent.setText(contact.getFullName());
        Button delete = (Button) dialog.findViewById(R.id.btnDelete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.deleteContact(contact.getId());
                dialog.dismiss();
                loadData();
            }
        });

        Button cancel = (Button) dialog.findViewById(R.id.btnCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}

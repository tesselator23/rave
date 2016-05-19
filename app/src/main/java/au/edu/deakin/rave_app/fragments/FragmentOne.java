package au.edu.deakin.rave_app.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ItemLongClick;
import org.androidannotations.annotations.ViewById;

import au.edu.deakin.rave_app.R;
import au.edu.deakin.rave_app.activity.MainActivity;
import au.edu.deakin.rave_app.adapter.EventAdapter;
import au.edu.deakin.rave_app.adapter.GridViewAdapter;
import au.edu.deakin.rave_app.model.Event;
import au.edu.deakin.rave_app.model.User;
import au.edu.deakin.rave_app.utils.Util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;


@EFragment(R.layout.fragment_one)
public class FragmentOne extends BaseFragment {

   // ArrayList<Drawable> allDrawableImages = new ArrayList<>();
    private TypedArray allImages;
    Integer[] images = new Integer[]{R.drawable.image1_a,R.drawable.image1_b,R.drawable.image1_c,R.drawable.image1_d,
            R.drawable.image1_e,
            R.drawable.image1_f,R.drawable.image1_g,R.drawable.image1_h,R.drawable.image1_i,R.drawable.image1_j,
            R.drawable.image1_k,R.drawable.image1_m,R.drawable.image1_n,R.drawable.image1_o,R.drawable.image1_p,};

    /*@ViewById
    GridView gridViewFragmentOne;*/

    @ViewById
    ListView lsEvent;
    List<Event> events;
    User user;
    boolean isEdit;

    public static FragmentOne newInstance() {
        FragmentOne fr = new FragmentOne_();
        return fr;
    }

    @AfterViews
    void init() {

       /* allImages = getResources().obtainTypedArray(R.array.all_images);
        for (int i = 0; i < allImages.length(); i++) {
            allDrawableImages.add(allImages.getDrawable(i));
            Event event = new Event();
            events.add(event);
        }*/

       /* GridViewAdapter gridViewAdapter = new GridViewAdapter(MainActivity.getInstance(), allDrawableImages);
        gridViewFragmentOne.setAdapter(gridViewAdapter);*/



        loadData();
    }

    void initData()
    {
       /* if(events != null && events.size() > 0)
            return;*/
        if(events == null)
            events = new ArrayList<>();
        Event event = new Event();
        event.setImage(getImage());
        event.setTitle("FASHION WEEK PROMO PARTYY");
        event.setDescription("Nightlife melbourne is bringing to you one of the biggest promo party, \n" +
                "so Come and join us for a good night out and endless Great music, only playing your favourite tunes, the best in:\n" +
                "Barry White \n" +
                "FABz & Guests\n" +
                "Dress code: Chique smart casual\n" +
                "Doors open 9pm | Cover $20| Guest list $15");
        event.setTime("21:00 ~23:00");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2016);
        cal.set(Calendar.MONTH, 5);
        cal.set(Calendar.DAY_OF_MONTH, 16);
        event.setFrom(cal.getTime());
        events.add(event);

        event = new Event();
        event.setImage(getImage());
        event.setTitle("Smirnoff Nightlife Exchange Project Presents: Mix Off at Billboard");
        event.setDescription("Be There at Billboard's on Thursday 6th October to see Billboard¡¯s resident DJ¡¯s Mix Off on the decks, and make sure your dance floor vote counts for your favourite! \n" +
                "\n" +
                "Smirnoff Mix Off is a DJ competition, which celebrates the country¡¯s best resident DJ¡¯s, each battling for the chance to represent Australia at the Smirnoff Nightlife Exchange Project event, on November 12th this year.");
        event.setTime("22:00¨C3:00");
        cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2016);
        cal.set(Calendar.MONTH, 5);
        cal.set(Calendar.DAY_OF_MONTH, 10);
        event.setFrom(cal.getTime());
        events.add(event);
    }

    void loadData()
    {
        user = sharedPreferences.getUser();
        events = database.getEvents(user != null ? user.getUserName() : "");
        initData();
        EventAdapter adapter = new EventAdapter(getActivity(),events);
        lsEvent.setAdapter(adapter);
    }



    @ItemClick
    public void lsEventItemClicked(int position) {
        isEdit = false;
        showEvent(events.get(position));
    }

    @ItemLongClick
    public void lsEventItemLongClicked(Event event) {
        deleteEvent(event);
    }

    @Click
    void btnAddEvent()
    {
        addEvent();
    }

    void addEvent()
    {
        // custom dialog
        final Event event = new Event();
        event.setFrom(Calendar.getInstance().getTime());
        event.setTo(Calendar.getInstance().getTime());
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
       // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_event);

        final EditText edtTitle = (EditText) dialog.findViewById(R.id.edtTitle);
        final EditText edtDescription = (EditText) dialog.findViewById(R.id.edtDescription);
        final EditText edtTime = (EditText) dialog.findViewById(R.id.edtTime);

        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.btnAddEvent);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(edtTitle.getText().toString()) ||TextUtils.isEmpty(edtDescription.getText().toString())
                        ||TextUtils.isEmpty(edtTime.getText().toString()))
                {
                    showMessage("missing input.");
                    return;
                }
                event.setTitle(edtTitle.getText().toString());
                event.setDescription(edtDescription.getText().toString());
                event.setTime(edtTime.getText().toString());
                event.setImage(getImage());
                if(user != null)
                {
                    event.setUserId(user.getUserName());
                }
                creatEvent(event, true);
                dialog.dismiss();
            }
        });

        final TextView edtFrom = (TextView) dialog.findViewById(R.id.edtFrom);
        String dateToTime = Util.dateToString(event.getFrom(),"E,dd MMM yyyy");
        edtFrom.setText(dateToTime);
        edtFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCreateDialog(null, new ITimeDate() {
                    @Override
                    public void onTime(Date date) {
                        String dateToTime = Util.dateToString(date,"E,dd MMM yyyy");
                        edtFrom.setText(dateToTime);
                        event.setFrom(date);
                    }
                });
            }
        });
        final TextView edtTo = (TextView) dialog.findViewById(R.id.edtTo);
        edtTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCreateDialog(null, new ITimeDate() {
                    @Override
                    public void onTime(Date date) {
                        String dateToTime = Util.dateToString(date,"dd/MM/yyyy");
                        edtTo.setText(dateToTime);
                        event.setTo(date);
                    }
                });
            }
        });

        dialog.show();
    }


    protected void onCreateDialog(Date date, ITimeDate timeDate) {

        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        if(date != null)
        {
            cal.setTime(date);
            day = cal.get(Calendar.DAY_OF_MONTH);
            month = cal.get(Calendar.MONTH);
            year = cal.get(Calendar.YEAR);
        }
        Dialog dialog =  new DatePickerDialog(getActivity(), datePickerListener(timeDate), year, month, day);
        dialog.show();
    }
    DatePickerDialog.OnDateSetListener  datePickerListener(final ITimeDate timeDate)
    {
        DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int selectedYear,
                                  int selectedMonth, int selectedDay) {
                String dateToString = selectedDay + " / " + (selectedMonth + 1) + " / "
                        + selectedYear;
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, selectedYear);
                cal.set(Calendar.MONTH, selectedMonth);
                cal.set(Calendar.DAY_OF_MONTH, selectedDay);
                timeDate.onTime(cal.getTime());
            }
        };
        return datePickerListener;
    }

    int getImage()
    {
        Random ran = new Random();
        int image = ran.nextInt(15);
        if(image > images.length -1)
        {
            return images[0];
        }
        return images[image];
    }

    void creatEvent(Event event, boolean isAdd)
    {
        if(isAdd) {
            database.addEvent(event);
        }
        else
        {
            database.updateEvent(event);
        }
        loadData();
    }

    void showEvent(final Event event)
    {
        // custom dialog
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.event_detail);

        final EditText edtTitle = (EditText) dialog.findViewById(R.id.edtTitle);
        edtTitle.setText(event.getTitle());
        final EditText edtDescription = (EditText) dialog.findViewById(R.id.edtDescription);
        edtDescription.setText(event.getDescription());
        final EditText edtTime = (EditText) dialog.findViewById(R.id.edtTime);
        edtTime.setText(event.getTime());

        final TextView edtFrom = (TextView) dialog.findViewById(R.id.edtFrom);
        String dateToTime = Util.dateToString(event.getFrom(),"dd/MM/yyyy");
        edtFrom.setText(dateToTime);
        edtFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCreateDialog(event.getFrom(), new ITimeDate() {
                    @Override
                    public void onTime(Date date) {
                        String dateToTime = Util.dateToString(date,"dd/MM/yyyy");
                        edtFrom.setText(dateToTime);
                        event.setFrom(date);
                    }
                });
            }
        });
        final TextView edtTo = (TextView) dialog.findViewById(R.id.edtTo);
        String dateToTimeTo = Util.dateToString(event.getTo(),"dd/MM/yyyy");
        edtTo.setText(dateToTimeTo);
        edtTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCreateDialog(event.getTo(), new ITimeDate() {
                    @Override
                    public void onTime(Date date) {
                        String dateToTime = Util.dateToString(date,"dd/MM/yyyy");
                        edtTo.setText(dateToTime);
                        event.setTo(date);
                    }
                });
            }
        });

        ImageView imgIcon = (ImageView) dialog.findViewById(R.id.imgIcon);
        imgIcon.setImageResource(event.getImage());

        final ImageView dialogButton = (ImageView) dialog.findViewById(R.id.btnEditUpdate);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isEdit)
                {
                    isEdit =true;
                    dialogButton.setImageResource(R.drawable.btn_add);
                    edtTitle.setEnabled(true);
                    edtDescription.setEnabled(true);
                    edtFrom.setEnabled(true);
                    edtTo.setEnabled(true);
                    return;
                }
                if(TextUtils.isEmpty(edtTitle.getText().toString()) ||TextUtils.isEmpty(edtDescription.getText().toString())
                        ||TextUtils.isEmpty(edtTime.getText().toString()))
                {
                    showMessage("missing input.");
                    return;
                }
                event.setTitle(edtTitle.getText().toString());
                event.setDescription(edtDescription.getText().toString());
                event.setTime(edtTime.getText().toString());
                creatEvent(event,false);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    void deleteEvent(final Event event)
    {
        // custom dialog
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.delete_layout);

        TextView tvEvent = (TextView) dialog.findViewById(R.id.tvEvent);
        tvEvent.setText(event.getTitle());
        Button delete = (Button) dialog.findViewById(R.id.btnDelete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.deleteEvent(event.getId());
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

    interface ITimeDate
    {
        void onTime(Date date);
    }

}

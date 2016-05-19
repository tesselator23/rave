package au.edu.deakin.rave_app.fragments;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;

import au.edu.deakin.rave_app.R;


public class FragmentThree extends Fragment implements OnMapReadyCallback {
    private View rootView;
     MapView mMapView;

    public static FragmentThree newInstance() {
        FragmentThree fr = new FragmentThree();
        return fr;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);   }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            rootView = inflater.inflate(R.layout.fragment_three, container, false);
            MapsInitializer.initialize(this.getActivity());
            mMapView = (MapView) rootView.findViewById(R.id.map);
            mMapView.onCreate(savedInstanceState);
            mMapView.getMapAsync(this);
            View view = inflater.inflate(R.layout.fragment_three, container, false);
            Button uber = (Button) rootView.findViewById(R.id.uber);
            uber.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    try {
                        PackageManager pm = getActivity().getPackageManager();
                        pm.getPackageInfo("com.ubercab", PackageManager.GET_ACTIVITIES);
                        String uri =
                                "uber://?action=setPickup&pickup=my_location&client_id=YOUR_CLIENT_ID";
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(uri));
                        startActivity(intent);
                    } catch (PackageManager.NameNotFoundException e) {
                        // No Uber app! Open mobile website.
                        String url = "https://m.uber.com/sign-up?client_id=YOUR_CLIENT_ID";
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                }
            });
        } catch (InflateException e) {

        }
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        {
//todo
        }

    }
}
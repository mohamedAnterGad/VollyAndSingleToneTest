package com.example.vollytest;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    ArrayList<Branches> branches ;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        branches = new ArrayList<>();

        branches = (ArrayList<Branches>) getIntent().getSerializableExtra("branches");

        mapFragment.getMapAsync(this);


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //public static arraylist
//        for(int i=0 ;i<DataAdapter.branches.size() ; i++ ){
//            LatLng branch = new LatLng(DataAdapter.branches.get(i).getLat(), DataAdapter.branches.get(i).getLng());
//            mMap.addMarker(new MarkerOptions().position(branch).title(branch +" "+ (i+1) ));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(branch));
//
//
//        }

        /// getSerializable

        for(int i=0 ; i< branches.size() ; i++){
            LatLng branch = new LatLng(branches.get(i).getLat(), branches.get(i).getLng());
            mMap.addMarker(new MarkerOptions().position(branch).title(branch +" "+ (i+1) ));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(branch));

        }

    }
}

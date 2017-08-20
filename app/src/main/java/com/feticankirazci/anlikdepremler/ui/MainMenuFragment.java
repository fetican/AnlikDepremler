package com.feticankirazci.anlikdepremler.ui;


import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.feticankirazci.anlikdepremler.R;
import com.feticankirazci.anlikdepremler.models.EarthQuakesList;
import com.feticankirazci.anlikdepremler.services.ResponseServiceEvent;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainMenuFragment extends Fragment{

    private ProgressBar mProgressBar;
    private MapView mMapView;
    private GoogleMap mGoogleMap;
    private View view;
    private Response<EarthQuakesList> mResponse;
    private int i;

    public static MainMenuFragment newInstance() {
        MainMenuFragment fragment = new MainMenuFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        mProgressBar = view.findViewById(R.id.progressBar);
        mProgressBar.getIndeterminateDrawable().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
        mMapView = (MapView) view.findViewById(R.id.mapView);
        mMapView.setVisibility(View.GONE);
        mMapView.onCreate(savedInstanceState);

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
            addMapMarkers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    private void addMapMarkers(){
        if(mMapView == null || mResponse == null){
            return;
        }
        mProgressBar.setVisibility(View.GONE);
        mMapView.setVisibility(View.VISIBLE);
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mGoogleMap = mMap;

                for (i = 0; i < 10; i++) {
                    LatLng earthQuake = new LatLng(mResponse.body().data.get(i).lat, mResponse.body().data.get(i).lng);
                    mGoogleMap.addMarker(new MarkerOptions().position(earthQuake).title(mResponse.body().data.get(i).lokasyon).snippet(mResponse.body().data.get(i).tarih2 + " Şiddeti:" + mResponse.body().data.get(i).siddeti));
                    // For zooming automatically to the location of the marker
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(mResponse.body().data.get(0).lat,mResponse.body().data.get(0).lng)).zoom(4).build();
                    mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                }
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN , sticky = true)
    public void onCreateViewEvent(ResponseServiceEvent event) {
        if (event.exception == null) {
            mResponse = event.mResponse;
            addMapMarkers();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}

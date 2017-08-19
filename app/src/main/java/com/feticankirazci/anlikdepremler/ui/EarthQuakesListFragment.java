package com.feticankirazci.anlikdepremler.ui;


import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.feticankirazci.anlikdepremler.R;
import com.feticankirazci.anlikdepremler.adapters.EarthQuakeRecyclerViewAdapter;
import com.feticankirazci.anlikdepremler.models.EarthQuakesList;
import com.feticankirazci.anlikdepremler.network.Factory;
import com.feticankirazci.anlikdepremler.services.ResponseServiceEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class EarthQuakesListFragment extends Fragment {

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private EarthQuakeRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Response<EarthQuakesList> mResponse;
    private View view;

    public static EarthQuakesListFragment newInstance() {
        EarthQuakesListFragment fragment = new EarthQuakesListFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_earth_quakes_list, container, false);
        mProgressBar = view.findViewById(R.id.progressBarEarthQuakes);
        mProgressBar.getIndeterminateDrawable().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
        initial();
        return view;
    }

    private void initial() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN , sticky = true)
    public void onMessageEvent(ResponseServiceEvent event) {
        if (event.exception == null) {
            mResponse = event.mResponse;
            mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
            mLayoutManager = new LinearLayoutManager(this.getContext());
            mRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new EarthQuakeRecyclerViewAdapter(mResponse,this.getContext());
            mRecyclerView.setAdapter(mAdapter);
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}

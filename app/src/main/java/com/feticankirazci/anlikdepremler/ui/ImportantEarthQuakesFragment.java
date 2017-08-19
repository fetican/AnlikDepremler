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
import com.feticankirazci.anlikdepremler.adapters.ImportantEQRecyclerViewAdapter;
import com.feticankirazci.anlikdepremler.models.ImportantEarthQuakesList;
import com.feticankirazci.anlikdepremler.services.ImportantServiceEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImportantEarthQuakesFragment extends Fragment {

    private ProgressBar mProgressBar;
    private ImportantEQRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Response<ImportantEarthQuakesList> mResponse;
    private RecyclerView mRecyclerView;
    private View view;

    public static ImportantEarthQuakesFragment newInstance() {
        ImportantEarthQuakesFragment fragment = new ImportantEarthQuakesFragment();
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

        view = inflater.inflate(R.layout.fragment_important_earth_quakes, container, false);
        mProgressBar = view.findViewById(R.id.progressBarImportantEarthQuakes);
        mProgressBar.getIndeterminateDrawable().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
        return view;
    }

    private void initial() {
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageEvent(ImportantServiceEvent event){
        if (event.exception == null){
            mResponse = event.mImportantResponse;
            mRecyclerView = (RecyclerView) view.findViewById(R.id.importantRecyclerView);
            mLayoutManager = new LinearLayoutManager(this.getContext());
            mRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new ImportantEQRecyclerViewAdapter(this.getContext(), mResponse) ;
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

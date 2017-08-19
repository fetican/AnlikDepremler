package com.feticankirazci.anlikdepremler.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.feticankirazci.anlikdepremler.R;
import com.feticankirazci.anlikdepremler.adapters.ListViewAdapter;
import com.feticankirazci.anlikdepremler.models.Contacts;
import com.feticankirazci.anlikdepremler.services.ContactsListEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment {

    private View view;
    private ListView mListView;
    private ListViewAdapter mAdapter;
    private ArrayList<Contacts> mContactsList;

    public static ContactsFragment newInstance() {
        ContactsFragment fragment = new ContactsFragment();
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
        view = inflater.inflate(R.layout.fragment_contacts, container, false);

        mListView = view.findViewById(R.id.listView);
        mAdapter = new ListViewAdapter(this.getActivity(),mContactsList);
        mListView.setAdapter(mAdapter);

        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN , sticky = true)
    public void onCreateViewEvent(ContactsListEvent event) {
        if (event.exception == null) {
            mContactsList = event.mContactList;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}

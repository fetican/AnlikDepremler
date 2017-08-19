package com.feticankirazci.anlikdepremler.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.feticankirazci.anlikdepremler.R;
import com.feticankirazci.anlikdepremler.models.Contacts;

import java.util.ArrayList;

/**
 * Created by Feti on 17.08.2017.
 */

public class ListViewAdapter extends BaseAdapter {

    private ArrayList<Contacts> mContactsList;
    private LayoutInflater mInflater;

    public ListViewAdapter(Context context, ArrayList<Contacts> contactsList) {
        this.mContactsList = contactsList;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mContactsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mContactsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = mInflater.inflate(R.layout.contact_list_item, parent, false);

        TextView contactName = itemView.findViewById(R.id.contactName);
        TextView contactNumber = itemView.findViewById(R.id.contactNumber);

        contactName.setText(mContactsList.get(position).getmName());
        contactNumber.setText(mContactsList.get(position).getmNumber());

        return itemView;
    }
}

package com.feticankirazci.anlikdepremler.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.feticankirazci.anlikdepremler.R;

/**
 * Created by Feti on 15.08.2017.
 */

public class EarthQuakeViewHolder extends RecyclerView.ViewHolder {

    public TextView mLocation, mMagnitude, mDate, mHour, mDepth;

    public EarthQuakeViewHolder(View itemView) {
        super(itemView);
        mLocation = itemView.findViewById(R.id.cardViewLocation);
        mDate = itemView.findViewById(R.id.cardViewDate);
        mDepth = itemView.findViewById(R.id.cardViewDepth);
        mHour = itemView.findViewById(R.id.cardViewHour);
        mMagnitude = itemView.findViewById(R.id.cardViewMagnitude);

    }
}

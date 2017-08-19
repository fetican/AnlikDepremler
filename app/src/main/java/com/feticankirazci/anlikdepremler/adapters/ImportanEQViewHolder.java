package com.feticankirazci.anlikdepremler.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.feticankirazci.anlikdepremler.R;

/**
 * Created by Feti on 17.08.2017.
 */

public class ImportanEQViewHolder extends RecyclerView.ViewHolder {

    public TextView mImportantMagnitude, mImportantDate, mImportantHour, mImportantMagnitudeFelt, mImportantLocation, mDamagedBuildings, mLifesLost;

    public ImportanEQViewHolder(View itemView) {
        super(itemView);
        mImportantDate = itemView.findViewById(R.id.importantDate);
        mImportantHour = itemView.findViewById(R.id.importantHour);
        mImportantLocation = itemView.findViewById(R.id.importantLocation);
        mImportantMagnitude = itemView.findViewById(R.id.importantMagnitude);
        mImportantMagnitudeFelt = itemView.findViewById(R.id.importantMagnitudeFelt);
        mDamagedBuildings = itemView.findViewById(R.id.damagedBuildings);
        mLifesLost = itemView.findViewById(R.id.lifeLost);

    }
}

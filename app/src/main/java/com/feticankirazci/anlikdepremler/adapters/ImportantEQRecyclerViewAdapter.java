package com.feticankirazci.anlikdepremler.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.feticankirazci.anlikdepremler.R;
import com.feticankirazci.anlikdepremler.models.ImportantEarthQuakesList;

import retrofit2.Response;

/**
 * Created by Feti on 17.08.2017.
 */

public class ImportantEQRecyclerViewAdapter extends RecyclerView.Adapter<ImportanEQViewHolder> {

    private Response<ImportantEarthQuakesList> mResponse;
    private Context mContext;

    public ImportantEQRecyclerViewAdapter(Context context, Response<ImportantEarthQuakesList> response) {
        this.mContext = context;
        this.mResponse = response;
    }

    @Override
    public ImportanEQViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImportanEQViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.important_eq_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ImportanEQViewHolder holder, int position) {

        holder.mImportantMagnitude.setText(String.valueOf(mResponse.body().data.get(position).siddeti));
        holder.mImportantMagnitudeFelt.setText(mContext.getResources().getString(R.string.magnitude_felt,mResponse.body().data.get(position).hissedilensiddedi));
        holder.mImportantHour.setText(mResponse.body().data.get(position).tarih.substring(11,16));
        holder.mImportantDate.setText(mResponse.body().data.get(position).tarih.substring(0,10));
        holder.mImportantLocation.setText(String.valueOf(mResponse.body().data.get(position).lokasyon));
        holder.mDamagedBuildings.setText(mContext.getResources().getString(R.string.damaged_buildings,mResponse.body().data.get(position).hasarlibina));
        holder.mLifesLost.setText(mContext.getResources().getString(R.string.life_lost,mResponse.body().data.get(position).cankaybi));
        if(mResponse.body().data.get(position).siddeti < 4){
            holder.mImportantMagnitude.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.magnitude_background_green));
        }else if(mResponse.body().data.get(position).siddeti < 5){
            holder.mImportantMagnitude.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.magnitude_background_yellow));
        }else{
            holder.mImportantMagnitude.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.magnitude_background_red));
        }
    }

    @Override
    public int getItemCount() {
        return mResponse.body().data.size();
    }
}

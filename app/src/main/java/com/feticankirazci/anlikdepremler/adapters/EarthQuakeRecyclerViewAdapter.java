package com.feticankirazci.anlikdepremler.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.feticankirazci.anlikdepremler.R;
import com.feticankirazci.anlikdepremler.models.EarthQuakesList;

import retrofit2.Response;

/**
 * Created by Feti on 15.08.2017.
 */

public class EarthQuakeRecyclerViewAdapter extends RecyclerView.Adapter<EarthQuakeViewHolder> {

    private Response<EarthQuakesList> mResponse;
    private Context mContext;

    public EarthQuakeRecyclerViewAdapter(Response<EarthQuakesList> response, Context context) {
        this.mResponse = response;
        this.mContext = context;
    }

    @Override
    public EarthQuakeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EarthQuakeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(EarthQuakeViewHolder holder, int position) {

        holder.mMagnitude.setText(String.valueOf(mResponse.body().data.get(position).siddeti));
        holder.mDepth.setText(mContext.getResources().getString(R.string.depth,String.valueOf(mResponse.body().data.get(position).derinlik)));
        holder.mHour.setText(mResponse.body().data.get(position).tarih2.substring(11,19));
        holder.mDate.setText(mResponse.body().data.get(position).tarih2.substring(0,10));
        holder.mLocation.setText(String.valueOf(mResponse.body().data.get(position).lokasyon));
        if(mResponse.body().data.get(position).siddeti < 4){
            holder.mMagnitude.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.magnitude_background_green));
        }else if(mResponse.body().data.get(position).siddeti < 5){
            holder.mMagnitude.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.magnitude_background_yellow));
        }else{
            holder.mMagnitude.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.magnitude_background_red));
        }
    }

    @Override
    public int getItemCount() {
        return mResponse.body().data.size();
    }
}

package com.hackathon.bus.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.hackathon.bus.NowBus_Info;
import com.hackathon.bus.R;
import com.hackathon.bus.StationInfoActivity;
import com.hackathon.bus.VO.BusResultVO;
import com.hackathon.bus.VO.BusVO;
import com.hackathon.bus.VO.StationVO;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by kim on 2018-08-12.
 */

public class StationResultRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    public static class BusResultViewHolder extends RecyclerView.ViewHolder {
        TextView sName;
        TextView sinfo;
        TextView sId;

        BusResultViewHolder(View view) {
            super(view);
            sName = view.findViewById(R.id.station_name);
            sinfo = view.findViewById(R.id.station_info);
            sId = view.findViewById(R.id.station_id);
        }

    }
    private LinkedList<StationVO> bus_result_itemsList;
    public StationResultRecyclerViewAdapter(LinkedList<StationVO> bus_result_itemsList, Context context){
        this.bus_result_itemsList = bus_result_itemsList;
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.station_result_item, parent, false);

        return new BusResultViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final BusResultViewHolder bus_result_viewHolder = (BusResultViewHolder) holder;

        bus_result_viewHolder.sName.setText(bus_result_itemsList.get(position).getsName());
        bus_result_viewHolder.sinfo.setText(bus_result_itemsList.get(position).getsDirection());
        bus_result_viewHolder.sId.setText(bus_result_itemsList.get(position).getsNum());
        bus_result_viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context,StationInfoActivity.class);
                intent.putExtra("StationName",bus_result_itemsList.get(position).getsName());
                intent.putExtra("StationNum", bus_result_itemsList.get(position).getsNum());
                intent.putExtra("StationDirection",bus_result_itemsList.get(position).getsDirection());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bus_result_itemsList.size();
    }



}

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

public class BusResultRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    public static class BusResultViewHolder extends RecyclerView.ViewHolder {
        TextView bNum;
        TextView bDirection;
        TextView bFirstArrive;
        TextView bSecondArrive;
        TextView bCongetion;

        BusResultViewHolder(View view) {
            super(view);
            bNum = view.findViewById(R.id.bus_num);
            bDirection = view.findViewById(R.id.bus_direction);
            bFirstArrive = view.findViewById(R.id.first_arrive);
            bSecondArrive=view.findViewById(R.id.second_arrive);
            bCongetion=view.findViewById(R.id.congetion);

        }

    }
    private LinkedList<BusVO> bus_result_itemsList;
    public BusResultRecyclerViewAdapter(LinkedList<BusVO> bus_result_itemsList, Context context){
        this.bus_result_itemsList = bus_result_itemsList;
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bus_result_item, parent, false);

        return new BusResultViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final BusResultViewHolder bus_result_viewHolder = (BusResultViewHolder) holder;

        bus_result_viewHolder.bNum.setText(bus_result_itemsList.get(position).getBusNumber());
        bus_result_viewHolder.bCongetion.setText(bus_result_itemsList.get(position).getCongestion());
        bus_result_viewHolder.bFirstArrive.setText(bus_result_itemsList.get(position).getArrMsg1());
        bus_result_viewHolder.bSecondArrive.setText(bus_result_itemsList.get(position).getArrMsg2());


    }

    @Override
    public int getItemCount() {
        return bus_result_itemsList.size();
    }



}

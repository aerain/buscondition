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
import com.hackathon.bus.VO.BusResultVO;

import java.util.ArrayList;

/**
 * Created by kim on 2018-08-12.
 */

public class BusResultRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    public static class BusResultViewHolder extends RecyclerView.ViewHolder {
        TextView bus_num;
        TextView bus_docha;
        TextView bus_go_end;

        BusResultViewHolder(View view) {
            super(view);
            bus_num = view.findViewById(R.id.bus_num);
            bus_docha = view.findViewById(R.id.bus_docha);
            bus_go_end = view.findViewById(R.id.bus_go_end);
        }

    }
    private ArrayList<BusResultVO> bus_result_itemsList;
    public BusResultRecyclerViewAdapter(ArrayList<BusResultVO> bus_result_itemsList,Context context){
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

        BusResultViewHolder bus_result_viewHolder = (BusResultViewHolder) holder;

        bus_result_viewHolder.bus_num.setText(bus_result_itemsList.get(position).getBus_num());
        bus_result_viewHolder.bus_docha.setText(bus_result_itemsList.get(position).getBus_docha());
        bus_result_viewHolder.bus_go_end.setText(bus_result_itemsList.get(position).getBus_go_end());

        bus_result_viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context,NowBus_Info.class);
                intent.putExtra("BusNum",bus_result_itemsList.get(position).getBus_num());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bus_result_itemsList.size();
    }



}

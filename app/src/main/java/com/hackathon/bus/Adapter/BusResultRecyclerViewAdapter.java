package com.hackathon.bus.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
        TextView bCongetion, bPredictCongestion;

        BusResultViewHolder(View view) {
            super(view);
            bNum = view.findViewById(R.id.bus_num);
            bDirection = view.findViewById(R.id.bus_direction);
            bFirstArrive = view.findViewById(R.id.first_arrive);
            bSecondArrive=view.findViewById(R.id.second_arrive);
            bCongetion=view.findViewById(R.id.congetion);
            bPredictCongestion = view.findViewById(R.id.predictCongestion);

        }

    }
    private LinkedList<BusVO> bus_result_itemsList;
    private ArrayList<Integer> parseCongestion;
    public BusResultRecyclerViewAdapter(LinkedList<BusVO> bus_result_itemsList, Context context, ArrayList<Integer> parseCongestion){
        this.bus_result_itemsList = bus_result_itemsList;
        this.parseCongestion = parseCongestion;
        Log.i("시발", parseCongestion.toString());
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

        BusVO node = bus_result_itemsList.get(position);
        int busTerm = Integer.parseInt(node.getBusterm());
        String currentCongestion = node.getCongestion();
        String curCongestionToString = "정보 없음";
        String predictCongestionToString = "정보 없음";
        double reride = 0.0;

        if (parseCongestion.size() != 0) {
            for(int index = Integer.parseInt(node.getSectionOrder1()); index < Integer.parseInt(node.getStationOrder()); index++) {
                reride += parseCongestion.get(index);
            }

            reride = reride / (30 * (60 / busTerm));

            switch(currentCongestion) {
                case "3":
                    reride += 13;
                    curCongestionToString = "여유";
                    break;
                case "4":
                    reride += 25;
                    curCongestionToString = "보통";
                    break;
                case "5":
                    reride += 37;
                    curCongestionToString = "혼잡";
                    break;
                case "6":
                    reride += 49;
                    curCongestionToString = "매우 혼잡";
                    break;
                default:
                    reride = -1;
                    curCongestionToString = "정보 없음";
                    break;


            }

            if(reride == -1) {
                predictCongestionToString = "정보 없음";
            } else if(reride <= 13) {
                predictCongestionToString = "여유";
            } else if(reride >= 14 && reride <= 25) {
                predictCongestionToString = "보통";
            } else if(reride >= 26 && reride <= 37) {
                predictCongestionToString = "혼잡";
            } else if(reride >= 38) {
                predictCongestionToString = "매우 혼잡";
            }

        }

        bus_result_viewHolder.bNum.setText(node.getBusNumber());
        bus_result_viewHolder.bCongetion.setText(curCongestionToString);
        bus_result_viewHolder.bPredictCongestion.setText(predictCongestionToString);
        bus_result_viewHolder.bFirstArrive.setText(node.getArrMsg1());
        bus_result_viewHolder.bSecondArrive.setText(node.getArrMsg2());

    }

    @Override
    public int getItemCount() {
        return bus_result_itemsList.size();
    }



}

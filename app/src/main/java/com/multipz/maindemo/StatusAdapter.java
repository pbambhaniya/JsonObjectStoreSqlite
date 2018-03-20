package com.multipz.maindemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.List;

/**
 * Created by Admin on 18-11-2017.
 */

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.MyViewHolder> {
    private Context context;
    private List<StatusModel> list;
    private ItemClickListener clickListener;


    public StatusAdapter(List<StatusModel> expertsList, Context context) {
        this.list = expertsList;
        this.context = context;
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txt_status,txt_id;
        private LinearLayout rel_root,lnr_judgement;

        public MyViewHolder(View view) {
            super(view);
            txt_status = (TextView) view.findViewById(R.id.txt_status);
            txt_id = (TextView) view.findViewById(R.id.txt_id);
            rel_root = (LinearLayout) view.findViewById(R.id.rel_root);
            lnr_judgement = (LinearLayout) view.findViewById(R.id.lnr_judgement);
            lnr_judgement.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.itemClicked(view, getAdapterPosition());
            }
        }
    }


    @Override
    public StatusAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_judgement, parent, false);

        return new StatusAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StatusAdapter.MyViewHolder holder, int position) {
        StatusModel model = list.get(position);
        holder.txt_status.setText(model.getDescriptions());
        holder.txt_id.setText(model.getStatusId());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}

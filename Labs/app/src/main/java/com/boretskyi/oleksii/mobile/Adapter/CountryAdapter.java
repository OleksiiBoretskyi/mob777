package com.boretskyi.oleksii.mobile.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.boretskyi.oleksii.mobile.Entity.Country;
import com.boretskyi.oleksii.mobile.R;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Oleksii on 23.10.2018.
 */
public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    private List<Country> countryList;
    private Context mContext;

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name)
        TextView tvTitle;
        @BindView(R.id.tv_region)
        TextView tvRegion;
        @BindView(R.id.tv_sub_region)
        TextView tvSubRegion;
        @BindView(R.id.iv_flag)
        ImageView imgPoster;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public CountryAdapter(Context context, List<Country> films) {
        this.countryList = films;
        mContext = context;
    }

    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View postView = inflater.inflate(R.layout.row_item, parent, false);
        return new ViewHolder(postView);
    }

    @Override
    public void onBindViewHolder(CountryAdapter.ViewHolder holder, int position) {
        Country item = countryList.get(position);
        Glide.with(mContext)
                .load(item.getFlagPng())
                .into(holder.imgPoster);
        holder.tvTitle.setText(item.getName());
        holder.tvRegion.setText(item.getRegion());
        holder.tvSubRegion.setText(item.getSubRegion());
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public void updateAnswers(List<Country> items) {
        countryList = items;
        notifyDataSetChanged();
    }
}
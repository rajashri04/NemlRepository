package com.neml.qrcodescanner.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.neml.qrcodescanner.R;
import com.neml.qrcodescanner.model.DataResponseModel;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<DataResponseModel> dataList;
    private Context context;

    public CustomAdapter(Context context, ArrayList<DataResponseModel> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public void updateData(ArrayList<DataResponseModel> responseData) {
        dataList = responseData;
        notifyDataSetChanged();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public TextView tvQrCode, tvStateCode, tvMarket, tvSociety,
                tvAgency, tvCommodity, tvSeason, tvFarmerId,
                tvFarmerName, tvFarmerMobileNo, tvBagScannedOn,
                tvLotId, tvLotReferenceNo, tvLotQty, tvTotalBags,
                tvTradePrice, tvTradeValue, tvLotStatus, tvLotCreatedDate;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            tvQrCode = mView.findViewById(R.id.tvQrCode);
            tvStateCode = mView.findViewById(R.id.tvStateCode);
            tvMarket = mView.findViewById(R.id.tvMarket);
            tvSociety = mView.findViewById(R.id.tvSociety);
            tvAgency = mView.findViewById(R.id.tvAgency);
            tvCommodity = mView.findViewById(R.id.tvCommodity);
            tvSeason = mView.findViewById(R.id.tvSeason);
            tvFarmerId = mView.findViewById(R.id.tvFarmerId);
            tvFarmerName = mView.findViewById(R.id.tvFarmerName);
            tvFarmerMobileNo = mView.findViewById(R.id.tvFarmerMobileNo);
            tvBagScannedOn = mView.findViewById(R.id.tvBagScannedOn);
            tvLotId = mView.findViewById(R.id.tvLotId);
            tvLotReferenceNo = mView.findViewById(R.id.tvLotReferenceNo);
            tvLotQty = mView.findViewById(R.id.tvLotQty);
            tvTotalBags = mView.findViewById(R.id.tvTotalBags);
            tvTradePrice = mView.findViewById(R.id.tvTradePrice);
            tvTradeValue = mView.findViewById(R.id.tvTradeValue);
            tvLotStatus = mView.findViewById(R.id.tvLotStatus);
            tvLotCreatedDate = mView.findViewById(R.id.tvLotCreatedDate);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        holder.tvQrCode.setText(TextUtils.isEmpty(dataList.get(position).getQrCode()) ? "-" : dataList.get(position).getQrCode());
        holder.tvStateCode.setText(TextUtils.isEmpty(dataList.get(position).getStateCode()) ? "-" : dataList.get(position).getStateCode());
        holder.tvMarket.setText(TextUtils.isEmpty(dataList.get(position).getMarket()) ? "-" : dataList.get(position).getMarket());
        holder.tvSociety.setText(TextUtils.isEmpty(dataList.get(position).getSociety()) ? "-" : dataList.get(position).getSociety());
        holder.tvAgency.setText(TextUtils.isEmpty(dataList.get(position).getAgency()) ? "-" : dataList.get(position).getAgency());
        holder.tvCommodity.setText(TextUtils.isEmpty(dataList.get(position).getCommodity()) ? "-" : dataList.get(position).getCommodity());
        holder.tvSeason.setText(TextUtils.isEmpty(dataList.get(position).getSeason()) ? "-" : dataList.get(position).getSeason());
        holder.tvFarmerId.setText(TextUtils.isEmpty(dataList.get(position).getFarmerId()) ? "-" : dataList.get(position).getFarmerId());
        holder.tvFarmerName.setText(TextUtils.isEmpty(dataList.get(position).getFarmerName()) ? "-" : dataList.get(position).getFarmerName());
        holder.tvFarmerMobileNo.setText(TextUtils.isEmpty(dataList.get(position).getFarmerMobileNo()) ? "-" : dataList.get(position).getFarmerMobileNo());
        holder.tvBagScannedOn.setText(TextUtils.isEmpty(dataList.get(position).getBagScannedOn()) ? "-" : dataList.get(position).getBagScannedOn());
        holder.tvLotId.setText(TextUtils.isEmpty(dataList.get(position).getLotId()) ? "-" : dataList.get(position).getLotId());
        holder.tvLotReferenceNo.setText(TextUtils.isEmpty(dataList.get(position).getLotReferenceNo()) ? "-" : dataList.get(position).getLotReferenceNo());
        holder.tvLotQty.setText(TextUtils.isEmpty(dataList.get(position).getLotQty()) ? "-" : dataList.get(position).getLotQty());
        holder.tvTotalBags.setText(TextUtils.isEmpty(dataList.get(position).getTotalBags()) ? "-" : dataList.get(position).getTotalBags());
        holder.tvTradePrice.setText(TextUtils.isEmpty(dataList.get(position).getTradePrice()) ? "-" : dataList.get(position).getTradePrice());
        holder.tvTradeValue.setText(TextUtils.isEmpty(dataList.get(position).getTradeValue()) ? "-" : dataList.get(position).getTradeValue());
        holder.tvLotStatus.setText(TextUtils.isEmpty(dataList.get(position).getLotStatus()) ? "-" : dataList.get(position).getLotStatus());
        holder.tvLotCreatedDate.setText(TextUtils.isEmpty(dataList.get(position).getLotCreatedDate()) ? "-" : dataList.get(position).getLotCreatedDate());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
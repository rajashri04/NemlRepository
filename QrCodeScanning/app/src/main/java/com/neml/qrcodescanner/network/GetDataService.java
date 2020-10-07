package com.neml.qrcodescanner.network;

import com.neml.qrcodescanner.model.DataRequestModel;
import com.neml.qrcodescanner.model.DataResponseModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GetDataService {

    @POST("QRIdentifierApi/getDetails")
    Call<ArrayList<DataResponseModel>> getAllDetails(@Body ArrayList<DataRequestModel> dataRequest);
}
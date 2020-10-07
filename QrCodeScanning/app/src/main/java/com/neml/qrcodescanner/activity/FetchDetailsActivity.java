package com.neml.qrcodescanner.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.neml.qrcodescanner.R;
import com.neml.qrcodescanner.adapter.CustomAdapter;
import com.neml.qrcodescanner.model.DataRequestModel;
import com.neml.qrcodescanner.model.DataResponseModel;
import com.neml.qrcodescanner.network.Connectivity;
import com.neml.qrcodescanner.network.GetDataService;
import com.neml.qrcodescanner.network.RetrofitClientInstance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FetchDetailsActivity extends AppCompatActivity {

    private RecyclerView rvDetails;
    private ArrayList<DataResponseModel> responseData = new ArrayList<>();
    private CustomAdapter customAdapter;
    private SwipeRefreshLayout pullToRefreshLayout;
    private String qrCode;
    private TextView tvNoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        initUI();

        Bundle bundle = getIntent().getExtras();
        qrCode = bundle.getString("QR");
        callAPI(qrCode);
        customAdapter = new CustomAdapter(getApplicationContext(), responseData);
        rvDetails.setAdapter(customAdapter);
    }


    private void initUI() {
        tvNoData = findViewById(R.id.tvNoRecord);
        rvDetails = findViewById(R.id.rvDetails);
        rvDetails.setLayoutManager(new LinearLayoutManager(FetchDetailsActivity.this));

        pullToRefreshLayout = findViewById(R.id.pullToRefresh);
        pullToRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (pullToRefreshLayout.isRefreshing())
                    pullToRefreshLayout.setRefreshing(false);

                callAPI(qrCode);
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    /*
     * Method for API call to get farmer details based on QR code
     * */
    private void callAPI(String contents) {
        if (Connectivity.isConnected(getApplicationContext())) {
            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            DataRequestModel request = new DataRequestModel();
            request.setQrCode(contents);
            ArrayList<DataRequestModel> requestList = new ArrayList<>();
            requestList.add(request);

            Call<ArrayList<DataResponseModel>> call = service.getAllDetails(requestList);
            call.enqueue(new Callback<ArrayList<DataResponseModel>>() {
                @Override
                public void onResponse(Call<ArrayList<DataResponseModel>> call, Response<ArrayList<DataResponseModel>> response) {
                    responseData = response.body();
                    if (responseData != null && responseData.size() > 0) {
                        tvNoData.setVisibility(View.GONE);
                        customAdapter.updateData(responseData);
                    } else {
                        //  Toast.makeText(FetchDetailsActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                        tvNoData.setVisibility(View.VISIBLE);
                        tvNoData.setText("No data found");
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<DataResponseModel>> call, Throwable t) {
                    Toast.makeText(FetchDetailsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            tvNoData.setVisibility(View.VISIBLE);
            tvNoData.setText("No network available, Kindly check your network connection.");
        }

    }
}
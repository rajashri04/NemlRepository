package com.neml.qrcodescanner.activity;

import android.content.Intent;
import android.os.Bundle;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.neml.qrcodescanner.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {
    //qr code scanner object
    private IntentIntegrator qrScan;
    private Button btnScan;
    private Button btnFetchDetails;
    private String qrCode;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initUI();

        //intializing scan object
        qrScan = new IntentIntegrator(this);
        qrScan.setCaptureActivity(PortraitCaptureActivity.class);
        qrScan.setBeepEnabled(true);
    }

    private void initUI() {
        tvResult = findViewById(R.id.tvResult);
        btnScan = findViewById(R.id.btnScan);
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initiating the qr code scan
                qrScan.initiateScan();
            }
        });

        btnFetchDetails = findViewById(R.id.btnFetchDetails);
        btnFetchDetails.setVisibility(GONE);
        btnFetchDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(qrCode)) {
                    Toast.makeText(MainActivity.this, "Please scan your QR code first to fetch details", Toast.LENGTH_LONG).show();
                } else {

                    Intent intent = new Intent(getApplicationContext(), FetchDetailsActivity.class);
                    intent.putExtra("QR", qrCode);
                    startActivity(intent);
                }
                // callAPI(qrCode);
            }
        });
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/


    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                tvResult.setText("QRCode -> "+result.getContents());
                qrCode = result.getContents();
                btnFetchDetails.setVisibility(View.VISIBLE);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}
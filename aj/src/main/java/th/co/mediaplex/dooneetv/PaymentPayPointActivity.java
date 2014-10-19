package th.co.mediaplex.dooneetv;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class PaymentPayPointActivity extends Activity {

    private ImageButton mpayImageButton,juspayImageButton,tescolotusImageButton,
            thailandpostImageButton,bigcImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_pay_point);

        mpayImageButton = (ImageButton)findViewById(R.id.mpayImageButton);
        juspayImageButton = (ImageButton)findViewById(R.id.juspayImageButton);
        tescolotusImageButton = (ImageButton)findViewById(R.id.tescolotusImageButton);
        thailandpostImageButton = (ImageButton)findViewById(R.id.thailandpostImageButton);
        bigcImageButton = (ImageButton)findViewById(R.id.bigcImageButton);

        mpayImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPayment(Config.PAYMENT_PAYPOINT_MPAY);
            }
        });
        juspayImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPayment(Config.PAYMENT_PAYPOINT_JUSTPAY);
            }
        });
        tescolotusImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPayment(Config.PAYMENT_PAYPOINT_TESCOLOTUS);
            }
        });
        thailandpostImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPayment(Config.PAYMENT_PAYPOINT_THAILANDPOST);
            }
        });
        bigcImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPayment(Config.PAYMENT_PAYPOINT_BIGC);
            }
        });

    }

    private void submitPayment(int paypoint){
        finish();
    }



}

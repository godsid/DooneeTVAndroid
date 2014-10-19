package th.co.mediaplex.dooneetv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class PaymentBankActivity extends Activity {


    private ImageButton scbImageButton, tmbImageButton,thanachartImageButton,krungsriImageButton
            ,bangkokbankImageButton,uobImageButton,ktbImageButton,kbankImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_bank);

        scbImageButton = (ImageButton)findViewById(R.id.scbImageButton);
        tmbImageButton = (ImageButton)findViewById(R.id.tmbImageButton);
        thanachartImageButton = (ImageButton)findViewById(R.id.thanachartImageButton);
        krungsriImageButton = (ImageButton)findViewById(R.id.krungsriImageButton);
        bangkokbankImageButton = (ImageButton)findViewById(R.id.bangkokbankImageButton);
        uobImageButton = (ImageButton)findViewById(R.id.uobImageButton);
        ktbImageButton = (ImageButton)findViewById(R.id.ktbImageButton);
        kbankImageButton = (ImageButton)findViewById(R.id.kbankImageButton);

        scbImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPayment(Config.PAYMENT_BANK_SCB);
            }
        });
        tmbImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPayment(Config.PAYMENT_BANK_TMB);
            }
        });
        thanachartImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPayment(Config.PAYMENT_BANK_THANACHAR);
            }
        });
        krungsriImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPayment(Config.PAYMENT_BANK_KRUNGSRI);
            }
        });
        bangkokbankImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPayment(Config.PAYMENT_BANK_BANGKOKBANK);
            }
        });
        uobImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPayment(Config.PAYMENT_BANK_UOB);
            }
        });
        ktbImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPayment(Config.PAYMENT_BANK_KTB);
            }
        });
        kbankImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPayment(Config.PAYMENT_BANK_KBANK);
            }
        });
    }

    private void submitPayment(int bank){
        finish();
    }
}

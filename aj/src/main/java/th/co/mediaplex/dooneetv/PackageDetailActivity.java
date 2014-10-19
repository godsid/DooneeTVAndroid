package th.co.mediaplex.dooneetv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import th.co.mediaplex.dooneetv.obj.MoviePackage;


public class PackageDetailActivity extends Activity {
    private static final int REQUEST_PACKAGE_DETAIL = 1 ;
    private static final int RESULT_PAYMENT = 1 ;


    private MoviePackage moviePackage;
    private TextView titleTextView,descriptionTextView;
    private Button creditCardButton,bankCounterButton,atmButton,payPointButton,iBankingButton,prepaidCardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_detail);
        titleTextView = (TextView)findViewById(R.id.titleTextView);
        descriptionTextView = (TextView)findViewById(R.id.descriptionTextView);

        creditCardButton = (Button)findViewById(R.id.creditCardButton);
        bankCounterButton = (Button)findViewById(R.id.bankCounterButton);
        atmButton = (Button)findViewById(R.id.atmButton);
        payPointButton = (Button)findViewById(R.id.payPointButton);
        iBankingButton = (Button)findViewById(R.id.iBankingButton);
        prepaidCardButton = (Button)findViewById(R.id.prepaidCardButton);

        Intent intent = getIntent();
        moviePackage = new MoviePackage(intent.getIntExtra("package_id",0));
        setTitle(intent.getStringExtra("title"));
        titleTextView.setText(intent.getStringExtra("title"));
        descriptionTextView.setText(intent.getStringExtra("conditions"));

        /* Credit Card */
        creditCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PaymentCreditCardActivity.class);
                startActivity(intent);
            }
        });
        /* Bank Counter */
        bankCounterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseBank();
            }
        });
        /* ATM */
        atmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseBank();
            }
        });
        /* Pay Point */
        payPointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PaymentPayPointActivity.class);
                startActivity(intent);
            }
        });

        /*iBanking */
        iBankingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseBank();
            }
        });
        /* prepaid card*/
        prepaidCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PaymentPrepaidCardActivity.class);
                startActivity(intent);
            }
        });
    }

    private void chooseBank(){
        Intent intent = new Intent(getApplicationContext(),PaymentBankActivity.class);
        startActivity(intent);
    }


}

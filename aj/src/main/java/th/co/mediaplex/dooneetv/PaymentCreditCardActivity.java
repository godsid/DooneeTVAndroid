package th.co.mediaplex.dooneetv;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class PaymentCreditCardActivity extends Activity {

    private EditText cardnoEditText,monthEditText,yearEditText,nameEditText,cvvEditText;
    private Button submitButton;
    InputMethodManager inputMethodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_credit_card);

        cardnoEditText = (EditText)findViewById(R.id.cardnoEditText);
        monthEditText = (EditText)findViewById(R.id.monthEditText);
        yearEditText = (EditText)findViewById(R.id.yearEditText);
        nameEditText = (EditText)findViewById(R.id.nameEditText);
        cvvEditText = (EditText)findViewById(R.id.cvvEditText);
        submitButton = (Button)findViewById(R.id.submitButton);

        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateData()){
                    finish();
                }
            }
        });
        submitButton.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });


    }

    private boolean validateData(){
        int month = Integer.parseInt(monthEditText.getText().toString());
        if(cardnoEditText.getText().length()<16){
            cardnoEditText.requestFocus();
            Toast.makeText(getApplicationContext(),R.string.error_payment_creditno_invalid,Toast.LENGTH_SHORT).show();
            return false;
        }

        if(month<1||month>12){
            monthEditText.requestFocus();
            Toast.makeText(getApplicationContext(),R.string.error_payment_creditexpire_invalid,Toast.LENGTH_SHORT).show();
            return false;
        }
        if(yearEditText.getText().length()<4){
            yearEditText.requestFocus();
            Toast.makeText(getApplicationContext(),R.string.error_payment_creditexpire_invalid,Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!nameEditText.getText().toString().matches("^[a-zA-Z]+ [a-zA-Z]+$")){
            nameEditText.requestFocus();
            Toast.makeText(getApplicationContext(),R.string.error_payment_creditname_invalid,Toast.LENGTH_SHORT).show();
            return false;
        }
        if(cvvEditText.getText().length()<3){
            cvvEditText.requestFocus();
            Toast.makeText(getApplicationContext(),R.string.error_payment_creditcvv_invalid,Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}

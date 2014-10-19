package th.co.mediaplex.dooneetv;

import android.app.Activity;
import android.content.Context;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class PaymentPrepaidCardActivity extends Activity {

    private Button submitButton;
    private EditText code1EditText,code2EditText,code3EditText,code4EditText;
    private InputMethodManager inputMethodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_prepaid_card);

        submitButton = (Button)findViewById(R.id.submitButton);
        code1EditText = (EditText)findViewById(R.id.code1EditText);
        code2EditText = (EditText)findViewById(R.id.code2EditText);
        code3EditText = (EditText)findViewById(R.id.code3EditText);
        code4EditText = (EditText)findViewById(R.id.code4EditText);

        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        submitButton.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateCode()){
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),R.string.error_payment_code_invalid,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Boolean validateCode(){
        if(code1EditText.getText().length()<4){
            code1EditText.requestFocus();
            return false;
        }
        if(code2EditText.getText().length()<4){
            code2EditText.requestFocus();
            return false;
        }
        if(code3EditText.getText().length()<4){
            code3EditText.requestFocus();
            return false;
        }
        if(code4EditText.getText().length()<4){
            code4EditText.requestFocus();
            return false;
        }
        return true;
    }
}

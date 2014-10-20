package th.co.mediaplex.dooneetv;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import th.co.mediaplex.dooneetv.obj.Member;
import th.co.mediaplex.dooneetv.obj.ProgressDialogLoading;


public class RegisterActivity extends Activity {

    private EditText emailEditText,passwordEditText,confirmPasswordEditText,
            phoneEditText;
    //,firstNameEditText,lastNameEditText
    private Button registerButton;
    private AQuery aq;
    protected MyApplication myApplication;
    private InputMethodManager inputMethodManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myApplication = (MyApplication)getApplication();
        if(myApplication.isLogin()){
            finish();
        }

        emailEditText = (EditText)findViewById(R.id.emailEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);
        confirmPasswordEditText = (EditText)findViewById(R.id.confirmPasswordEditText);
        //firstNameEditText = (EditText)findViewById(R.id.firstNameEditText);
        //lastNameEditText = (EditText)findViewById(R.id.lastNameEditText);
        phoneEditText = (EditText)findViewById(R.id.phoneEditText);
        registerButton = (Button)findViewById(R.id.registerButton);

        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateData()){
                    register();
                }
            }
        });
        registerButton.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });
    }

    private boolean validateData(){
        if(emailEditText.getText().toString().isEmpty()){
            emailEditText.requestFocus();
            Toast.makeText(getApplicationContext(),R.string.error_email_invalid,Toast.LENGTH_SHORT).show();
            return false;
        }
        if(passwordEditText.getText().toString().isEmpty()||passwordEditText.getText().length()<4){
            emailEditText.requestFocus();
            Toast.makeText(getApplicationContext(),R.string.error_password_invalid,Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!passwordEditText.getText().toString().equals(confirmPasswordEditText.getText().toString())){
            confirmPasswordEditText.requestFocus();
            Toast.makeText(getApplicationContext(),R.string.error_password_missmatch,Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!phoneEditText.getText().toString().matches("^[0-9]{8,11}$")){
            phoneEditText.requestFocus();
            Toast.makeText(getApplicationContext(),R.string.error_phone_invalid,Toast.LENGTH_SHORT).show();
            return false;
        }
        return  true;
    }
    private void register(){
        aq = new AQuery(getApplicationContext());
        String url = Config.urlApiMemberRegister;
        Map<String, Object> params = new HashMap<String, Object>();
        ProgressDialogLoading progressDialogLoading = new ProgressDialogLoading(this);

        params.put("email",emailEditText.getText());
        params.put("password",passwordEditText.getText());
        params.put("phone",phoneEditText.getText());
        params.put("firstname","AJ");
        params.put("lastname","AJ");
        params.put("gender","");
        Log.d(Config.TAG,url);
        aq.progress(progressDialogLoading)
          .ajax(url,params, JSONObject.class,new AjaxCallback<JSONObject>(){
              @Override
              public void callback(String url, JSONObject object, AjaxStatus status) {
                  super.callback(url, object, status);
                  if(object!=null){
                      Log.d(Config.TAG,object.toString());
                      try {
                          if(object.getString("status").equals("success")){
                              myApplication.setMember(new Member(object.getJSONObject("items")));
                              finish();
                          }else{
                              Toast.makeText(getApplicationContext(),object.getString("message"),Toast.LENGTH_SHORT).show();
                          }
                      } catch (JSONException e) {
                          e.printStackTrace();
                      }
                  }else{
                    Toast.makeText(getApplicationContext(),status.getMessage(),Toast.LENGTH_SHORT).show();
                  }
              }
          });
    }

}

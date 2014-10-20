package th.co.mediaplex.dooneetv;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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


public class LoginActivity extends Activity {

    private EditText emailEditText,passwordEditText;
    private Button loginButton;
    private AQuery aq;
    protected MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myApplication = (MyApplication)getApplication();

        if(myApplication.isLogin()){
           finish();
        }

        emailEditText = (EditText)findViewById(R.id.emailEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);
        loginButton = (Button)findViewById(R.id.loginButton);



        /* Test */
        emailEditText.setText("banpot.sr@gmail.com");
        passwordEditText.setText("1234qwer");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateData()){
                    checkLogin();
                }
            }
        });
    }
    private boolean validateData(){
        if(emailEditText.getText().length()<3){
            emailEditText.requestFocus();
            Toast.makeText(getApplicationContext(),R.string.error_login_email_invalid,Toast.LENGTH_SHORT).show();
            return false;
        }
        if(passwordEditText.getText().toString().isEmpty()){
            passwordEditText.requestFocus();
            Toast.makeText(getApplicationContext(),R.string.error_login_password_invalid,Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private void checkLogin(){
        String url = Config.urlApiMemberLogin;
        Map<String, Object> params = new HashMap<String, Object>();
        ProgressDialogLoading progressDialogLoading = new ProgressDialogLoading(this);

        params.put("email",emailEditText.getText());
        params.put("password",passwordEditText.getText());
        Log.d(Config.TAG, url);
        aq = new AQuery(getApplicationContext());
        aq.progress(progressDialogLoading)
          .ajax(url,params, JSONObject.class,new AjaxCallback<JSONObject>(){
              @Override
              public void callback(String url, JSONObject object, AjaxStatus status) {
                  super.callback(url, object, status);
                  if(object!=null){
                      Log.d(Config.TAG,object.toString());
                      try {
                          if(object.getString("status").equals("success")){
                              JSONObject items = object.getJSONObject("items");
                              myApplication.setMember(new Member(items));
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

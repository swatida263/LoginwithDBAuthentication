package android.blog.loginwithdbauthentication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    WebView mWebView;
    //EditText txtUsername, txtPassword;
    TextView tx1;
    int counter = 3;
    //Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
// TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText txtUserName = (EditText) findViewById(R.id.editText);
        final EditText txtPassword = (EditText) findViewById(R.id.editText2);
        final Button btnLogin = (Button) findViewById(R.id.button);
        final Button signup = (Button)findViewById(R.id.button3);
        TextView header = (TextView) findViewById(R.id.textView);
        mWebView = (WebView) findViewById(R.id.webView) ;
        tx1 = (TextView) findViewById(R.id.textView3);
        mWebView.setVisibility(View.INVISIBLE);
        header.setVisibility(View.VISIBLE);

        btnLogin.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String username = txtUserName.getText().toString();
                String password = txtPassword.getText().toString();

                DBUserAdapter dbUser = new DBUserAdapter(MainActivity.this);
                dbUser.open();

                if (dbUser.Login(username, password))
                {
                    Toast.makeText(MainActivity.this, "Successfully Logged In", Toast.LENGTH_LONG).show();
                    signup.setVisibility(View.INVISIBLE);
                    mWebView.setVisibility(View.VISIBLE);
                    mWebView.loadUrl("http://bugwrangler.in");
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Invalid Username/Password", Toast.LENGTH_LONG).show();
                }
                dbUser.close();

                tx1.setVisibility(View.VISIBLE);
                counter--;
                tx1.setText(Integer.toString(counter));

                if (counter == 0) {
                    btnLogin.setEnabled(false);
                }
                dbUser.close();
            }

        }) ;

        signup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                launchSignUpActivity(v) ;
            }
        });}


        private void launchSignUpActivity(View v)
    {
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }
    }

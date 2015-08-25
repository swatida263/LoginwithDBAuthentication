package android.blog.loginwithdbauthentication;

import android.view.View.OnClickListener;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class SignUpActivity extends ActionBarActivity {
 EditText NAME, PASSWORD;
    Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        NAME = (EditText) findViewById(R.id.editText);
        PASSWORD = (EditText) findViewById(R.id.editText2);
        signUpBtn = (Button) findViewById(R.id.button);

        signUpBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = NAME.getText().toString();
                String password = PASSWORD.getText().toString();

                DBUserAdapter dbUser = new DBUserAdapter(SignUpActivity.this);
                dbUser.open();
                dbUser.AddUser(username, password);
                dbUser.close();

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
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
    }
}

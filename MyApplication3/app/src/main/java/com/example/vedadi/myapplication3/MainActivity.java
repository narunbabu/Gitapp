package com.example.vedadi.myapplication3;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mPasswordView;
    private EditText mEmailView;
    private TextView mResultView;
    private UserLoginTask mAuthTask = null;
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "ameyem:ab@123", "ab@ameyem.com:ab@123", "ab:ab@123"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmailView =  (EditText) findViewById(R.id.email_ab); // Dpends on edit text id

        mPasswordView = (EditText) findViewById(R.id.pwd_ab); // Dpends on edit text id

        mResultView=(TextView) findViewById(R.id.result_ab) ;


        Button mEmailSignInButton = (Button) findViewById(R.id.submit_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }



    private void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        mAuthTask = new UserLoginTask(email, password);
        mAuthTask.execute((Void) null);


    }

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            //mResultView.setText("Failed");
            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);


                }

            }

            // TODO: register the new account here.
            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            //showProgress(false);

            if (success) {
                mResultView.setText("Success");
            } else {
                mResultView.setText("Failed");
            }
        }


    }

}

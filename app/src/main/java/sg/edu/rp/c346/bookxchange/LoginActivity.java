package sg.edu.rp.c346.bookxchange;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

import static sg.edu.rp.c346.bookxchange.R.id.txtEmail;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

//    /**
//     * Id to identity READ_CONTACTS permission request.
//     */
//    private static final int REQUEST_READ_CONTACTS = 0;
//
//    /**
//     * A dummy authentication store containing known user names and passwords.
//     * TODO: remove after connecting to a real authentication system.
//     */
//    private static final String[] DUMMY_CREDENTIALS = new String[]{
//            "foo@example.com:hello", "bar@example.com:world"
//    };
//    /**
//     * Keep track of the login task to ensure we can cancel it if requested.
//     */
//
//
//    // UI references.

    private EditText mPasswordView, mEmail;
    private View mProgressView;
    private View mLoginFormView;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmail = (EditText) findViewById(txtEmail);
        mPasswordView = (EditText) findViewById(R.id.txtPassword);


     //   Button btnLogin = (Button) findViewById(R.id.btnLogin);
        Button btnRegister = (Button) findViewById(R.id.btnRegistration);
        firebaseAuth = FirebaseAuth.getInstance();


//        btnLogin.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String Email = mEmail.getText().toString();
//                String Pwd = mPasswordView.getText().toString();
//
//                if (Email.equalsIgnoreCase("xvannjc98@gmail.com") && Pwd.equals("12345")) {
//                    Intent MainIntent = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(MainIntent);
//                    Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(LoginActivity.this, "Sorry, Email or Password is Incorrect.", Toast.LENGTH_LONG).show();
//                }
//            }
//        });


        btnRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MainIntent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(MainIntent);
            }
        });



        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    public void btnLogin_Click(View v) {

        String email = mEmail.getText().toString().trim();
        String pass = mPasswordView.getText().toString();

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("Email format is incorrect");
//            return;
        }
        else if(TextUtils.isEmpty(pass) || pass.length() < 8)
        {
           mPasswordView.setError("You must have 8 characters in your password");
//            return;
        }else {
            final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this, "Please wait...", "Processing...", true);
            (firebaseAuth.signInWithEmailAndPassword(mEmail.getText().toString(), mPasswordView.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();

                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                        startActivity(i);
                    } else {
                        Log.e("Error", task.getException().toString());
                        Toast.makeText(LoginActivity.this, "Sorry, your email and/or password is invalid.", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }

}
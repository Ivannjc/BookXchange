package sg.edu.rp.c346.bookxchange;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private EditText txtEmailAddressRegi;
    private EditText txtPasswordRegi;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        txtEmailAddressRegi = (EditText) findViewById(R.id.txtEmailRegistration);
        txtPasswordRegi = (EditText) findViewById(R.id.txtPasswordRegistration);
        firebaseAuth = FirebaseAuth.getInstance();
    }
    public void btnRegistrationUser_Click(View v) {

        String email = txtEmailAddressRegi.getText().toString().trim();
        String pass = txtPasswordRegi.getText().toString();
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            txtEmailAddressRegi.setError("Email format is incorrect");
            return;
        }
        else if(TextUtils.isEmpty(pass) || pass.length() < 8)
        {
            txtPasswordRegi.setError("Password not strong enough, You must have 8 characters in your password");
            return;
        }else {


            final ProgressDialog progressDialog = ProgressDialog.show(RegistrationActivity.this, "Please Wait...", "Processing...", true);
            (firebaseAuth.createUserWithEmailAndPassword(txtEmailAddressRegi.getText().toString(), txtPasswordRegi.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();

                    if (task.isSuccessful()) {
                        Toast.makeText(RegistrationActivity.this, "Registration successful", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                        startActivity(i);
                    } else {
                        Log.e("Error", task.getException().toString());
                        Toast.makeText(RegistrationActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}

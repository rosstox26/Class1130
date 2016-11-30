package com.ateachr.tox26.class1130;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ScoreUpdate extends Activity implements View.OnClickListener{

    private Button buttonUpload;
    private EditText editTextScore;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_update);

        buttonUpload = (Button) findViewById(R.id.buttonUpload);
        buttonUpload.setOnClickListener(this);
        editTextScore = (EditText) findViewById(R.id.editTextScore);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in

                } else {
                    // User is signed out
                                  }
                // ...
            }
        };
    }

    @Override
    public void onClick(View view) {


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("scores");

        FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();

        Toast.makeText(this, "Current user:" + currentuser.getEmail(), Toast.LENGTH_SHORT).show();
        String score = editTextScore.getText().toString();

        User user = new User(score, currentuser.getEmail());
        myRef.child(currentuser.getUid().toString()).setValue(user);

    }
}

package com.example.practical2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent in = getIntent();
        String name = in.getStringExtra("name");
        String description = in.getStringExtra("description");
        int id = in.getIntExtra("id",0);
        boolean value = in.getExtras().getBoolean("followed");
        user = new User(name, description, id, value);

        DBHandler db = new DBHandler(this);

        TextView nameTxt = findViewById(R.id.nameTxt);
        nameTxt.setText(user.getName());

        TextView desTxt = findViewById(R.id.desTxt);
        desTxt.setText(user.getDescription());

        Button followBtn = findViewById(R.id.followBtn);
        Log.d(null, String.valueOf(value));
        if(user.getFollowed()){
            followBtn.setText("Followed");
        }
        else{
            followBtn.setText("Follow");
        }
        followBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                user.Change();
                db.updateUser(user);
                if(user.getFollowed()){
                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_LONG).show();
                    followBtn.setText("Unfollow");
                }
                else{
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_LONG).show();
                    followBtn.setText("Follow");
                }
            }
        });

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent in = new Intent(MainActivity.this, MainActivity2.class);

                EditText txt = findViewById(R.id.input);
                String userInput = txt.getText().toString();

                startActivity(in);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
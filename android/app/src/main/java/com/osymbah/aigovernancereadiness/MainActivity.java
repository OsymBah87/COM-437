package com.osymbah.aigovernancereadiness;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.startAssessmentButton);
        Button savedButton = findViewById(R.id.savedAssessmentsButton);

        startButton.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, SetupActivity.class)));

        savedButton.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, SavedAssessmentsActivity.class)));
    }
}

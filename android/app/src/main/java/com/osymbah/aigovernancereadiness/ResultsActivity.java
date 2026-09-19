package com.osymbah.aigovernancereadiness;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Button homeButton = findViewById(R.id.resultsHomeButton);
        Button savedButton = findViewById(R.id.resultsSavedButton);

        homeButton.setOnClickListener(view -> {
            Intent intent = new Intent(ResultsActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        savedButton.setOnClickListener(view ->
                startActivity(new Intent(ResultsActivity.this, SavedAssessmentsActivity.class)));
    }
}

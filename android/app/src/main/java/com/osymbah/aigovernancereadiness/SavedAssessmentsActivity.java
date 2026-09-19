package com.osymbah.aigovernancereadiness;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SavedAssessmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_assessments);

        Button backButton = findViewById(R.id.savedBackButton);
        backButton.setOnClickListener(view -> finish());
    }
}

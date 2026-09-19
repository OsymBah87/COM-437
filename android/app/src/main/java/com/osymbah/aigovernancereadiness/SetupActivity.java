package com.osymbah.aigovernancereadiness;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SetupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        Button backButton = findViewById(R.id.setupBackButton);
        Button nextButton = findViewById(R.id.setupNextButton);

        backButton.setOnClickListener(view -> finish());
        nextButton.setOnClickListener(view ->
                startActivity(new Intent(SetupActivity.this, QuestionActivity.class)));
    }
}

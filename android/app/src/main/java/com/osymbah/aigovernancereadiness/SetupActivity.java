package com.osymbah.aigovernancereadiness;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SetupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        Button backButton = findViewById(R.id.setupBackButton);
        Button nextButton = findViewById(R.id.setupNextButton);
        EditText systemNameInput = findViewById(R.id.systemNameInput);
        EditText systemPurposeInput = findViewById(R.id.systemPurposeInput);
        EditText organizationInput = findViewById(R.id.organizationInput);

        backButton.setOnClickListener(view -> finish());
        nextButton.setOnClickListener(view -> {
            String systemName = systemNameInput.getText().toString().trim();
            String systemPurpose = systemPurposeInput.getText().toString().trim();
            String organization = organizationInput.getText().toString().trim();

            if (systemName.isEmpty()) {
                systemNameInput.setError(getString(R.string.system_name_required));
                return;
            }
            if (systemPurpose.isEmpty()) {
                systemPurposeInput.setError(getString(R.string.system_purpose_required));
                return;
            }

            Intent intent = new Intent(SetupActivity.this, QuestionActivity.class);
            intent.putExtra(QuestionActivity.EXTRA_SYSTEM_NAME, systemName);
            intent.putExtra(QuestionActivity.EXTRA_SYSTEM_PURPOSE, systemPurpose);
            intent.putExtra(QuestionActivity.EXTRA_ORGANIZATION, organization);
            startActivity(intent);
        });
    }
}

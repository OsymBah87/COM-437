package com.osymbah.aigovernancereadiness;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SavedAssessmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_assessments);

        Button backButton = findViewById(R.id.savedBackButton);
        backButton.setOnClickListener(view -> finish());
        showSavedAssessments();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showSavedAssessments();
    }

    private void showSavedAssessments() {
        LinearLayout list = findViewById(R.id.savedAssessmentList);
        TextView emptyMessage = findViewById(R.id.savedEmptyMessage);
        list.removeAllViews();

        java.util.List<AssessmentRecord> records = new AssessmentDatabaseHelper(this).getAll();
        emptyMessage.setVisibility(records.isEmpty() ? android.view.View.VISIBLE : android.view.View.GONE);

        java.text.DateFormat dateFormat = java.text.DateFormat.getDateTimeInstance(
                java.text.DateFormat.MEDIUM,
                java.text.DateFormat.SHORT);
        for (AssessmentRecord record : records) {
            Button assessmentButton = (Button) getLayoutInflater().inflate(
                    R.layout.saved_assessment_item,
                    list,
                    false);
            assessmentButton.setAllCaps(false);
            assessmentButton.setText(getString(
                    R.string.saved_assessment_format,
                    record.getSystemName(),
                    record.getOverallScore(),
                    record.getReadinessLevel(),
                    dateFormat.format(new java.util.Date(record.getCreatedAt()))));
            assessmentButton.setOnClickListener(view -> {
                Intent intent = new Intent(this, ResultsActivity.class);
                intent.putExtra(ResultsActivity.EXTRA_ASSESSMENT_ID, record.getId());
                startActivity(intent);
            });
            list.addView(assessmentButton);
        }
    }
}

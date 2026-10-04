package com.osymbah.aigovernancereadiness;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity {
    public static final String EXTRA_ASSESSMENT_ID = "assessment_id";
    private AssessmentDatabaseHelper databaseHelper;
    private AssessmentRecord currentRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        databaseHelper = new AssessmentDatabaseHelper(this);
        Button homeButton = findViewById(R.id.resultsHomeButton);
        Button savedButton = findViewById(R.id.resultsSavedButton);
        Button saveButton = findViewById(R.id.resultsSaveButton);
        long assessmentId = getIntent().getLongExtra(EXTRA_ASSESSMENT_ID, -1);

        if (assessmentId != -1) {
            currentRecord = databaseHelper.getById(assessmentId);
            saveButton.setEnabled(false);
            saveButton.setText(R.string.assessment_saved);
        } else {
            currentRecord = createRecordFromAssessment();
        }

        if (currentRecord == null) {
            Toast.makeText(this, R.string.assessment_not_found, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        displayRecord(currentRecord);

        saveButton.setOnClickListener(view -> {
            long savedId = databaseHelper.insert(currentRecord);
            if (savedId == -1) {
                Toast.makeText(this, R.string.save_failed, Toast.LENGTH_SHORT).show();
                return;
            }
            saveButton.setEnabled(false);
            saveButton.setText(R.string.assessment_saved);
            Toast.makeText(this, R.string.save_successful, Toast.LENGTH_SHORT).show();
        });

        homeButton.setOnClickListener(view -> {
            Intent intent = new Intent(ResultsActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        savedButton.setOnClickListener(view ->
                startActivity(new Intent(ResultsActivity.this, SavedAssessmentsActivity.class)));
    }

    private AssessmentRecord createRecordFromAssessment() {
        java.util.ArrayList<Integer> responses = getIntent().getIntegerArrayListExtra(QuestionActivity.EXTRA_RESPONSES);
        if (responses == null || responses.size() != QuestionBank.getQuestions().size()) {
            return null;
        }

        AssessmentResult result = AssessmentScorer.score(QuestionBank.getQuestions(), responses);
        java.util.Map<String, Integer> scores = result.getCategoryScores();
        return new AssessmentRecord(
                -1,
                valueOrDefault(getIntent().getStringExtra(QuestionActivity.EXTRA_SYSTEM_NAME), getString(R.string.unnamed_system)),
                valueOrDefault(getIntent().getStringExtra(QuestionActivity.EXTRA_SYSTEM_PURPOSE), getString(R.string.not_provided)),
                valueOrDefault(getIntent().getStringExtra(QuestionActivity.EXTRA_ORGANIZATION), getString(R.string.not_provided)),
                System.currentTimeMillis(),
                encodeResponses(responses),
                scores.get("Security"),
                scores.get("Privacy"),
                scores.get("Fairness"),
                scores.get("Transparency"),
                scores.get("Accountability"),
                scores.get("Human Oversight"),
                result.getOverallScore(),
                result.getReadinessLevel());
    }

    private void displayRecord(AssessmentRecord record) {
        TextView summaryText = findViewById(R.id.resultsSummaryText);
        TextView categoryText = findViewById(R.id.resultsCategoryText);
        TextView recommendationsText = findViewById(R.id.resultsRecommendationsText);

        summaryText.setText(getString(
                R.string.results_summary_format,
                record.getSystemName(),
                record.getOverallScore(),
                record.getReadinessLevel()));
        categoryText.setText(getString(
                R.string.results_categories_format,
                record.getSecurityScore(),
                record.getPrivacyScore(),
                record.getFairnessScore(),
                record.getTransparencyScore(),
                record.getAccountabilityScore(),
                record.getOversightScore()));
        recommendationsText.setText(buildRecommendations(record));
    }

    private String buildRecommendations(AssessmentRecord record) {
        StringBuilder recommendations = new StringBuilder(getString(R.string.recommendations_heading));
        appendRecommendation(recommendations, "Security", record.getSecurityScore(), R.string.security_recommendation);
        appendRecommendation(recommendations, "Privacy", record.getPrivacyScore(), R.string.privacy_recommendation);
        appendRecommendation(recommendations, "Fairness", record.getFairnessScore(), R.string.fairness_recommendation);
        appendRecommendation(recommendations, "Transparency", record.getTransparencyScore(), R.string.transparency_recommendation);
        appendRecommendation(recommendations, "Accountability", record.getAccountabilityScore(), R.string.accountability_recommendation);
        appendRecommendation(recommendations, "Human Oversight", record.getOversightScore(), R.string.oversight_recommendation);
        if (recommendations.toString().equals(getString(R.string.recommendations_heading))) {
            recommendations.append("\n").append(getString(R.string.no_priority_recommendations));
        }
        return recommendations.toString();
    }

    private void appendRecommendation(StringBuilder builder, String category, int score, int messageId) {
        if (score < 75) {
            builder.append("\n\n").append(category).append(": ").append(getString(messageId));
        }
    }

    private String encodeResponses(java.util.List<Integer> responses) {
        StringBuilder encoded = new StringBuilder();
        for (int index = 0; index < responses.size(); index++) {
            if (index > 0) {
                encoded.append(",");
            }
            encoded.append(responses.get(index));
        }
        return encoded.toString();
    }

    private String valueOrDefault(String value, String defaultValue) {
        return value == null || value.trim().isEmpty() ? defaultValue : value.trim();
    }
}

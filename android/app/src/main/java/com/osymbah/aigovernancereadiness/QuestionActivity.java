package com.osymbah.aigovernancereadiness;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {
    public static final String EXTRA_SYSTEM_NAME = "system_name";
    public static final String EXTRA_SYSTEM_PURPOSE = "system_purpose";
    public static final String EXTRA_ORGANIZATION = "organization";
    public static final String EXTRA_RESPONSES = "responses";
    private static final String STATE_QUESTION_INDEX = "question_index";
    private static final String STATE_RESPONSES = "responses";

    private List<GovernanceQuestion> questions;
    private ArrayList<Integer> selectedResponses;
    private int currentQuestionIndex = 0;

    private TextView categoryText;
    private TextView questionText;
    private TextView progressText;
    private ProgressBar progressBar;
    private RadioGroup responseGroup;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        questions = QuestionBank.getQuestions();
        if (savedInstanceState != null) {
            currentQuestionIndex = savedInstanceState.getInt(STATE_QUESTION_INDEX, 0);
            selectedResponses = savedInstanceState.getIntegerArrayList(STATE_RESPONSES);
        }

        if (selectedResponses == null || selectedResponses.size() != questions.size()) {
            selectedResponses = new ArrayList<>(questions.size());
            for (int index = 0; index < questions.size(); index++) {
                selectedResponses.add(-1);
            }
        }

        categoryText = findViewById(R.id.questionCategoryText);
        questionText = findViewById(R.id.questionText);
        progressText = findViewById(R.id.questionProgressText);
        progressBar = findViewById(R.id.questionProgressBar);
        responseGroup = findViewById(R.id.responseGroup);
        nextButton = findViewById(R.id.questionNextButton);
        Button backButton = findViewById(R.id.questionBackButton);

        progressBar.setMax(questions.size());
        showCurrentQuestion();

        backButton.setOnClickListener(view -> {
            saveCurrentResponse();
            if (currentQuestionIndex == 0) {
                finish();
                return;
            }

            currentQuestionIndex--;
            showCurrentQuestion();
        });

        nextButton.setOnClickListener(view -> {
            if (responseGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, R.string.select_response_message, Toast.LENGTH_SHORT).show();
                return;
            }

            saveCurrentResponse();
            if (currentQuestionIndex == questions.size() - 1) {
                Intent intent = new Intent(QuestionActivity.this, ResultsActivity.class);
                intent.putExtra(EXTRA_SYSTEM_NAME, getIntent().getStringExtra(EXTRA_SYSTEM_NAME));
                intent.putExtra(EXTRA_SYSTEM_PURPOSE, getIntent().getStringExtra(EXTRA_SYSTEM_PURPOSE));
                intent.putExtra(EXTRA_ORGANIZATION, getIntent().getStringExtra(EXTRA_ORGANIZATION));
                intent.putIntegerArrayListExtra(EXTRA_RESPONSES, selectedResponses);
                startActivity(intent);
                return;
            }

            currentQuestionIndex++;
            showCurrentQuestion();
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        saveCurrentResponse();
        outState.putInt(STATE_QUESTION_INDEX, currentQuestionIndex);
        outState.putIntegerArrayList(STATE_RESPONSES, selectedResponses);
        super.onSaveInstanceState(outState);
    }

    private void showCurrentQuestion() {
        GovernanceQuestion question = questions.get(currentQuestionIndex);
        categoryText.setText(question.getCategory());
        questionText.setText(question.getText());
        progressText.setText(getString(
                R.string.question_progress_format,
                currentQuestionIndex + 1,
                questions.size()));
        progressBar.setProgress(currentQuestionIndex + 1);

        responseGroup.clearCheck();
        int savedResponse = selectedResponses.get(currentQuestionIndex);
        if (savedResponse != -1) {
            responseGroup.check(savedResponse);
        }

        nextButton.setText(currentQuestionIndex == questions.size() - 1
                ? R.string.view_results
                : R.string.next_question);
    }

    private void saveCurrentResponse() {
        selectedResponses.set(currentQuestionIndex, responseGroup.getCheckedRadioButtonId());
    }
}

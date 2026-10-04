package com.osymbah.aigovernancereadiness;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AssessmentScorerTest {
    @Test
    public void allYesProducesOneHundredPercent() {
        List<GovernanceQuestion> questions = QuestionBank.getQuestions();
        ArrayList<Integer> responses = repeatedResponse(questions.size(), R.id.responseYes);
        AssessmentResult result = AssessmentScorer.score(questions, responses);

        assertEquals(100, result.getOverallScore());
        assertEquals("Higher Readiness", result.getReadinessLevel());
        for (int score : result.getCategoryScores().values()) {
            assertEquals(100, score);
        }
    }

    @Test
    public void allNoProducesZeroPercent() {
        List<GovernanceQuestion> questions = QuestionBank.getQuestions();
        AssessmentResult result = AssessmentScorer.score(
                questions,
                repeatedResponse(questions.size(), R.id.responseNo));

        assertEquals(0, result.getOverallScore());
        assertEquals("Needs Attention", result.getReadinessLevel());
    }

    @Test
    public void allPartlyProducesSixtySevenPercent() {
        List<GovernanceQuestion> questions = QuestionBank.getQuestions();
        AssessmentResult result = AssessmentScorer.score(
                questions,
                repeatedResponse(questions.size(), R.id.responsePartly));

        assertEquals(67, result.getOverallScore());
        assertEquals("Developing Readiness", result.getReadinessLevel());
    }

    @Test
    public void mixedResponsesProduceExpectedScores() {
        List<GovernanceQuestion> questions = QuestionBank.getQuestions();
        ArrayList<Integer> responses = new ArrayList<>();
        for (int index = 0; index < questions.size(); index += 3) {
            responses.add(R.id.responseYes);
            responses.add(R.id.responsePartly);
            responses.add(R.id.responseNo);
        }

        AssessmentResult result = AssessmentScorer.score(questions, responses);

        assertEquals(56, result.getOverallScore());
        assertEquals("Developing Readiness", result.getReadinessLevel());
        for (int score : result.getCategoryScores().values()) {
            assertEquals(56, score);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void missingResponseIsRejected() {
        List<GovernanceQuestion> questions = QuestionBank.getQuestions();
        AssessmentScorer.score(
                questions,
                repeatedResponse(questions.size() - 1, R.id.responseYes));
    }

    @Test(expected = IllegalArgumentException.class)
    public void unknownResponseIsRejected() {
        List<GovernanceQuestion> questions = QuestionBank.getQuestions();
        ArrayList<Integer> responses = repeatedResponse(questions.size(), R.id.responseYes);
        responses.set(0, -1);
        AssessmentScorer.score(questions, responses);
    }

    private ArrayList<Integer> repeatedResponse(int count, int response) {
        ArrayList<Integer> responses = new ArrayList<>();
        for (int index = 0; index < count; index++) {
            responses.add(response);
        }
        return responses;
    }
}

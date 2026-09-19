package com.osymbah.aigovernancereadiness;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class QuestionBankTest {
    @Test
    public void questionBankContainsThreeQuestionsPerPlannedCategory() {
        List<GovernanceQuestion> questions = QuestionBank.getQuestions();
        Map<String, Integer> categoryCounts = new HashMap<>();

        for (GovernanceQuestion question : questions) {
            assertFalse(question.getCategory().trim().isEmpty());
            assertFalse(question.getText().trim().isEmpty());
            categoryCounts.put(
                    question.getCategory(),
                    categoryCounts.getOrDefault(question.getCategory(), 0) + 1);
        }

        assertEquals(18, questions.size());
        assertEquals(6, categoryCounts.size());
        assertEquals(Integer.valueOf(3), categoryCounts.get("Security"));
        assertEquals(Integer.valueOf(3), categoryCounts.get("Privacy"));
        assertEquals(Integer.valueOf(3), categoryCounts.get("Fairness"));
        assertEquals(Integer.valueOf(3), categoryCounts.get("Transparency"));
        assertEquals(Integer.valueOf(3), categoryCounts.get("Accountability"));
        assertEquals(Integer.valueOf(3), categoryCounts.get("Human Oversight"));
    }
}

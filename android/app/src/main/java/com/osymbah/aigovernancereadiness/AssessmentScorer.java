package com.osymbah.aigovernancereadiness;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class AssessmentScorer {
    private AssessmentScorer() {
    }

    public static AssessmentResult score(List<GovernanceQuestion> questions, List<Integer> responses) {
        if (questions == null || responses == null || questions.isEmpty()) {
            throw new IllegalArgumentException("Questions and responses are required.");
        }
        if (questions.size() != responses.size()) {
            throw new IllegalArgumentException("Each question must have one response.");
        }

        LinkedHashMap<String, Integer> earned = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> possible = new LinkedHashMap<>();

        for (int index = 0; index < questions.size(); index++) {
            String category = questions.get(index).getCategory();
            earned.put(category, earned.getOrDefault(category, 0) + pointsFor(responses.get(index)));
            possible.put(category, possible.getOrDefault(category, 0) + 3);
        }

        LinkedHashMap<String, Integer> categoryScores = new LinkedHashMap<>();
        int totalEarned = 0;
        int totalPossible = 0;
        for (Map.Entry<String, Integer> entry : earned.entrySet()) {
            int categoryPossible = possible.get(entry.getKey());
            categoryScores.put(entry.getKey(), percentage(entry.getValue(), categoryPossible));
            totalEarned += entry.getValue();
            totalPossible += categoryPossible;
        }

        int overallScore = percentage(totalEarned, totalPossible);
        return new AssessmentResult(categoryScores, overallScore, readinessLevel(overallScore));
    }

    private static int pointsFor(int responseId) {
        if (responseId == R.id.responseYes) {
            return 3;
        }
        if (responseId == R.id.responsePartly) {
            return 2;
        }
        if (responseId == R.id.responseNotSure) {
            return 1;
        }
        if (responseId == R.id.responseNo) {
            return 0;
        }
        throw new IllegalArgumentException("Unknown assessment response.");
    }

    private static int percentage(int earned, int possible) {
        return Math.round((earned * 100f) / possible);
    }

    private static String readinessLevel(int score) {
        if (score >= 75) {
            return "Higher Readiness";
        }
        if (score >= 50) {
            return "Developing Readiness";
        }
        return "Needs Attention";
    }
}

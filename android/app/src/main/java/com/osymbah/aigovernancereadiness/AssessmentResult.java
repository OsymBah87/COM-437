package com.osymbah.aigovernancereadiness;

import java.util.LinkedHashMap;
import java.util.Map;

public class AssessmentResult {
    private final LinkedHashMap<String, Integer> categoryScores;
    private final int overallScore;
    private final String readinessLevel;

    public AssessmentResult(Map<String, Integer> categoryScores, int overallScore, String readinessLevel) {
        this.categoryScores = new LinkedHashMap<>(categoryScores);
        this.overallScore = overallScore;
        this.readinessLevel = readinessLevel;
    }

    public LinkedHashMap<String, Integer> getCategoryScores() {
        return new LinkedHashMap<>(categoryScores);
    }

    public int getOverallScore() {
        return overallScore;
    }

    public String getReadinessLevel() {
        return readinessLevel;
    }
}

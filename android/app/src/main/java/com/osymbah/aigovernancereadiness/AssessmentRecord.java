package com.osymbah.aigovernancereadiness;

public class AssessmentRecord {
    private final long id;
    private final String systemName;
    private final String systemPurpose;
    private final String organization;
    private final long createdAt;
    private final String responses;
    private final int securityScore;
    private final int privacyScore;
    private final int fairnessScore;
    private final int transparencyScore;
    private final int accountabilityScore;
    private final int oversightScore;
    private final int overallScore;
    private final String readinessLevel;

    public AssessmentRecord(long id, String systemName, String systemPurpose, String organization,
                            long createdAt, String responses, int securityScore, int privacyScore,
                            int fairnessScore, int transparencyScore, int accountabilityScore,
                            int oversightScore, int overallScore, String readinessLevel) {
        this.id = id;
        this.systemName = systemName;
        this.systemPurpose = systemPurpose;
        this.organization = organization;
        this.createdAt = createdAt;
        this.responses = responses;
        this.securityScore = securityScore;
        this.privacyScore = privacyScore;
        this.fairnessScore = fairnessScore;
        this.transparencyScore = transparencyScore;
        this.accountabilityScore = accountabilityScore;
        this.oversightScore = oversightScore;
        this.overallScore = overallScore;
        this.readinessLevel = readinessLevel;
    }

    public long getId() { return id; }
    public String getSystemName() { return systemName; }
    public String getSystemPurpose() { return systemPurpose; }
    public String getOrganization() { return organization; }
    public long getCreatedAt() { return createdAt; }
    public String getResponses() { return responses; }
    public int getSecurityScore() { return securityScore; }
    public int getPrivacyScore() { return privacyScore; }
    public int getFairnessScore() { return fairnessScore; }
    public int getTransparencyScore() { return transparencyScore; }
    public int getAccountabilityScore() { return accountabilityScore; }
    public int getOversightScore() { return oversightScore; }
    public int getOverallScore() { return overallScore; }
    public String getReadinessLevel() { return readinessLevel; }
}

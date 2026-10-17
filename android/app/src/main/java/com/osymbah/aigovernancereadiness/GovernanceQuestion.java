package com.osymbah.aigovernancereadiness;

public class GovernanceQuestion {
    private final String category;
    private final String text;
    private final String nistCoreMapping;

    public GovernanceQuestion(String category, String text, String nistCoreMapping) {
        this.category = category;
        this.text = text;
        this.nistCoreMapping = nistCoreMapping;
    }

    public String getCategory() {
        return category;
    }

    public String getText() {
        return text;
    }

    public String getNistCoreMapping() {
        return nistCoreMapping;
    }
}

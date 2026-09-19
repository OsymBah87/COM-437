package com.osymbah.aigovernancereadiness;

/**
 * A single draft question used by the educational QuickCheck AI prototype.
 */
public class GovernanceQuestion {
    private final String category;
    private final String text;

    public GovernanceQuestion(String category, String text) {
        this.category = category;
        this.text = text;
    }

    public String getCategory() {
        return category;
    }

    public String getText() {
        return text;
    }
}

package com.osymbah.aigovernancereadiness;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class QuestionBank {
    private QuestionBank() {
    }

    public static List<GovernanceQuestion> getQuestions() {
        return Collections.unmodifiableList(Arrays.asList(
                new GovernanceQuestion("Security",
                        "Are access controls in place to limit who can use the AI system and its data?"),
                new GovernanceQuestion("Security",
                        "Have security threats and vulnerabilities affecting the AI system been assessed?"),
                new GovernanceQuestion("Security",
                        "Is there a process for reporting and responding to security incidents?"),

                new GovernanceQuestion("Privacy",
                        "Has the personal or sensitive information used by the AI system been identified?"),
                new GovernanceQuestion("Privacy",
                        "Is each type of personal information collected for a documented purpose?"),
                new GovernanceQuestion("Privacy",
                        "Are retention and secure deletion requirements defined for personal information?"),

                new GovernanceQuestion("Fairness",
                        "Have groups that could be affected differently by the AI system been identified?"),
                new GovernanceQuestion("Fairness",
                        "Has the AI system been tested to compare outcomes across relevant groups?"),
                new GovernanceQuestion("Fairness",
                        "Is there a documented plan to monitor outcomes for signs of unfair bias after deployment?"),

                new GovernanceQuestion("Transparency",
                        "Are the intended purpose, expected users, and known limitations of the AI system documented?"),
                new GovernanceQuestion("Transparency",
                        "Will users be informed when they are interacting with or affected by an AI system?"),
                new GovernanceQuestion("Transparency",
                        "Can important AI outputs be explained to affected users in understandable language?"),

                new GovernanceQuestion("Accountability",
                        "Has an owner been assigned responsibility for the AI system's decisions and outcomes?"),
                new GovernanceQuestion("Accountability",
                        "Are approval and escalation responsibilities documented for the AI system?"),
                new GovernanceQuestion("Accountability",
                        "Are important decisions, approvals, and system changes recorded for later review?"),

                new GovernanceQuestion("Human Oversight",
                        "Can a qualified person review or challenge important AI-assisted decisions?"),
                new GovernanceQuestion("Human Oversight",
                        "Can authorized personnel pause or stop the system when a serious concern is identified?"),
                new GovernanceQuestion("Human Oversight",
                        "Have responsible personnel received training for their oversight duties?")
        ));
    }
}

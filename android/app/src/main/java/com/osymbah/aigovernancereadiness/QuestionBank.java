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
                        "Are access and change controls defined for the AI system, its data, and relevant third-party components?",
                        "MAP 4.1, MAP 4.2"),
                new GovernanceQuestion("Security",
                        "Have security and resilience risks been assessed and documented for the system's intended context?",
                        "MEASURE 2.7"),
                new GovernanceQuestion("Security",
                        "Are responsibilities and procedures defined for responding to, recovering from, and communicating AI-related incidents or errors?",
                        "MANAGE 4.1, MANAGE 4.3"),

                new GovernanceQuestion("Privacy",
                        "Have privacy requirements for the AI system and its data use been identified and documented with relevant stakeholders?",
                        "MAP 1.6"),
                new GovernanceQuestion("Privacy",
                        "Have privacy risks associated with the system been assessed and documented?",
                        "MEASURE 2.10"),
                new GovernanceQuestion("Privacy",
                        "Are controls for collecting, using, retaining, sharing, and securely deleting relevant data defined and reviewed?",
                        "MAP 1.6, MEASURE 1.2, MEASURE 2.10"),

                new GovernanceQuestion("Fairness",
                        "Have potentially affected groups and the system's foreseeable positive and negative impacts been identified and documented?",
                        "MAP 5.1"),
                new GovernanceQuestion("Fairness",
                        "Has the system been evaluated for fairness and bias in conditions relevant to its intended use, with methods and results documented?",
                        "MEASURE 2.1, MEASURE 2.11"),
                new GovernanceQuestion("Fairness",
                        "Are responsibilities and procedures in place to track fairness-related risks and unexpected impacts after deployment?",
                        "GOVERN 1.5, MEASURE 3.1, MANAGE 4.1"),

                new GovernanceQuestion("Transparency",
                        "Are the system's intended purpose, context, expected users, benefits, limitations, and foreseeable uses documented?",
                        "MAP 1.1, MAP 3.1, MAP 3.2"),
                new GovernanceQuestion("Transparency",
                        "Are relevant users and affected people given information about the system's role, intended use, and important limitations?",
                        "MAP 2.2, GOVERN 4.2"),
                new GovernanceQuestion("Transparency",
                        "Is information about system outputs and limitations documented so people can interpret outputs appropriately?",
                        "MAP 2.2, MEASURE 2.9"),

                new GovernanceQuestion("Accountability",
                        "Are AI risk-management roles, responsibilities, decision authority, and communication paths documented?",
                        "GOVERN 2.1, GOVERN 2.3"),
                new GovernanceQuestion("Accountability",
                        "Are risk tolerances and the criteria and authority for proceeding with, changing, or stopping the system documented?",
                        "GOVERN 1.3, GOVERN 1.4, MANAGE 1.1"),
                new GovernanceQuestion("Accountability",
                        "Are identified risks prioritized, assigned a treatment, and documented, including any residual risk?",
                        "MANAGE 1.2, MANAGE 1.3, MANAGE 1.4"),

                new GovernanceQuestion("Human Oversight",
                        "Are oversight duties assigned, and are personnel trained to carry them out?",
                        "GOVERN 2.2, GOVERN 3.2"),
                new GovernanceQuestion("Human Oversight",
                        "Can a qualified person review AI-supported outputs and, where appropriate, challenge, appeal, or override them?",
                        "MAP 2.2, MAP 3.5, MANAGE 4.1"),
                new GovernanceQuestion("Human Oversight",
                        "Can authorized personnel disengage or deactivate the system when its outcomes are inconsistent with intended use?",
                        "MANAGE 2.4")
        ));
    }
}

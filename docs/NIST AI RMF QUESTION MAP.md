# QuickCheck AI Draft Question Mapping to NIST AI RMF 1.0

**Review date:** 16 October 2026
**Status:** Student-created draft mapping; not independently validated or endorsed by NIST.

## Purpose and limits

This document records the proposed relationship between the 18 QuickCheck AI questions and selected outcomes in the NIST AI Risk Management Framework (AI RMF) 1.0 Core. The six QuickCheck categories are app-design groupings. They are not NIST AI RMF categories. NIST organizes the Core under GOVERN, MAP, MEASURE, and MANAGE.

The mappings identify relevant outcomes that informed each question. They do not show that a question fully tests an outcome, that every applicable NIST outcome is covered, or that an organization conforms to the Framework. The mappings and question wording are educational design choices and need independent content review. NIST describes the AI RMF as a voluntary resource and states that the Playbook is not a checklist or ordered set of steps to implement in full.

## Question bank and proposed mappings

| ID | QuickCheck category | Draft question | NIST AI RMF 1.0 Core mapping |
| --- | --- | --- | --- |
| Q01 | Security | Are access and change controls defined for the AI system, its data, and relevant third-party components? | MAP 4.1, MAP 4.2 |
| Q02 | Security | Have security and resilience risks been assessed and documented for the system's intended context? | MEASURE 2.7 |
| Q03 | Security | Are responsibilities and procedures defined for responding to, recovering from, and communicating AI-related incidents or errors? | MANAGE 4.1, MANAGE 4.3 |
| Q04 | Privacy | Have privacy requirements for the AI system and its data use been identified and documented with relevant stakeholders? | MAP 1.6 |
| Q05 | Privacy | Have privacy risks associated with the system been assessed and documented? | MEASURE 2.10 |
| Q06 | Privacy | Are controls for collecting, using, retaining, sharing, and securely deleting relevant data defined and reviewed? | MAP 1.6, MEASURE 1.2, MEASURE 2.10 |
| Q07 | Fairness | Have potentially affected groups and the system's foreseeable positive and negative impacts been identified and documented? | MAP 5.1 |
| Q08 | Fairness | Has the system been evaluated for fairness and bias in conditions relevant to its intended use, with methods and results documented? | MEASURE 2.1, MEASURE 2.11 |
| Q09 | Fairness | Are responsibilities and procedures in place to track fairness-related risks and unexpected impacts after deployment? | GOVERN 1.5, MEASURE 3.1, MANAGE 4.1 |
| Q10 | Transparency | Are the system's intended purpose, context, expected users, benefits, limitations, and foreseeable uses documented? | MAP 1.1, MAP 3.1, MAP 3.2 |
| Q11 | Transparency | Are relevant users and affected people given information about the system's role, intended use, and important limitations? | MAP 2.2, GOVERN 4.2 |
| Q12 | Transparency | Is information about system outputs and limitations documented so people can interpret outputs appropriately? | MAP 2.2, MEASURE 2.9 |
| Q13 | Accountability | Are AI risk-management roles, responsibilities, decision authority, and communication paths documented? | GOVERN 2.1, GOVERN 2.3 |
| Q14 | Accountability | Are risk tolerances and the criteria and authority for proceeding with, changing, or stopping the system documented? | GOVERN 1.3, GOVERN 1.4, MANAGE 1.1 |
| Q15 | Accountability | Are identified risks prioritized, assigned a treatment, and documented, including any residual risk? | MANAGE 1.2, MANAGE 1.3, MANAGE 1.4 |
| Q16 | Human Oversight | Are oversight duties assigned, and are personnel trained to carry them out? | GOVERN 2.2, GOVERN 3.2 |
| Q17 | Human Oversight | Can a qualified person review AI-supported outputs and, where appropriate, challenge, appeal, or override them? | MAP 2.2, MAP 3.5, MANAGE 4.1 |
| Q18 | Human Oversight | Can authorized personnel disengage or deactivate the system when its outcomes are inconsistent with intended use? | MANAGE 2.4 |

## Implementation notes

- `QuestionBank.java` stores each question's app category, text, and mapping identifiers.
- `GovernanceQuestion.getNistCoreMapping()` provides the mapping string to the application code. The current screen does not display these identifiers.
- `QuestionBankTest` checks that each question has a non-empty mapping, that mapping identifiers use the expected function and outcome-number format, and that all four Core functions appear somewhere in the question bank.
- These automated checks test metadata presence and formatting only. They do not verify the substantive accuracy of a mapping, the quality of a governance practice, or a system's risk level.
- The approved response values, point assignments, readiness bands, and recommendations were not changed as part of this question revision. They remain preliminary educational rules and should be reviewed separately.

## References

Tabassi, E. (2023). *Artificial intelligence risk management framework (AI RMF 1.0)* (NIST AI 100-1). National Institute of Standards and Technology. https://doi.org/10.6028/NIST.AI.100-1

National Institute of Standards and Technology. (n.d.). *NIST AI RMF Playbook*. https://airc.nist.gov/airmf-resources/playbook/

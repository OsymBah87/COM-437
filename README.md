# AI Governance Readiness Checklist

## Project Focus

This project is a local-first educational prototype that applies my developing knowledge of AI governance. The goal is to organize structured assessment responses into an initial readiness summary and rule-based recommendations.

## Project Description

The AI Governance Readiness Checklist is a proposed Android application intended to help a user conduct an initial review of a proposed or existing artificial intelligence system. The application will organize questions into six governance categories:

- Security
- Privacy
- Fairness
- Transparency
- Accountability
- Human oversight

After an assessment is completed, the application is intended to calculate category scores, assign an initial readiness level, identify possible areas of concern, and display recommendations for further review.

The intended users may include students, developers, cybersecurity professionals, project owners, and governance personnel who need a structured starting point for discussing AI risk. The course version will be a single-user, local-first educational prototype. It will not provide a legal, regulatory, certification, or compliance determination.

## Problem Being Addressed

AI governance considerations may be spread across security, privacy, fairness, transparency, accountability, and human-oversight guidance. This may make an initial review difficult for someone who is still learning about AI governance.

The proposed application will present these considerations through a guided mobile assessment and translate the responses into an organized summary. It will not replace a formal assessment. Its purpose is to help users identify possible gaps, document preliminary observations, and determine where additional review may be needed.

## Development Platform

- **Target platform:** Android mobile devices
- **Programming language:** Java
- **Development environment:** Android Studio with the Android SDK
- **Testing environment:** Android Studio emulator and a physical Android device
- **Version control and documentation:** Git and GitHub

## Front-End and Back-End Support

### Front End

The planned interface will use Android activities, XML layouts, standard form controls, cards, buttons, progress indicators, and accessible color contrast. Navigation is expected to move from assessment setup to questions, results, and saved assessments.

### Local Back End

The idea is for the prototype to store assessment records, responses, category scores, results, and recommendations on the device. SQLite or the Android Room persistence library will be evaluated before implementation.

### Business Logic

Java classes are expected to validate required fields, convert responses into numeric values, calculate category and overall scores, assign readiness levels, and select rule-based recommendations.

### Future Cloud Support

A later version may use Firebase for authentication, synchronized storage, multi-user access, and audit history. These features are outside the current minimum course scope.

### Security Considerations

The application is intended to minimize permissions, avoid unnecessary personal data, validate input, and keep prototype data within application-controlled storage.

## Planned Functionality

1. Display a welcome screen explaining the application and its limitations.
2. Create a new assessment with basic system information.
3. Present structured questions across the six governance categories.
4. Validate required questions before scoring.
5. Calculate category scores and an overall readiness classification.
6. Display rule-based recommendations for categories requiring attention.
7. Save, view, search, and delete completed assessments locally.
8. Open a saved assessment to review its responses and results.

## Assessment and Scoring Approach

Response options may use a consistent scale such as **Yes**, **Partial**, **No**, and **Not Applicable**. The initial prototype will use transparent rule-based scoring rather than artificial intelligence to evaluate responses.

A recognized AI-governance framework and the final question weights will be selected during the research and design phase. The question sources, scoring rules, and assumptions will be documented before implementation. The application will distinguish between an educational readiness indicator and a formal risk or compliance judgment.

## Design and Wireframes

The planned design will use a restrained green and neutral-gray palette. Risk results will include text labels in addition to color. The interface will use readable labels, large touch targets, visible progress, predictable navigation, and concise explanations for governance terms.

The final Android layouts may change after testing, learning, and feedback. Wireframes are included in the Word project outline and may be added to the repository as the design develops.

## Project Goals and Success Criteria

- A user can create and complete an assessment without encountering a blocking error.
- Completed assessments can be stored and retrieved after the app is closed and reopened.
- The same responses consistently produce the same category scores and readiness level.
- The results screen identifies areas requiring attention and displays relevant recommendations.
- The application navigates across multiple activities on a physical Android device.
- The repository contains current code and readable documentation.

## Current Limitations

- Single-user operation
- Local storage only
- No formal legal, regulatory, certification, or compliance determination
- The framework, assessment questions, and scoring method still require further research

## Documentation

- **Project Wiki:** https://github.com/OsymBah87/COM-437/wiki
- **Repository:** https://github.com/OsymBah87/COM-437

## Disclaimer

This is a student-developed educational prototype. It is not intended to replace a formal legal, regulatory, compliance, or organizational risk assessment.

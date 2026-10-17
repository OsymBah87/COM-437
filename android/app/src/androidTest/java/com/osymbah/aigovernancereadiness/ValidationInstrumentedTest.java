package com.osymbah.aigovernancereadiness;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ValidationInstrumentedTest {
    @Test
    public void blankSetupRequiresSystemName() {
        try (ActivityScenario<SetupActivity> scenario = ActivityScenario.launch(SetupActivity.class)) {
            onView(withId(R.id.setupNextButton)).perform(click());
            onView(withId(R.id.systemNameInput)).check(matches(hasErrorText("Enter the AI system name.")));
            onView(withId(R.id.setupNextButton)).check(matches(isDisplayed()));
        }
    }

    @Test
    public void unansweredQuestionDoesNotAdvance() {
        Context context = ApplicationProvider.getApplicationContext();
        Intent intent = new Intent(context, QuestionActivity.class);
        intent.putExtra(QuestionActivity.EXTRA_SYSTEM_NAME, "Test System");
        intent.putExtra(QuestionActivity.EXTRA_SYSTEM_PURPOSE, "Course validation");

        try (ActivityScenario<QuestionActivity> scenario = ActivityScenario.launch(intent)) {
            onView(withId(R.id.questionNextButton)).perform(click());
            onView(withId(R.id.questionProgressText)).check(matches(withText("Question 1 of 18")));
            onView(withId(R.id.questionText)).check(matches(isDisplayed()));
        }
    }

    @Test
    public void mixedResponsesShowExpectedScoresAndRecommendations() {
        try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
            onView(withId(R.id.startAssessmentButton)).perform(click());
            onView(withId(R.id.systemNameInput)).perform(replaceText("Mixed Test System"));
            onView(withId(R.id.systemPurposeInput)).perform(replaceText("Course validation"), closeSoftKeyboard());
            onView(withId(R.id.setupNextButton)).perform(click());

            int[] responses = {R.id.responseYes, R.id.responsePartly, R.id.responseNo};
            for (int index = 0; index < 18; index++) {
                onView(withId(responses[index % responses.length])).perform(click());
                onView(withId(R.id.questionNextButton)).perform(click());
            }

            onView(withId(R.id.resultsSummaryText)).check(matches(withText(
                    "Mixed Test System\n\nOverall score: 56%\nReadiness level: Developing Readiness")));
            onView(withId(R.id.resultsCategoryText)).check(matches(withText(
                    "Category scores\n\nSecurity: 56%\nPrivacy: 56%\nFairness: 56%\n" +
                            "Transparency: 56%\nAccountability: 56%\nHuman Oversight: 56%")));
            onView(withId(R.id.resultsRecommendationsText)).check(matches(withText(
                    "Priority recommendations\n\n" +
                            "Security: Review access controls, security risk assessment, and incident response arrangements.\n\n" +
                            "Privacy: Identify sensitive data and document its purpose, retention, and secure deletion requirements.\n\n" +
                            "Fairness: Identify affected groups and test and monitor outcomes for unfair differences.\n\n" +
                            "Transparency: Document the system’s purpose and limitations and explain important outputs to affected users.\n\n" +
                            "Accountability: Assign ownership and document approval, escalation, decision, and change records.\n\n" +
                            "Human Oversight: Define trained human reviewers and procedures to challenge, pause, or stop the system.")));
        }
    }

    @Test
    public void systemBackReturnsFromQuestionToSetup() {
        try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
            onView(withId(R.id.startAssessmentButton)).perform(click());
            onView(withId(R.id.systemNameInput)).perform(replaceText("Test System"));
            onView(withId(R.id.systemPurposeInput)).perform(replaceText("Course validation"), closeSoftKeyboard());
            onView(withId(R.id.setupNextButton)).perform(click());
            onView(withId(R.id.questionProgressText)).check(matches(withText("Question 1 of 18")));

            pressBack();

            onView(withId(R.id.setupNextButton)).check(matches(isDisplayed()));
            onView(withId(R.id.systemNameInput)).check(matches(withText("Test System")));
            onView(withId(R.id.systemPurposeInput)).check(matches(withText("Course validation")));
        }
    }

    @Test
    public void systemBackReturnsFromSavedAssessmentsToWelcome() {
        try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
            onView(withId(R.id.savedAssessmentsButton)).perform(click());
            onView(withText("6. Saved Assessments")).check(matches(isDisplayed()));

            pressBack();

            onView(withText("1. Welcome")).check(matches(isDisplayed()));
            onView(withId(R.id.startAssessmentButton)).check(matches(isDisplayed()));
        }
    }

    @Test
    public void systemBackReturnsFromResultsToFinalAnsweredQuestion() {
        try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
            onView(withId(R.id.startAssessmentButton)).perform(click());
            onView(withId(R.id.systemNameInput)).perform(replaceText("Test System"));
            onView(withId(R.id.systemPurposeInput)).perform(replaceText("Course validation"), closeSoftKeyboard());
            onView(withId(R.id.setupNextButton)).perform(click());

            for (int index = 0; index < 18; index++) {
                onView(withId(R.id.responseYes)).perform(click());
                onView(withId(R.id.questionNextButton)).perform(click());
            }

            onView(withText("4. Assessment Results")).check(matches(isDisplayed()));
            pressBack();
            onView(withId(R.id.questionProgressText)).check(matches(withText("Question 18 of 18")));
            onView(withId(R.id.responseYes)).check(matches(isChecked()));
        }
    }
}

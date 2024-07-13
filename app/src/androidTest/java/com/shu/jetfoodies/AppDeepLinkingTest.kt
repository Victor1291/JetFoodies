package com.shu.jetfoodies

import android.content.Intent
import android.net.Uri
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AppDeepLinkingTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = activityScenarioRule.scenario
    }

    @After
    fun cleanup() {
        scenario.close()
    }

    @Test
    fun deepLinkApplicationTextDashboardDeeplinkHasText() {
        // Launch the activity with the deep link
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("example://compose/dashboard/johnDoe")
        }
        scenario.onActivity { activity ->
            activity.startActivity(intent)
        }

        // Verify that the activity displays the expected text
        val value = composeTestRule.onNodeWithTag("dashboardDeeplinkArgument")
        composeTestRule.onRoot().printToLog("Ui test")
        value.assertTextEquals("This is Home Screen johnDoe")
        for ((key, value) in value.fetchSemanticsNode().config) {
            if (key.name == "EditableText") {
                assertEquals("This is Home Screen johnDoe", value.toString())
            }
        }
    }
    @Test
    fun deepLinkApplicationTestId() {
        // Launch the activity with the deep link
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("example://compose/detail/924131")
        }
        scenario.onActivity { activity ->
            activity.startActivity(intent)
        }

        // Verify that the activity displays the expected text
        val value = composeTestRule.onNodeWithTag("deeplinkArgument")
        composeTestRule.onRoot().printToLog("Ui test")
        value.assertTextEquals("924131")
        for ((key, value) in value.fetchSemanticsNode().config) {
            if (key.name == "EditableText") {
                assertEquals("924131", value.toString())
            }
        }
    }
}

/*
cd C:\Users\vshut\AppData\Local\Android\Sdk
.\adb shell am start -W -a android.intent.action.VIEW -d "example://compose/dashboard/dashBoardDeeplink"
.\adb shell am start -W -a android.intent.action.VIEW -d "example://compose/settings/sampleArgument"
 */
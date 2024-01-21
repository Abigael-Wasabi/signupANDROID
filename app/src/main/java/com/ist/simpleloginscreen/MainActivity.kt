/**
 * A class representing the main activity of the Simple Login Screen app.
 *
 * This activity extends the ComponentActivity class and sets the content of the activity to a Compose UI defined in the Ondemand composable function.
 *
 * Example Usage:
 * ```
 * val mainActivity = MainActivity()
 * mainActivity.onCreate(savedInstanceState)
 * ```
 *
 * Inputs:
 * - savedInstanceState (Bundle): The saved instance state of the activity.
 *
 * Flow:
 * 1. The onCreate method is called when the activity is being created.
 * 2. The super.onCreate(savedInstanceState) line calls the onCreate method of the parent class.
 * 3. The setContent method is called to set the content of the activity.
 * 4. The SimpleLoginScreenTheme composable function is used to apply the theme to the UI.
 * 5. The Ondemand composable function is called to create the UI for the activity.
 *
 * Outputs:
 * None.
 */
package com.ist.simpleloginscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ist.simpleloginscreen.app.SimpleLoginApp
import com.ist.simpleloginscreen.presentation.ui.theme.SimpleLoginScreenTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleLoginScreenTheme {
                SimpleLoginApp()
            }
        }
    }
}
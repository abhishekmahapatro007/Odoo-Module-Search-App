package com.example.modulecheckq

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.border
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

val communityModules = listOf(
    "Discuss", "Calendar", "To-do", "Members", "Contacts", "CRM", "Sales",
    "Dashboards", "Point of Sale", "Invoicing", "Project", "Timesheets",
    "Website", "eLearning", "Email Marketing", "SMS Marketing", "Events",
    "Surveys", "Purchase", "Inventory", "Manufacturing", "Maintenance",
    "Repairs", "Employees", "Attendances", "Recruitment", "Fleet", "Time Off",
    "Expenses", "Lunch", "Live Chat", "Data Cleaning", "Apps", "Settings", "Tests"
)

val enterpriseModules = communityModules + listOf(
    "Knowledge", "Meeting Rooms", "Frontdesk", "Appointments", "Subscriptions", "Rental",
    "Kitchen Display", "Accounting", "Documents", "Field Service", "Planning", "Helpdesk",
    "Social Marketing", "Marketing Automation", "Quality", "Barcode", "PLM", "Consolidation",
    "Sign", "Payroll", "Appraisals", "Referrals", "Approvals", "Whatsapp", "IoT", "Apps",
    "Settings", "Tests", "Attendances", "Recruitment", "Fleet", "Time off", "Expenses",
    "Lunch", "Live Chat", "Data cleaning"
)

@Composable
fun ModuleSearchApp() {
    var input by remember { mutableStateOf("") }
    var resultText by remember { mutableStateOf("") }
    var resultModules by remember { mutableStateOf<List<String>>(emptyList()) }
    var isSearchFocused by remember { mutableStateOf(false) } // Track focus state
    var isSearchPerformed by remember { mutableStateOf(false) } // Track if search button was clicked

    val onSearchClick = {
        val moduleName = input.trim().lowercase()

        isSearchPerformed = true // Mark search as performed

        when {
            moduleName.isEmpty() -> {
                resultText = "Please enter a module name."
                resultModules = emptyList()
            }
            moduleName == "community" -> {
                resultText = "Community Modules:"
                resultModules = communityModules
            }
            moduleName == "enterprise" -> {
                resultText = "Enterprise Modules:"
                resultModules = enterpriseModules
            }
            moduleName in communityModules.map { it.lowercase() } && moduleName !in enterpriseModules.map { it.lowercase() } -> {
                resultText = "$moduleName is available in Community only."
                resultModules = emptyList()
            }
            moduleName !in communityModules.map { it.lowercase() } && moduleName in enterpriseModules.map { it.lowercase() } -> {
                resultText = "$moduleName is available in Enterprise only."
                resultModules = emptyList()
            }
            moduleName in communityModules.map { it.lowercase() } && moduleName in enterpriseModules.map { it.lowercase() } -> {
                resultText = "$moduleName is available in both Community and Enterprise."
                resultModules = emptyList()
            }
            else -> {
                resultText = "$moduleName is not available in either Community or Enterprise."
                resultModules = emptyList()
            }
        }
    }

    val context = LocalContext.current

    MaterialTheme(colorScheme = darkColorScheme()) {
        Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Input Field
                BasicTextField(
                    value = input,
                    onValueChange = { input = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(12.dp)) // Rounded corners
                        .background(Color.White) // Set background color to white
                        .border(1.dp, Color.Gray),

                    textStyle = TextStyle(color = Color.Black), // Set text color to black

                    decorationBox = { innerTextField ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            innerTextField()
                        }
                    }
                )

                // Search Button
                Button(
                    onClick = onSearchClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                       // .background(Color.Gray)
                ) {
                    Text("Search", color = Color.White,modifier = Modifier.padding(8.dp))
                }

                // Instruction Text
                Text(
                    text = "Type the module to check if it's present in either Community or Enterprise version.\n" +
                            "Type 'Community' or 'Enterprise' to display the full list of modules.",
                    modifier = Modifier.padding(top = 8.dp),
                    color = Color.LightGray,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center
                )

                // Result Label
                Text(
                    text = resultText,
                    modifier = Modifier.padding(top = 20.dp),
                    color = Color.White
                )

                // Scrollable List for Community/Enterprise Modules
                if (isSearchPerformed && resultModules.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                            .padding(20.dp)
                    ) {
                        items(resultModules) { module ->
                            Text(
                                text = module,
                                color = Color.Gray,
                                modifier = Modifier.padding(4.dp),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }

            // GitHub Button
            FloatingActionButton(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/abhishekmahapatro007"))
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(30.dp, 60.dp)
                    .size(56.dp),
                shape = CircleShape,
                containerColor = Color.White
            ) {
                Image(
                    painter = painterResource(id = R.drawable.github_icon),
                    contentDescription = "GitHub Icon",
                    modifier = Modifier.size(40.dp)
                )
            }

            // Footer
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            ) {
                Text(
                    text = "© 2024 Developed and Published by Abhishek Mahapatro",
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ModuleSearchApp()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ModuleSearchApp()
        }
    }
}

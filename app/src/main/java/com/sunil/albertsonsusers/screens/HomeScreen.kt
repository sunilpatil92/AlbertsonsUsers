package com.sunil.albertsonsusers.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    var inputUser = remember { mutableStateOf("10") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Albertsons",
                            fontSize = 21.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF2196F3)),
                actions = {}
            )
        },
        content = { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp, 0.dp, 10.dp, 0.dp)
                    .padding(innerPadding)
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    val context = LocalContext.current
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(
                        text = "Welcome",
                        modifier = Modifier.padding(30.dp),
                        fontSize = 36.sp
                    )
                    OutlinedTextField(
                        value = inputUser.value,
                        onValueChange = {newValue ->
                           // inputUser.value = it

                            if (newValue.all { it.isDigit() && it in '0'..'9' }) {
                                inputUser.value = newValue
                            } else { }
                        },
                        singleLine = true,
                        label = { Text(text = "Enter Number of Users") },
                        leadingIcon = { Icon(Icons.Default.Person, contentDescription = "") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp, 10.dp, 20.dp, 10.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = {

                            if (inputUser.value.isEmpty()) {
                                Toast.makeText(
                                    context,
                                    "please enter some number",
                                    Toast.LENGTH_SHORT
                                ).show()
                                return@Button
                            }
                            val input = inputUser.value.toInt()
                            if (input != null) {
                                if (input <= 0) {
                                    Toast.makeText(
                                        context,
                                        "please enter number greter then 0",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    return@Button
                                }
                            }
                            navController.navigate("Users/${input}")
                        },
                        content = { Text(text = "Submit", fontSize = 20.sp) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(30.dp, 10.dp, 30.dp, 5.dp)
                    )


                }

            }


        }

    )
}
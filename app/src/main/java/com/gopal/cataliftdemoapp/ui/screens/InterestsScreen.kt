package com.gopal.cataliftdemoapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.gopal.cataliftdemoapp.R
import com.gopal.cataliftdemoapp.ui.components.InterestCard

@Composable
fun InterestsScreen(
    onContinue: (selectedInterests: List<String>) -> Unit
) {
    val allInterests = listOf(
        Interest("Music", R.drawable.interest_music),
        Interest("Art", R.drawable.interest_art),
        Interest("Sports", R.drawable.interest_sports),
        Interest("Gaming", R.drawable.interest_gaming),
        Interest("Coding", R.drawable.interest_coding),
        Interest("Cooking", R.drawable.interest_cooking),
        Interest("Travel", R.drawable.interest_travelling),
        Interest("Photography", R.drawable.interest_photography)
    )

    var selectedInterests by remember { mutableStateOf(setOf<String>()) }
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    val filteredInterests = allInterests.filter {
        it.name.contains(searchQuery.text, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Text(
            text = "Select Your Interests",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = {
                Text(
                    "Search interests..."
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(50),
            singleLine = true,
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                cursorColor = MaterialTheme.colorScheme.primary
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(filteredInterests) { interest ->
                val isSelected = interest.name in selectedInterests

                InterestCard(
                    interest = interest,
                    isSelected = isSelected,
                    onClick = {
                        selectedInterests = if (isSelected) {
                            selectedInterests - interest.name
                        } else {
                            selectedInterests + interest.name
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                onContinue(selectedInterests.toList())
            },
            enabled = selectedInterests.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .navigationBarsPadding()
        ) {
            Text("Continue")
        }
    }
}

data class Interest(val name: String, val iconRes: Int)

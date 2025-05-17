package com.gopal.cataliftdemoapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.gopal.cataliftdemoapp.R
import com.gopal.cataliftdemoapp.ui.components.ProfessionCard

@Composable
fun ProfessionScreen(
    navController: NavController,
    selectedInterests: List<String>
) {
    val allProfessions = listOf(
        Profession("Developer", R.drawable.profession_developer),
        Profession("Designer", R.drawable.profession_designer),
        Profession("Teacher", R.drawable.profession_teacher),
        Profession("Doctor", R.drawable.profession_doctor),
        Profession("Engineer", R.drawable.profession_engineer),
        Profession("Artist", R.drawable.profession_artist),
        Profession("Writer", R.drawable.profession_writer),
        Profession("Entrepreneur", R.drawable.profession_entrepreneur)
    )

    var selectedProfession by remember { mutableStateOf<String?>(null) }
    var searchQuery by remember { mutableStateOf("") }

    val filteredProfessions = remember(searchQuery) {
        if (searchQuery.isBlank()) {
            allProfessions
        } else {
            allProfessions.filter {
                it.name.contains(searchQuery.trim(), ignoreCase = true)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(16.dp)
    ) {
        Text(
            text = "Select Your Profession",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search professions...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            shape = RoundedCornerShape(32.dp),
            modifier = Modifier
                .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.Gray
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
            items(filteredProfessions) { profession ->
                val isSelected = selectedProfession == profession.name
                ProfessionCard(
                    profession = profession,
                    isSelected = isSelected,
                    onClick = {
                        selectedProfession = profession.name
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                selectedProfession?.let {
                    navController.navigate(
                        "profile?interests=${selectedInterests.joinToString(",")}&profession=$it"
                    )
                }
            },
            enabled = selectedProfession != null,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .navigationBarsPadding()
        ) {
            Text("Continue")
        }
    }
}

data class Profession(val name: String, val iconRes: Int)

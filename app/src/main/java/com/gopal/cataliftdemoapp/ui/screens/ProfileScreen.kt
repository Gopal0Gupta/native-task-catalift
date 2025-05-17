package com.gopal.cataliftdemoapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gopal.cataliftdemoapp.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileScreen(
    selectedInterests: List<String> = emptyList(),
    selectedProfession: String = ""
) {
    Scaffold() { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(R.drawable.header_background),
                    contentDescription = "Header",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .padding(16.dp)
                        .size(32.dp)
                        .background(
                            MaterialTheme.colorScheme.onPrimaryContainer,
                            CircleShape
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .offset(y = (-40).dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(R.drawable.profile_placeholder),
                    contentDescription = "Profile Picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("Anmol Jha", style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.width(6.dp))
                    Image(
                        painter = painterResource(R.drawable.ic_verified),
                        contentDescription = "Verified",
                        modifier = Modifier.size(20.dp)
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFFFD700),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(" 4.5", style = MaterialTheme.typography.bodyMedium)
                }

                Text(
                    "UI UX Designer and Researcher",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
            ) {
                TagItem("Google")
                TagItem("Oxford University")
                TagItem(selectedProfession.ifBlank { "Profession" })
            }

            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = {  }, shape = RoundedCornerShape(12.dp)) {
                    Text("Connect")
                }
                OutlinedButton(onClick = {  }, shape = RoundedCornerShape(12.dp)) {
                    Text("Message")
                }
            }

            Section(title = "About") {
                Text(
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }

            Section(title = "Experience") {
                ProfileCard(
                    title = "User Experience Designer",
                    subtitle = "Alphabet Inc.",
                    date = "Aug 2024 - Present",
                    location = "Pune, Maharashtra"
                )
            }

            Section(title = "Education") {
                ProfileCard(
                    title = "UX Design Intern",
                    subtitle = "MIT Institute of Design",
                    date = "Jan 2022 - May 2023",
                    location = "Pune, Maharashtra"
                )
            }

            if (selectedInterests.isNotEmpty()) {
                Section(title = "Interests") {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        selectedInterests.forEach {
                            TagItem(it)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}


@Composable
fun TagItem(text: String) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFE8EAF6),
        modifier = Modifier
            .defaultMinSize(minHeight = 32.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            color = Color(0xFF3F51B5),
            fontSize = 14.sp
        )
    }
}

@Composable
fun Section(title: String, content: @Composable () -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)) {
        Text(
            title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        content()
    }
}

@Composable
fun ProfileCard(title: String, subtitle: String, date: String, location: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.google_logo),
                    contentDescription = "Company Logo",
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(title, style = MaterialTheme.typography.titleSmall)
                    Text(subtitle, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(date, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            Text(location, style = MaterialTheme.typography.bodySmall, color = Color.Gray)

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "– Creating and refining design samples to kickstart projects for clients.\n" +
                        "– Prototyping and building landing pages tailored to user goals.\n" +
                        "– Learning HTML and CSS to enhance front-end development.",
                style = MaterialTheme.typography.bodySmall,
                color = Color.DarkGray
            )
        }
    }
}

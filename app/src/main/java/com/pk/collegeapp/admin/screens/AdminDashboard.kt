package com.pk.collegeapp.admin.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pk.collegeapp.ui.theme.TITLE_SIZE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashBoard(navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Admin Dashboard") }) }
    )
    { pad ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(pad)
        ) {
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable {

                }) {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
                    text = "Manage Banners",
                    fontWeight = FontWeight.Bold,
                    fontSize = TITLE_SIZE
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ShowPrev() {
    AdminDashBoard(navController = rememberNavController())
}

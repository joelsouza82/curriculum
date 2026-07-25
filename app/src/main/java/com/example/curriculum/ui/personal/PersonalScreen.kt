package com.example.curriculum.ui.personal

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.curriculum.ui.theme.CurriculumTheme
import com.example.curriculum.ui.theme.WhiteText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalScreen(
    viewModel: PersonalViewModel = viewModel(),
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("🏠 Dados Pessoais", color = WhiteText) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = WhiteText)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            when (val state = uiState) {
                is PersonalUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is PersonalUiState.Error -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(state.message)
                        Button(onClick = { viewModel.loadData() }) {
                            Text("Tentar novamente")
                        }
                    }
                }
                is PersonalUiState.Success -> {
                    val personal = state.data
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        item {
                            SectionHeader("Endereço")
                            InfoItem(
                                icon = Icons.Default.Home,
                                title = personal.address,
                                subtitle = "${personal.neighborhood} — ${personal.city} / ${personal.state}\n${personal.cep}"
                            )
                        }
                        item {
                            SectionHeader("Contatos")
                            InfoItem(
                                icon = Icons.Default.Phone,
                                title = personal.phone
                            )
                            InfoItem(
                                icon = Icons.Default.Email,
                                title = personal.email
                            )
                        }
                        personal.linkedin?.let { url ->
                            item {
                                SectionHeader("Linkedin")
                                ClickableInfoItem(
                                    icon = Icons.Default.Person,
                                    title = url,
                                    onClick = {
                                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                        context.startActivity(intent)
                                    }
                                )
                            }
                        }
                        personal.github?.let { url ->
                            item {
                                SectionHeader("Github")
                                ClickableInfoItem(
                                    icon = Icons.Default.Code,
                                    title = url,
                                    onClick = {
                                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                        context.startActivity(intent)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PersonalScreenPreview() {
    CurriculumTheme {
        PersonalScreen(onBack = {})
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
    )
}

@Composable
fun InfoItem(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, subtitle: String? = null) {
    ListItem(
        headlineContent = { Text(title) },
        supportingContent = subtitle?.let { { Text(it) } },
        leadingContent = { Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary) }
    )
}

@Composable
fun ClickableInfoItem(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, onClick: () -> Unit) {
    ListItem(
        headlineContent = { Text(title) },
        leadingContent = { Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
        modifier = Modifier.clickable { onClick() }
    )
}

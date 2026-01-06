package com.alhussain.cap10.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cap10.composeapp.generated.resources.Res
import cap10.composeapp.generated.resources.ic_matches
import cap10.composeapp.generated.resources.ic_stadium_icon
import cap10.composeapp.generated.resources.ic_stadiums
import cap10.composeapp.generated.resources.ic_teams
import cap10.composeapp.generated.resources.icon_booking_stadium
import com.alhussain.cap10.getCurrentLocalization
import com.alhussain.cap10.theme.MyAppTheme
import com.alhussain.cap10.widgets.FeatureCard
import org.jetbrains.compose.resources.painterResource

const val ASPECT_VALUE = 1.1f

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    MyAppTheme {
        CompositionLocalProvider {
            Scaffold(
                modifier = Modifier,
                topBar = { TopBarWidget() },
                bottomBar = { /*NavigationBarWidget() */ },
                containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
            ) { paddingValues ->
                Column(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .background(MaterialTheme.colorScheme.surfaceContainerLowest),
                ) {
                    HomePageContent()
                }
            }
        }
    }
}

@Composable
fun HomePageContent() =
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // First item spans 2 columns (full width)
            item(span = { GridItemSpan(2) }) {
                FeatureCard(
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                    getCurrentLocalization().players,
                    Res.drawable.ic_matches,
                )
            }

            // Regular grid items
            item {
                FeatureCard(
                    modifier = Modifier,
                    getCurrentLocalization().bookStadium,
                    Res.drawable.icon_booking_stadium,
                )
            }

            item {
                FeatureCard(
                    modifier = Modifier,
                    getCurrentLocalization().posts,
                    Res.drawable.ic_stadium_icon,
                )
            }

            // Add more items as needed
        }
    }

@Composable
fun NavigationBarWidget() =
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(Res.drawable.ic_stadiums),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                )
            },
            onClick = {},
            selected = true,
            label = {
                Text(getCurrentLocalization().home)
            },
            colors =
                NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    selectedTextColor = MaterialTheme.colorScheme.onSurface,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                ),
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(Res.drawable.ic_teams),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                )
            },
            onClick = {},
            selected = false,
            label = {
                Text(getCurrentLocalization().myTeams)
            },
            colors =
                NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    selectedTextColor = MaterialTheme.colorScheme.onSurface,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                ),
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(Res.drawable.ic_stadiums),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                )
            },
            onClick = {},
            selected = false,
            label = {
                Text(getCurrentLocalization().stadiums)
            },
            colors =
                NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    selectedTextColor = MaterialTheme.colorScheme.onSurface,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                ),
        )
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWidget() =
    TopAppBar(
        title = {
            Column {
                Text(
                    getCurrentLocalization().appName,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    "Welcome back",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(
                    Icons.Default.Menu,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    Icons.Default.Phone,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        },
        colors =
            TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface,
                titleContentColor = MaterialTheme.colorScheme.onSurface,
                navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                actionIconContentColor = MaterialTheme.colorScheme.onSurface,
            ),
    )

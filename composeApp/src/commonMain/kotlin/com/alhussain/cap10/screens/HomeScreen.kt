package com.alhussain.cap10.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import cap10.composeapp.generated.resources.Res
import cap10.composeapp.generated.resources.ic_matches
import cap10.composeapp.generated.resources.ic_stadium_icon
import cap10.composeapp.generated.resources.ic_stadiums
import cap10.composeapp.generated.resources.ic_teams
import cap10.composeapp.generated.resources.ic_tournaments
import cap10.composeapp.generated.resources.icon_booking_stadium
import com.alhussain.cap10.getCurrentLocalization
import com.alhussain.cap10.theme.Green
import com.alhussain.cap10.theme.MyAppTheme
import com.alhussain.cap10.widgets.FeatureCard
import com.alhussain.cap10.widgets.SliderWidget
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    MyAppTheme {


        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Scaffold(modifier = Modifier, topBar = {
                TopAppBar(
                    title = {
                        Text(
                            getCurrentLocalization().appName,
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge
                        )
                    },
                    navigationIcon = {
                        Icon(
                            Icons.Default.Menu,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    },
                    actions = {
                        Icon(

                            Icons.Default.Phone,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Icon(
                            Icons.Default.Notifications,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    },
                )
            }, bottomBar = {
                NavigationBar(containerColor = Green) {
                    NavigationBarItem(
                        icon = {
                            Icon(
                                painterResource(Res.drawable.ic_stadiums),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        onClick = {},
                        selected = true,
                        label = {
                            Text(
                                getCurrentLocalization().home,
                            )
                        },
                    )
                    NavigationBarItem(icon = {
                        Icon(
                            painterResource(Res.drawable.ic_teams),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }, onClick = {

                    }, selected = false, label = {
                        Text(
                            getCurrentLocalization().myTeams
                        )
                    })
                    NavigationBarItem(
                        icon = {
                            Icon(
                                painterResource(Res.drawable.ic_stadiums),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)

                            )
                        },
                        onClick = {

                        },
                        selected = false,
                        label = {
                            Text(
                                getCurrentLocalization().stadiums,

                                )
                        },

                        )
                }
            }) { paddingValues ->
                Column(
                    modifier = Modifier.fillMaxSize().padding(paddingValues)
                ) {
                    SliderWidget(emptyList())
                    Column(
                        modifier = Modifier.fillMaxSize()
                            .padding(vertical = 8.dp, horizontal = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,

                        ) {
                        val cardModifier: Modifier = remember {
                            Modifier.weight(1f).aspectRatio(1.1f)
                        }


                        Spacer(modifier = Modifier.height(16.dp))

                        Row {
                            FeatureCard(
                                modifier = cardModifier,
                                getCurrentLocalization().bookStadium,
                                Res.drawable.icon_booking_stadium
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            FeatureCard(
                                modifier = cardModifier,
                                getCurrentLocalization().leagues,
                                Res.drawable.ic_tournaments
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row {
                            FeatureCard(
                                modifier = cardModifier,
                                getCurrentLocalization().officials,
                                Res.drawable.ic_matches
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            FeatureCard(
                                modifier = cardModifier,
                                getCurrentLocalization().posts,
                                Res.drawable.ic_stadium_icon
                            )
                        }
                    }
                }

            }
        }

    }
}
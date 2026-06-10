package com.cameraring.notifier.ui.settings

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen() {

    var enabled by remember {
        mutableStateOf(true)
    }

    var blinking by remember {
        mutableStateOf(true)
    }

    var size by remember {
        mutableFloatStateOf(120f)
    }

    var thickness by remember {
        mutableFloatStateOf(8f)
    }

    var alpha by remember {
        mutableFloatStateOf(1f)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Camera Ring Notifier",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(20.dp))

        Row(
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Text(
                "Aktivieren",
                modifier = Modifier.weight(1f)
            )

            Switch(
                checked = enabled,
                onCheckedChange = {
                    enabled = it
                }
            )
        }

        Spacer(Modifier.height(16.dp))

        Row(
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Text(
                "Blinken",
                modifier = Modifier.weight(1f)
            )

            Switch(
                checked = blinking,
                onCheckedChange = {
                    blinking = it
                }
            )
        }

        Spacer(Modifier.height(20.dp))

        Text("Ringgröße")

        Slider(
            value = size,
            onValueChange = {
                size = it
            },
            valueRange = 60f..250f
        )

        Spacer(Modifier.height(10.dp))

        Text("Ringstärke")

        Slider(
            value = thickness,
            onValueChange = {
                thickness = it
            },
            valueRange = 2f..20f
        )

        Spacer(Modifier.height(10.dp))

        Text("Transparenz")

        Slider(
            value = alpha,
            onValueChange = {
                alpha = it
            },
            valueRange = 0.1f..1f
        )

        Spacer(Modifier.height(30.dp))

        Text(
            "Vorschau",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            contentAlignment =
                Alignment.Center
        ) {

            Canvas(
                modifier = Modifier.size(220.dp)
            ) {

                drawCircle(
                    color =
                        androidx.compose.ui.graphics.Color.Green,
                    radius = size / 2,
                    style = Stroke(thickness),
                    alpha = alpha
                )
            }
        }
    }
}
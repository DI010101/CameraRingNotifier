package com.cameraring.notifier.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(
    name = "ring_settings"
)

class SettingsRepository(
    private val context: Context
) {

    companion object {

        val ENABLED =
            booleanPreferencesKey("enabled")

        val COLOR =
            longPreferencesKey("color")

        val BLINK =
            booleanPreferencesKey("blink")

        val BLINK_INTERVAL =
            longPreferencesKey("blink_interval")

        val SIZE =
            floatPreferencesKey("size")

        val THICKNESS =
            floatPreferencesKey("thickness")

        val ALPHA =
            floatPreferencesKey("alpha")

        val AUTO_POSITION =
            booleanPreferencesKey("auto_position")

        val POS_X =
            floatPreferencesKey("pos_x")

        val POS_Y =
            floatPreferencesKey("pos_y")
    }

    val settings: Flow<RingSettings> =
        context.dataStore.data.map { pref ->

            RingSettings(

                enabled =
                    pref[ENABLED] ?: true,

                color =
                    pref[COLOR] ?: 0xFF00FF00,

                blinking =
                    pref[BLINK] ?: true,

                blinkInterval =
                    pref[BLINK_INTERVAL] ?: 1000L,

                ringSize =
                    pref[SIZE] ?: 120f,

                ringThickness =
                    pref[THICKNESS] ?: 8f,

                alpha =
                    pref[ALPHA] ?: 1f,

                autoCameraPosition =
                    pref[AUTO_POSITION] ?: true,

                posX =
                    pref[POS_X] ?: 930f,

                posY =
                    pref[POS_Y] ?: 120f
            )
        }

    suspend fun save(settings: RingSettings) {

        context.dataStore.edit { pref ->

            pref[ENABLED] = settings.enabled

            pref[COLOR] = settings.color

            pref[BLINK] = settings.blinking

            pref[BLINK_INTERVAL] =
                settings.blinkInterval

            pref[SIZE] =
                settings.ringSize

            pref[THICKNESS] =
                settings.ringThickness

            pref[ALPHA] =
                settings.alpha

            pref[AUTO_POSITION] =
                settings.autoCameraPosition

            pref[POS_X] =
                settings.posX

            pref[POS_Y] =
                settings.posY
        }
    }
}
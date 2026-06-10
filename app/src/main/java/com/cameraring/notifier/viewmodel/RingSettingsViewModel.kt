package com.cameraring.notifier.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cameraring.notifier.data.RingSettings
import com.cameraring.notifier.data.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RingSettingsViewModel(
    private val repository: SettingsRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(RingSettings())

    val uiState: StateFlow<RingSettings> =
        _uiState.asStateFlow()

    init {
        loadSettings()
    }

    private fun loadSettings() {

        viewModelScope.launch {

            repository.settings.collect { settings ->

                _uiState.value = settings
            }
        }
    }

    fun setEnabled(enabled: Boolean) {

        _uiState.update {
            it.copy(
                enabled = enabled
            )
        }

        save()
    }

    fun setBlinking(blinking: Boolean) {

        _uiState.update {
            it.copy(
                blinking = blinking
            )
        }

        save()
    }

    fun setBlinkInterval(interval: Long) {

        _uiState.update {
            it.copy(
                blinkInterval = interval
            )
        }

        save()
    }

    fun setRingSize(size: Float) {

        _uiState.update {
            it.copy(
                ringSize = size
            )
        }

        save()
    }

    fun setRingThickness(thickness: Float) {

        _uiState.update {
            it.copy(
                ringThickness = thickness
            )
        }

        save()
    }

    fun setAlpha(alpha: Float) {

        _uiState.update {
            it.copy(
                alpha = alpha
            )
        }

        save()
    }

    fun setColor(color: Long) {

        _uiState.update {
            it.copy(
                color = color
            )
        }

        save()
    }

    fun setAutoPosition(auto: Boolean) {

        _uiState.update {
            it.copy(
                autoCameraPosition = auto
            )
        }

        save()
    }

    fun setPosition(
        x: Float,
        y: Float
    ) {

        _uiState.update {
            it.copy(
                posX = x,
                posY = y
            )
        }

        save()
    }

    private fun save() {

        viewModelScope.launch {

            repository.save(
                _uiState.value
            )
        }
    }
}
package com.douglas.presentation.ui.model

import androidx.annotation.StringRes

sealed interface UIState {
    object Idle : UIState
    object Loading : UIState
    data class Error(@StringRes val error: Int) : UIState
}
package com.kinoyamboladmin.utilities.error_handling

import com.kinoyamboladmin.utilities.texts.UiText

sealed class InformationStateUiState(val visible: Boolean, val message: UiText = UiText.DynamicString()) {
    class Hidden :
        InformationStateUiState(
            visible = false
        )

    class Information(message: UiText, val showProgress: Boolean = false) :
        InformationStateUiState(
            visible = true,
            message = message
        )

    class Error(message: UiText, val onDismiss: () -> Unit) :
        InformationStateUiState(
            visible = true,
            message = message
        )
}

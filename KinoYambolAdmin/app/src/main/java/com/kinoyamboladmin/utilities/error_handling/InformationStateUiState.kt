package com.kinoyamboladmin.utilities.error_handling

sealed class InformationStateUiState(val visible: Boolean, val message: String = "") {
    class Oculta :
        InformationStateUiState(
            visible = false
        )
    class Informacion(mensaje: String, val muestraProgreso: Boolean = false) :
        InformationStateUiState(
            visible = true,
            message = mensaje
        )
    class Error(mensaje: String, val onDismiss: () -> Unit) :
        InformationStateUiState(
            visible = true,
            message = mensaje
        )
}

package com.kinoyamboladmin.utilities.validation

import com.kinoyamboladmin.utilities.texts.UiText

// Validacion.kt -----------------------------------------------
// Abstracción del resultado de una validación.
// Si hay error, se indica el mensaje de error.
// Será el UIState que reciben nuestros TextField para indicar si hay error o no.
// Puedo crear una unstancia de Validacion con un objeto anónimo que
// implemente la interfaz Validacion usando object : Validacion { ... }
interface Validation {
    val hasError: Boolean
        get() = false
    val errorMessage: UiText?
        get() = null
}
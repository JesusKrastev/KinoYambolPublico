package com.kinoyamboladmin.utilities.validation.validators

import com.kinoyamboladmin.utilities.texts.UiText
import com.kinoyamboladmin.utilities.validation.Validation
import com.kinoyamboladmin.utilities.validation.Validator
import java.util.Locale

class DniValidator(
    val error: UiText
) : Validator<String> {
    override fun validate(texto: String): Validation {
        val letras = "TRWAGMYFPDXBNJZSQVHLCKE"
        val dni = texto.uppercase(Locale.getDefault())
        var hayError = dni.length != 9 || !dni.matches(Regex("[0-9]{8}[A-Z]"))
        if (!hayError) {
            val letra = dni.substring(8, 9)
            val index = Integer.parseInt(dni.substring(0, 8)) % 23
            hayError = letras.substring(index, index + 1) != letra
        }
        return object : Validation {
            override val hasError: Boolean
                get() = hayError
            override val errorMessage: UiText
                get() = error
        }
    }
}
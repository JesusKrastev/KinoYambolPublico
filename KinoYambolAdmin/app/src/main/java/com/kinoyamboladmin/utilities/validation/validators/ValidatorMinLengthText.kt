package com.kinoyamboladmin.utilities.validation.validators

import com.kinoyamboladmin.utilities.validation.Validation
import com.kinoyamboladmin.utilities.validation.Validator

class ValidatorMinLengthText(
    val minimumLength: Int,
    val error: String = "El texto debe ser mayor o igual a ${minimumLength}"
) : Validator<String> {
    override fun validate(texto: String): Validation {
        return object : Validation {
            override val hasError: Boolean
                get() = texto.length < minimumLength
            override val errorMessage: String
                get() = error
        }
    }
}

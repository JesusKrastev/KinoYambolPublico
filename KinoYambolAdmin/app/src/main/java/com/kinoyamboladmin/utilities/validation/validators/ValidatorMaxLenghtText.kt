package com.kinoyamboladmin.utilities.validation.validators

import com.kinoyamboladmin.utilities.validation.Validation
import com.kinoyamboladmin.utilities.validation.Validator

class ValidatorMaxLenghtText(
    val maximunSize: Int,
    val error: String = "El texto debe ser inferior o igual a $maximunSize"
) : Validator<String> {
    override fun validate(texto: String): Validation {
        return object : Validation {
            override val hasError: Boolean
                get() = texto.length > maximunSize
            override val errorMessage: String
                get() = error
        }
    }
}
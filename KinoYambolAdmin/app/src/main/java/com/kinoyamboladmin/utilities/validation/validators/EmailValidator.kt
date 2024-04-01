package com.kinoyamboladmin.utilities.validation.validators

import com.kinoyamboladmin.utilities.texts.UiText
import com.kinoyamboladmin.utilities.validation.Validation
import com.kinoyamboladmin.utilities.validation.Validator

class EmailValidator(
    val error: UiText
) : Validator<String> {
    override fun validate(texto: String): Validation {
        return object : Validation {
            override val hasError: Boolean
                get() = !Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})$").matches(texto)
            override val errorMessage: UiText
                get() = error
        }
    }
}
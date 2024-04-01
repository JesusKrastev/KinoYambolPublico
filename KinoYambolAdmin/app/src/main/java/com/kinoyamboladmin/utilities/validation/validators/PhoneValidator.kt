package com.kinoyamboladmin.utilities.validation.validators

import com.kinoyamboladmin.utilities.texts.UiText
import com.kinoyamboladmin.utilities.validation.Validation
import com.kinoyamboladmin.utilities.validation.Validator

class PhoneValidator(
    val error: UiText
) : Validator<String> {
    override fun validate(text: String): Validation {
        return object : Validation {
            override val hasError: Boolean
                get() = !Regex("^[0-9 ]{9,18}$").matches(text)
            override val errorMessage: UiText
                get() = error
        }
    }
}
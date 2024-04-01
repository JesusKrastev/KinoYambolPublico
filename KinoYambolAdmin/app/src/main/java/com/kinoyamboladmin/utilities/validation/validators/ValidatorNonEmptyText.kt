package com.kinoyamboladmin.utilities.validation.validators

import com.kinoyamboladmin.utilities.texts.UiText
import com.kinoyamboladmin.utilities.validation.Validation
import com.kinoyamboladmin.utilities.validation.Validator

class ValidatorNonEmptyText(
    val error: UiText
) : Validator<String> {
    override fun validate(text: String): Validation {
        return object : Validation {
            override val hasError: Boolean
                get() = text.isEmpty()
            override val errorMessage: UiText
                get() = error
        }
    }
}

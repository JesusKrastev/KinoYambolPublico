package com.kinoyamboladmin.utilities.validation.validators

import android.util.Range
import com.kinoyamboladmin.utilities.texts.UiText
import com.kinoyamboladmin.utilities.validation.Validation
import com.kinoyamboladmin.utilities.validation.Validator

class RealNumberValidator(
    val range: Range<Double> = Range(0.0, Double.MAX_VALUE),
    val error: UiText
) : Validator<String> {
    override fun validate(text: String): Validation {
        return object : Validation {
            override val hasError: Boolean
                get() = !text.matches(Regex("^[0-9]+(\\.[0-9]+)?$"))
                        ||
                        !range.contains(text.toDouble())
            override val errorMessage: UiText
                get() = error
        }
    }
}
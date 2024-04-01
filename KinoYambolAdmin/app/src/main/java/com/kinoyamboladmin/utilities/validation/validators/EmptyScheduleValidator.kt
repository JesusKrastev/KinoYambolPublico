package com.kinoyamboladmin.utilities.validation.validators

import com.kinoyamboladmin.models.Schedule
import com.kinoyamboladmin.utilities.texts.UiText
import com.kinoyamboladmin.utilities.validation.Validation
import com.kinoyamboladmin.utilities.validation.Validator

class EmptyScheduleValidator(
    val error: UiText
) : Validator<List<Schedule>> {
    override fun validate(schedules: List<Schedule>): Validation {
        return object : Validation {
            override val hasError: Boolean
                get() = schedules.all { it.movieSchedule.isEmpty() } ||
                        (schedules.any { schedule -> schedule.movieSchedule.values.any { hours -> hours.isEmpty() } } && // Check if there is at least one schedule without hours
                                schedules.any { it.movieSchedule.size > 1 }
                        )
            override val errorMessage: UiText
                get() = error
        }
    }
}
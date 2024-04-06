package com.kinoyamboladmin.data.mocks.gender

import com.kinoyamboladmin.R
import javax.inject.Inject

class GenderDaoMock @Inject constructor() {
    private var genders = mutableListOf<GenderMock>(
        GenderMock(
            id = 1,
            name = R.string.label_action_gender
        ),
        GenderMock(
            id = 2,
            name = R.string.label_adventure_gender
        ),
        GenderMock(
            id = 3,
            name = R.string.label_comedy_gender
        ),
        GenderMock(
            id = 4,
            name = R.string.label_drama_gender
        ),
        GenderMock(
            id = 5,
            name = R.string.label_thriller_gender
        ),
        GenderMock(
            id = 6,
            name = R.string.label_science_fiction_gender
        ),
        GenderMock(
            id = 7,
            name = R.string.label_terror_gender
        ),
        GenderMock(
            id = 8,
            name = R.string.label_romance_gender
        ),
        GenderMock(
            id = 9,
            name = R.string.label_fancy_gender
        ),
        GenderMock(
            id = 10,
            name = R.string.label_animation_gender
        ),
        GenderMock(
            id = 11,
            name = R.string.label_documentary_gender
        ),
        GenderMock(
            id = 12,
            name = R.string.label_suspense_gender
        ),
        GenderMock(
            id = 13,
            name = R.string.label_musical_gender
        ),
        GenderMock(
            id = 14,
            name = R.string.label_mystery_gender
        ),
        GenderMock(
            id = 15,
            name = R.string.label_western_gender
        ),
        GenderMock(
            id = 16,
            name = R.string.label_war_gender
        ),
        GenderMock(
            id = 17,
            name = R.string.label_crime_gender
        ),
        GenderMock(
            id = 18,
            name = R.string.label_biography_gender
        ),
        GenderMock(
            id = 19,
            name = R.string.label_sports_gender
        ),
        GenderMock(
            id = 20,
            name = R.string.label_historical_gender
        )
    )

    fun get(): MutableList<GenderMock> = genders
    fun get(id: Int): GenderMock? = genders.find { it.id == id }
}
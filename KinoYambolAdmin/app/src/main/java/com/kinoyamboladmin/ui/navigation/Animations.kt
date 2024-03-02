package com.kinoyamboladmin.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry

fun AnimatedContentTransitionScope<NavBackStackEntry>.entryFromRight(duracion: Int) =
    fadeIn(animationSpec = tween(duracion)) +
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                tween(duracion)
            )

fun AnimatedContentTransitionScope<NavBackStackEntry>.entryFromLeft(duracion: Int) =
    fadeIn(animationSpec = tween(duracion)) +
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                tween(duracion)
            )

fun AnimatedContentTransitionScope<NavBackStackEntry>.exitFromRight(duracion: Int) =
    fadeOut(animationSpec = tween(duracion)) +
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                tween(duracion)
            )

fun AnimatedContentTransitionScope<NavBackStackEntry>.exitFromLeft(duracion: Int) =
    fadeOut(animationSpec = tween(duracion)) +
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                tween(duracion)
            )

fun AnimatedContentTransitionScope<NavBackStackEntry>.exitFromBottom(duracion: Int) =
    fadeOut(animationSpec = tween(duracion)) +
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Down,
                tween(duracion)
            )

fun AnimatedContentTransitionScope<NavBackStackEntry>.exitFromTop(duracion: Int) =
    fadeOut(animationSpec = tween(duracion)) +
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Up,
                tween(duracion)
            )

fun AnimatedContentTransitionScope<NavBackStackEntry>.entryFromTop(duracion: Int) =
    fadeIn(animationSpec = tween(duracion)) +
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Up,
                tween(duracion)
            )

fun AnimatedContentTransitionScope<NavBackStackEntry>.entryFromBottom(duracion: Int) =
    fadeIn(animationSpec = tween(duracion)) +
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Up,
                tween(duracion)
            )
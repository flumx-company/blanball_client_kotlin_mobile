package com.example.blanball.presentation.navigation

import Destinations
import com.example.blanball.R

sealed class BottomNavItem(
    var icon: Int,
    var screen_route: String
) {
    object Home : BottomNavItem(
        icon = R.drawable.ic_home,
        screen_route = Destinations.HOME.route
    )

    object FutureEvents :
        BottomNavItem(
            icon = R.drawable.ic_future_events,
            screen_route = Destinations.FUTURE_EVENTS.route
        )

    object CreateNewEvent : BottomNavItem(
        icon = R.drawable.ic_create_new_event,
        screen_route = Destinations.CREATE_NEW_EVENT_STEP_1.route
    )

    object Rating : BottomNavItem(
        icon = R.drawable.ic_rating,
        screen_route = Destinations.RATING.route
    )

    object Chat : BottomNavItem(
        icon = R.drawable.ic_chats,
        screen_route = Destinations.CHATS.route
    )
}
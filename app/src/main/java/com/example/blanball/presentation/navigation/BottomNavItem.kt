package com.example.blanball.presentation.navigation

import Destinations
import com.example.blanball.R

sealed class BottomNavItem(var icon: Int, var screen_route: String) {
    object Home : BottomNavItem(icon = R.drawable.ic_home, Destinations.HOME.route)
    object FutureEvents : BottomNavItem(icon = R.drawable.ic_future_events,  Destinations.FUTURE_EVENTS.route)
    object CreateNewEvent : BottomNavItem(icon = R.drawable.ic_create_new_event, Destinations.CREATE_NEW_EVENT_STEP_1.route )
    object Rating : BottomNavItem(icon = R.drawable.ic_rating, Destinations.RATING.route )
    object Chat : BottomNavItem (icon = R.drawable.ic_chats, Destinations.CHATS.route)
}

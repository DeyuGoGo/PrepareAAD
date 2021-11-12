package com.intersense.myneighbor.navi

import kotlinx.coroutines.flow.MutableStateFlow

class NavigationManager {

    var commands = MutableStateFlow(NavigationDirections.none)

    fun navigate(
        directions: NavigationCommand
    ) {
        commands.value = directions
    }

}
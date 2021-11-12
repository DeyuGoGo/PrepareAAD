package com.intersense.myneighbor.navi

import androidx.navigation.NamedNavArgument


object NavigationDirections {

    val none = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "none"
    }

    val back = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "back"
    }
    val buttons_main = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "buttons_main"
    }
    val animation_main = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "animation_main"
    }

    val component_main = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "component_main"
    }

    val buttons_root = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "buttons_root"
    }
    val animation_root = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "animation_root"
    }

    val component_root = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "component_root"
    }

    val component_location = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "component_location"
    }

    val component_dialog = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "component_dialog"
    }

    val open_location_permission = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "open_location_permission"
    }

}





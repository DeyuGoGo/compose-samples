package com.intersense.myneighbor.navi

import androidx.navigation.compose.NamedNavArgument

interface NavigationCommand {

    val arguments: List<NamedNavArgument>

    val destination: String
}
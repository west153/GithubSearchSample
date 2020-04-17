package com.example.simplegithubsearch.utils

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator


fun NavController.navigateSafe(
  directions: NavDirections,
  args: Bundle? = null,
  navOptions: NavOptions? = null,
  extras: Navigator.Extras? = null
) {
  val resId = directions.actionId
  val action = currentDestination?.getAction(resId) ?: graph.getAction(resId)
  if (action != null && currentDestination?.id != action.destinationId) {
    navigate(resId, args ?: directions.arguments, navOptions, extras)
  }
}
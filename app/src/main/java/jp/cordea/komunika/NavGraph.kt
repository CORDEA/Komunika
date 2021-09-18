package jp.cordea.komunika

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import jp.cordea.komunika.ui.add.AddContact
import jp.cordea.komunika.ui.home.Home

object Destination {
    const val HOME = "home"
    const val ADD_CONTACT = "add_contact"
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = Destination.HOME) {
        composable(route = Destination.HOME) {
            Home(hiltViewModel(), navController)
        }
        composable(route = Destination.ADD_CONTACT) {
            AddContact(hiltViewModel())
        }
    }
}

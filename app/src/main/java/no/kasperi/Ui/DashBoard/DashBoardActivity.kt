package no.kasperi.Ui.DashBoard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import no.kasperi.Abstraction.AbstractActivity
import no.kasperi.food4u.R

class DashBoardActivity : AbstractActivity(R.layout.activity_dash_board) {

    override fun init() {
        val navView: BottomNavigationView = findViewById(R.id.bunn_nav_meny)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
    }

    override fun running() {}

    override fun stopped() {}
    override fun formatNumber(kalorier: Double): String? {
        TODO("Not yet implemented")
    }
}
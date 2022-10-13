package no.kasperi.Ui.Splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import no.kasperi.Ui.komIGang.KomIGangActivity
import no.kasperi.food4u.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        goToMain()
    }

    private fun goToMain() {
        Thread.sleep(2000)
        val i = Intent(this@SplashActivity, KomIGangActivity::class.java)
        finish()
        startActivity(i)
    }
}
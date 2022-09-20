package no.kasperi.Ui.Splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.content.ContextCompat.startActivity
import no.kasperi.Abstraction.AbstractActivity
import no.kasperi.Ui.Intro.IntroActivity
import no.kasperi.food4u.R

abstract class SplashActivity : AbstractActivity(R.layout.activity_splash) {
    override fun init() {
        Handler().postDelayed({
            startActivity(Intent(this, IntroActivity::class.java))
            finish()
        }, 3000)
    }

    override fun running() {}

    override fun stopped() {}
    }

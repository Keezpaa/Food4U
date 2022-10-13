package no.kasperi.Ui.Intro

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_intro.*
import no.kasperi.Abstraction.AbstractActivity
import no.kasperi.Ui.DashBoard.DashBoardActivity
import no.kasperi.Ui.Registrer.RegistrerActivity
import no.kasperi.Ui.loggInn.LoggInnActivity
import no.kasperi.food4u.R

class IntroActivity : AbstractActivity(R.layout.activity_intro) {

    private lateinit var viewModel: IntroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(IntroViewModel::class.java)
    }

    override fun init() {}

    override fun onStart() {
        super.onStart()
        val currentUser = viewModel.auth.currentUser
        updateUI(currentUser)
    }

    override fun formatNumber(kalorier: Double): String? {
        TODO("Not yet implemented")
    }

    override fun running() {
        intro_logginn_btn.setOnClickListener {
            startActivity(Intent(this, LoggInnActivity::class.java))
        }

        intro_registrer_btn.setOnClickListener {
            startActivity(Intent(this, RegistrerActivity::class.java))
        }
    }

    override fun stopped() {}

    private fun updateUI(user: FirebaseUser?) {
        user?.let { startActivity(Intent(this, DashBoardActivity::class.java)) }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        // You can't go back | Log in to proceed
    }
}

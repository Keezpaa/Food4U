package no.kasperi.Ui.Registrer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_registrer.*
import no.kasperi.Abstraction.AbstractActivity
import no.kasperi.Ui.komIGang.KomIGangActivity
import no.kasperi.food4u.R

class RegistrerActivity : AbstractActivity(R.layout.activity_registrer) {

    private lateinit var viewModel: RegistrerViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(RegistrerViewModel::class.java)
    }

    override fun running() {
        registrer_btn.setOnClickListener {
            viewModel.validateUserInput(
                registrer_brukernavn_input,
                registrer_email_input,
                registrer_pass_input,
                registrer_bekreft_pass_input
            )
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.registerSuccessful.observe(this, Observer {
            when (it) {
                true -> {
                    startActivity(Intent(this, KomIGangActivity::class.java))
                }
                false -> {
                    Toast.makeText(this, "Please enter valid credentials", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun stopped() {
        viewModel.registerSuccessful.removeObservers(this)
    }

    override fun formatNumber(kalorier: Double): String? {
        TODO("Not yet implemented")
    }
}
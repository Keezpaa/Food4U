package no.kasperi.Ui.glemtPassord

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_glemt_passord.*
import no.kasperi.Abstraction.AbstractActivity
import no.kasperi.Ui.loggInn.LoggInnActivity
import no.kasperi.food4u.R

class GlemtPassordActivity : AbstractActivity(R.layout.activity_glemt_passord) {

    private lateinit var viewModel : GlemtPassordViewHolder

    override fun init() {
        viewModel = ViewModelProvider(this).get(GlemtPassordViewHolder::class.java)
    }

    override fun running() {
        glemt_pass_btn.setOnClickListener{
            viewModel.verifyEmail(glemt_pass_email.text.toString())
        }

        viewModel.userVerified.observe(this, Observer {
            when (it) {
                true -> showSuccessDialog()
                false -> Toast.makeText(this, "User not found", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun stopped() {
        viewModel.userVerified.removeObservers(this)
    }

    override fun formatNumber(kalorier: Double): String? {
        TODO("Not yet implemented")
    }

    private fun showSuccessDialog() {
        Snackbar.make(findViewById(android.R.id.content), "The email has been sent. Please check your inbox", Snackbar.LENGTH_LONG)
            .show()

        startActivity(Intent(this, LoggInnActivity::class.java))
    }
}
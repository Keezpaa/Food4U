package no.kasperi.Ui.komIGang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_kom_igang.*
import no.kasperi.Abstraction.AbstractActivity
import no.kasperi.Ui.DashBoard.DashBoardActivity
import no.kasperi.food4u.R

class KomIGangActivity : AbstractActivity(R.layout.activity_kom_igang) {

    private lateinit var viewModel : KomIGangRepository

    override fun init() {
        viewModel = ViewModelProvider(this).get(KomIGangRepository::class.java)
    }

    override fun running() {
        komigang_viewpager.adapter = viewModel.adapter

        neste_btn.setOnClickListener{
            when(komigang_viewpager.currentItem){
                0 -> komigang_viewpager.currentItem = 1
                1 -> komigang_viewpager.currentItem = 2
                2 -> Unit
            }
        }

        komigang_btn.setOnClickListener{
            goToHomepage()
        }

        komigang_skip_btn.setOnClickListener{
            goToHomepage()
        }
    }

    override fun stopped() {}
    override fun formatNumber(kalorier: Double): String? {
        TODO("Not yet implemented")
    }

    fun goToHomepage(){
        startActivity(Intent(this, DashBoardActivity::class.java))
        finish()
    }
}
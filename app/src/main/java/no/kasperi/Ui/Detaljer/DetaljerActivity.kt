package no.kasperi.Ui.Detaljer

import android.content.Intent
import android.content.res.ColorStateList
import android.telephony.PhoneNumberUtils.formatNumber
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detaljer.*
import no.kasperi.Abstraction.AbstractActivity
import no.kasperi.Models.OppskriftMain
import no.kasperi.Ui.webView.WebViewActivity
import no.kasperi.food4u.R

abstract class DetaljerActivity : AbstractActivity(R.layout.activity_detaljer) {

    private lateinit var viewModel: DetaljerViewModel
    private lateinit var oppskrift: OppskriftMain
    private var erFavoritt = false

    override fun init() {
        viewModel = ViewModelProvider(this).get(DetaljerViewModel::class.java)
    }

    override fun running() {
        oppskrift = intent.getParcelableExtra<OppskriftMain?>("OPPSKRIFT")!!
        Log.d("Oppskrift element", oppskrift.toString())

        viewModel.erFavorittOppskrift(oppskrift)
        presentRecipeData(oppskrift)

        viewModel.adapter.submitList(oppskrift.oppskrift.ingredienser)
        ingredienser.adapter = viewModel.adapter

        viewModel.getCookTime(oppskrift.oppskrift.totalTid)

        dele_knapp.setOnClickListener {
            startActivity(Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(
                    Intent.EXTRA_TEXT,
                    "Kanskje det her er interessant for deg? ${oppskrift.oppskrift.tittel}. Sjekk det ut her: ${oppskrift.oppskrift.url}"
                )
            })
        }

        liker_knapp.setOnClickListener {
            when (erFavoritt) {
                true -> viewModel.fjernOppskriftFraFavoritt(oppskrift)
                false -> viewModel.leggTilSomFavoritt(oppskrift)
            }
        }

        se_oppskrift_btn.setOnClickListener {
            startActivity(
                Intent(this, WebViewActivity::class.java)
                    .putExtra("URL", oppskrift.oppskrift.url)
            )
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.matlaging_varighet.observe(this, Observer {
            varighet.text = it
        })

        viewModel.erFavorittOppskrift.observe(this, Observer {
            if (it != null) {
                erFavoritt = it
            }
            when (it) {
                true -> {
                    liker_knapp.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            applicationContext,
                            R.color.md_theme_dark_errorContainer
                        )
                    )
                }

                false -> {
                    liker_knapp.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            applicationContext,
                            R.color.md_theme_dark_error
                        )
                    )
                }
                else -> {}
            }
        })
    }

    private fun presentRecipeData(item: OppskriftMain) {
        Glide.with(this).load(item.oppskrift.bilde)
            .placeholder(R.mipmap.food4u_app_logo).into(oppskrift_kort_bakgrunn)

        oppskrift_tittel.text = item.oppskrift.tittel
        varighet.text = item.oppskrift.totalTid.toString()
        kalorier_text.text = formatNumber(item.oppskrift.kalorier)
        protein_text.text = item.oppskrift.totaleNaeringsstoffer.FAMS?.antall?.let { formatNumber(it) }
        karbohydrater_text.text = item.oppskrift.totaleNaeringsstoffer.CHOCDF?.antall?.let { formatNumber(it) }
        fett_text.text = item.oppskrift.totaleNaeringsstoffer.FAT?.antall?.let { formatNumber(it) }
    }

    override fun stopped() {
        viewModel.matlaging_varighet.removeObservers(this)
        viewModel.erFavorittOppskrift.removeObservers(this)
    }
}
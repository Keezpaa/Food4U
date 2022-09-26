package no.kasperi.Ui.DashBoard.Main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_main.*
import no.kasperi.Abstraction.AbstractFragment
import no.kasperi.Listeners.ForslagsElementClickListener
import no.kasperi.Listeners.OppskriftClickListener
import no.kasperi.Models.ForslagsElement
import no.kasperi.Models.HjemKategoriElement
import no.kasperi.Models.OppskriftMain
import no.kasperi.Ui.Detaljer.DetaljerActivity
import no.kasperi.Ui.Kategorier.KategoriResultatActivity
import no.kasperi.food4u.R


class MainFragment : AbstractFragment(R.layout.fragment_main) {

    private lateinit var viewModel: MainFragmentViewModel

    override fun init(view: View) {
        viewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)
    }

    override fun running() {
        kategorier_recycler.adapter = viewModel.kategoriAdapter

        hoved_skjerm_viewpager.adapter = viewModel.adapter
        hoved_skjerm_viewpager.offscreenPageLimit = 3

        getData()

        swipe_oppdater_layout.setOnRefreshListener {
            getData()
        }

        observeData()
    }

    private fun getData() {
        viewModel.getBrukernavn()
        viewModel.getHjemKategorier()
        viewModel.createPageTransformer()

        viewModel.getTimeOfDay(object : OppskriftClickListener {
            override fun onOppskriftClick(oppskrift: OppskriftMain) {
                startActivity(
                    Intent(requireContext(), DetaljerActivity::class.java)
                        .putExtra("OPPSKRIFT", oppskrift)
                )
            }
        })

        swipe_oppdater_layout.isRefreshing = false
    }

    override fun stop() {
        viewModel.removeObserver(this)
        viewModel.brukerVelkomstMelding.removeObservers(this)
        viewModel.brukernavn.removeObservers(this)
        viewModel.pageTransformer.removeObservers(this)
    }

    fun observeData() {
        viewModel.observeData(this, object : ForslagsElementClickListener {
            override fun onKategoriClick(kategori: ForslagsElement) {
                //Not a category
            }

            override fun onHjemKategoriClick(kategori: HjemKategoriElement) {
                startActivity(
                    Intent(context, KategoriResultatActivity::class.java)
                        .putExtra("KATEGORI", kategori.kategoriNavn)
                )
            }
        })

        viewModel.brukerVelkomstMelding.observe(this, Observer {
            hoved_skjerm_header.text = it ?: ""
        })

        viewModel.brukernavn.observe(this, Observer {
            hjem_header_brukernavn.text = it.capitalize()
        })

        viewModel.pageTransformer.observe(this, Observer {
            hoved_skjerm_viewpager.setPageTransformer(it)
        })
    }
}
package no.kasperi.Ui.DashBoard.Kategorier


import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import no.kasperi.Abstraction.AbstractFragment
import no.kasperi.Listeners.ForslagsElementClickListener
import no.kasperi.Models.ForslagsElement
import no.kasperi.Models.HjemKategoriElement
import no.kasperi.Ui.Kategorier.KategoriResultatActivity
import no.kasperi.food4u.R

class KategoriFragment : AbstractFragment(R.layout.fragment_kategori) {

    private lateinit var viewModel: KategoriViewModel

    override fun init(view: View) {
        viewModel = ViewModelProvider(this).get(KategoriViewModel::class.java)
    }

    override fun running() {
        viewModel.initKategorier()

        kategori_skjerm_recycler.adapter = viewModel.adapter

        viewModel.observeData(this, object : ForslagsElementClickListener {
            override fun onKategoriClick(category: ForslagsElement) {
                startActivity(
                    Intent(context, KategoriResultatActivity::class.java)
                    .putExtra("KATEGORI",kategori.kategoriNavn))
            }

            override fun onHjemKategoriClick(category: HjemKategoriElement) {
                // Not a home category
            }
        })
    }

    override fun stop() {
        viewModel.removeObserver(this)
    }
}
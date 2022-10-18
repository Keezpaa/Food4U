package no.kasperi.Ui.Kategorier

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_kategori_resultat.*
import no.kasperi.Abstraction.AbstractActivity
import no.kasperi.Listeners.OppskriftClickListener
import no.kasperi.Models.OppskriftMain
import no.kasperi.Ui.Detaljer.DetaljerActivity
import no.kasperi.food4u.R

class KategoriResultatActivity : AbstractActivity(R.layout.activity_kategori_resultat) {

    private lateinit var viewModel: KategoriResultatViewModel
    private lateinit var categoryName: String

    override fun init() {
        viewModel = ViewModelProvider(this).get(KategoriResultatViewModel::class.java)
    }

    override fun running() {
        viewModel.clearCounters()

        categoryName = intent.getStringExtra("CATEGORY") ?: ""
        kategori_resultat_subtext.text = categoryName

        getData()
        kategori_resultat_recycler.adapter = viewModel.adapter

        swipe_refresh_layout.setOnRefreshListener {
            viewModel.getResults(categoryName.toLowerCase())
            swipe_refresh_layout.isRefreshing = false
        }

        viewModel.observeData(this, object : OppskriftClickListener {
            override fun onOppskriftClick(oppskrift: OppskriftMain) {
                startActivity(
                    Intent(this@KategoriResultatActivity, DetaljerActivity::class.java)
                        .putExtra("RECIPE", oppskrift)
                )
            }
        })

        viewModel.isLoading.observe(this, Observer {
            when(it){
                true -> kategori_resultat_lasting.visibility = View.VISIBLE
                false -> kategori_resultat_lasting.visibility = View.GONE
            }
        })
    }

    override fun stopped() {
        viewModel.removeObserve(this)
    }

    override fun formatNumber(kalorier: Double): String? {
        TODO("Not yet implemented")
    }

    private fun getData() {
        viewModel.getResults(categoryName.toLowerCase())
        observeDataPaging()
    }

    fun observeDataPaging() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            kategori_resultat_recycler.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (!kategori_resultat_recycler.canScrollVertically(1)) {
                    viewModel.loadMoreRecipes(categoryName.toLowerCase())
                }
            }
        }
    }
}
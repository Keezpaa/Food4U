package no.kasperi.Ui.Resultater

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_filter_resultat.*
import kotlinx.android.synthetic.main.activity_kategori_resultat.*
import no.kasperi.Abstraction.AbstractActivity
import no.kasperi.Listeners.OppskriftClickListener
import no.kasperi.Models.OppskriftMain
import no.kasperi.Ui.Detaljer.DetaljerActivity
import no.kasperi.food4u.R

class FilterResultatActivity : AbstractActivity(R.layout.activity_filter_resultat) {

    private lateinit var viewModel: FilterResultatViewModel
    private var kcalMinVerdi = 0.0
    private var kcalMaxVerdi = 0.0
    private lateinit var matType: String
    private lateinit var diettType: String

    override fun init() {
        viewModel = ViewModelProvider(this).get(FilterResultatViewModel::class.java)
    }

    @SuppressLint("SetTextI18n")
    override fun running() {
        kcalMinVerdi = intent.getDoubleExtra("KCAL_MIN_VALUE", 0.0)
        kcalMaxVerdi = intent.getDoubleExtra("KCAL_MAX_VALUE", 0.0)
        matType = intent.getStringExtra("MEAL_TYPE") ?: ""
        diettType = intent.getStringExtra("DIET_TYPE") ?: ""

        kalori_min_max.text =
            "Kalorier fra : ${kcalMinVerdi.toInt()} opptil ${kcalMaxVerdi.toInt()} kcal"
        mat_type.text = "Mat Type : $matType"
        diett_type.text = "Diett Type : ${diettType.capitalize()}"

        resultat_skjerm_recycler.adapter = viewModel.adapter

        getData()

        swipe_oppdater_layout.setOnRefreshListener {
            getData()
        }

        viewModel.observeData(this)

        viewModel.errorCase.observe(this, Observer {
            when(it){
                true -> {
                    Snackbar.make(findViewById(android.R.id.content), application.resources.getText(R.string.ingen_resultater), Snackbar.LENGTH_LONG)
                        .setAction("Go back") {
                            onBackPressed()
                        }
                        .show()
                }

                false -> Unit
            }
        })
    }

    override fun stopped() {
        viewModel.removeObservers(this)
    }

    override fun formatNumber(kalorier: Double): String? {
        TODO("Not yet implemented")
    }


    private fun getData() {
        viewModel.clearCounters()

        viewModel.getRecipeData(
            kcalMinVerdi.toInt(),
            kcalMaxVerdi.toInt(),
            matType,
            diettType,
            object : OppskriftClickListener {
                override fun onOppskriftClick(oppskrift: OppskriftMain) {
                    startActivity(
                        Intent(this@FilterResultatActivity, DetaljerActivity::class.java)
                            .putExtra("OPPSKRIFT", oppskrift)
                    )
                }
            })

        swipe_refresh_layout.isRefreshing = false

        observeDataPaging()
    }

    fun observeDataPaging() {
        resultat_skjerm_recycler.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (!resultat_skjerm_recycler.canScrollVertically(1)) {
                viewModel.getRecipeData(
                    kcalMinVerdi.toInt(),
                    kcalMaxVerdi.toInt(),
                    matType,
                    diettType,
                    object : OppskriftClickListener {
                        override fun onOppskriftClick(recipe: OppskriftMain) {
                            startActivity(
                                Intent(this@FilterResultatActivity, DetaljerActivity::class.java)
                                    .putExtra("OPPSKRIFT", recipe)
                            )
                        }
                    })
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
    }
}
package no.kasperi.Ui.DashBoard.Søk

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_sok.*
import no.kasperi.Abstraction.AbstractFragment
import no.kasperi.Listeners.OppskriftClickListener
import no.kasperi.Listeners.QueryClickListener
import no.kasperi.Models.OppskriftMain
import no.kasperi.Models.QueryModel
import no.kasperi.Ui.Detaljer.DetaljerActivity
import no.kasperi.food4u.R

class SokFragment : AbstractFragment(R.layout.fragment_sok) {

    private lateinit var viewModel: SokViewModel

    override fun init(view: View) {
        viewModel = ViewModelProvider(this).get(SokViewModel::class.java)
    }

    override fun running() {
        viewModel.getRecentUserQueries()

        sok_sokebar.setOnClickListener {
            sok_sokebar.isIconified = false
        }

        sok_sokebar.clearFocus()

        sok_sokebar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                sok_sokebar.clearFocus()
                sok_sokebar.setQuery("", false)
                Log.d("HEI", query)

                viewModel.addQueryToDb(QueryModel(query!!))
                viewModel.clearCounters()
                callApiForResuls(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        sok_recycler.adapter = viewModel.adapter

        viewModel.observeData(this,object : QueryClickListener {
            override fun onQueryClick(query: QueryModel) {
                callApiForResuls(query.queryNavn)
            }
        })
    }

    override fun stop() {
        viewModel.removeObservers(this)
    }

    private fun callApiForResuls(query : String){
        viewModel.getDataFromRepository(query, object : OppskriftClickListener {
            override fun onOppskriftClick(oppskrift: OppskriftMain) {
                startActivity( Intent(requireContext(), DetaljerActivity::class.java)
                    .putExtra("OPPSKRIFT", oppskrift))
            }
        })

        observeDataPaging(query)
    }

    fun observeDataPaging(query : String){
        sok_recycler.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (!sok_recycler.canScrollVertically(1)) {
                viewModel.loadMoreRecipes(query)
            }
        }
    }
}

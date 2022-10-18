package no.kasperi.Ui.DashBoard.Søk

import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import no.kasperi.Adapters.SokAdapter
import no.kasperi.Listeners.ElementClickListener
import no.kasperi.Listeners.OppskriftClickListener
import no.kasperi.Listeners.QueryClickListener
import no.kasperi.Models.OppskriftMain
import no.kasperi.Models.QueryModel
import no.kasperi.Models.TomModel

class SokViewModel : ViewModel(), ElementClickListener {

    private val repo = SokRepository()
    val data = repo.data
    val recentQueries = repo.nyligQueries
    val emptyQueries = repo.tommeQueries
    private lateinit var callback: OppskriftClickListener
    private lateinit var queryCallback: QueryClickListener
    val adapter = SokAdapter(this)

    fun getDataFromRepository(query: String, callback: OppskriftClickListener) {
        this.callback = callback
        repo.getDataFromApi(query)
    }

    fun observeData(owner: LifecycleOwner, queryCallback: QueryClickListener) {
        this.queryCallback = queryCallback

        data.observe(owner, Observer {
            it?.let {
                Log.d("", it.toString())

                adapter.submitList(it)
                adapter.notifyDataSetChanged()
            }
        })

        recentQueries.observe(owner, Observer {
            it?.let { adapter.submitList(it) }
        })

        emptyQueries.observe(owner, Observer {
            when (it) {
                true -> {
                    adapter.submitList(listOf(TomModel()))
                    adapter.notifyDataSetChanged()
                }
                false -> Unit
            }
        })
    }

    fun removeObservers(owner: LifecycleOwner) {
        data.removeObservers(owner)
        recentQueries.removeObservers(owner)
        emptyQueries.removeObservers(owner)
    }

    override fun onElementClick(view: View) {
        when (view.tag) {
            is OppskriftMain -> callback.onOppskriftClick(view.tag as OppskriftMain)
            is QueryModel -> queryCallback.onQueryClick(view.tag as QueryModel)
        }
    }

    fun getRecentUserQueries() {
        repo.getRecentUserQueries()
    }

    fun addQueryToDb(query: QueryModel) {
        repo.addQueryToDb(query)
    }

    fun loadMoreRecipes(query: String) {
        repo.getDataFromApi(query)
    }

    fun clearCounters() {
        repo.clearCounters()
    }
}
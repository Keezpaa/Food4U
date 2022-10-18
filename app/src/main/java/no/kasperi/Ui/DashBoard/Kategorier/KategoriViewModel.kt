package no.kasperi.Ui.DashBoard.Kategorier

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import no.kasperi.Adapters.KategoriAdapter
import no.kasperi.Listeners.ElementClickListener
import no.kasperi.Listeners.ForslagsElementClickListener
import no.kasperi.Models.ForslagsElement

class KategoriViewModel : ViewModel(), ElementClickListener {

    private val repo = KategoriRepository()
    val kategorier = repo.kategorier
    val adapter = KategoriAdapter(this)
    private lateinit var callback: ForslagsElementClickListener

    fun initKategorier() {
        repo.getKategorier()
    }

    fun observeData(owner: LifecycleOwner, callback: ForslagsElementClickListener) {
        this.callback = callback
        kategorier.observe(owner, Observer {
            adapter.submitList(it)
        })
    }

    fun removeObserver(owner: LifecycleOwner) {
        kategorier.removeObservers(owner)
    }

    override fun onElementClick(view: View) {
        when (view.tag) {
            is ForslagsElement -> {
                callback.onKategoriClick(view.tag as ForslagsElement)
            }
        }
    }


}

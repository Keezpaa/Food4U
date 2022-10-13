package no.kasperi.Ui.DashBoard.Main

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import no.kasperi.Adapters.HjemsideAdapter
import no.kasperi.Adapters.KategoriAdapter
import no.kasperi.Listeners.ElementClickListener
import no.kasperi.Listeners.ForslagsElementClickListener
import no.kasperi.Listeners.OppskriftClickListener
import no.kasperi.Models.HjemKategoriElement
import no.kasperi.Models.OppskriftMain
import no.kasperi.Models.ShimmerModel
import java.util.*

class MainFragmentViewModel : ViewModel(), ElementClickListener {

    private lateinit var callback: OppskriftClickListener
    private lateinit var kategoriCallback: ForslagsElementClickListener
    private val repo = MainFragmentRepository()
    val oppskrifter = repo.oppskrifter
    val brukernavn = repo.brukernavn
    val brukerVelkomstMelding = MutableLiveData<String>()
    val kategorier = repo.hjemKategorier
    val adapter = HjemsideAdapter(this)
    val kategoriAdapter = KategoriAdapter(this)
    val pageTransformer = MutableLiveData<CompositePageTransformer>()

    init {
        adapter.submitList(listOf(ShimmerModel(), ShimmerModel(), ShimmerModel()))
    }

    @SuppressLint("NotifyDataSetChanged")
    fun observeData(owner: LifecycleOwner, kategoriCallback: ForslagsElementClickListener) {
        this.kategoriCallback = kategoriCallback

        oppskrifter.observe(owner, Observer {
            Log.d("data is", "data is $it")

            if (it != null) {
                adapter.submitList(it.hits)
            }
            adapter.notifyDataSetChanged()
        })

        kategorier.observe(owner, Observer {
            Log.d("home categories", "home categories : $it")
            kategoriAdapter.submitList(it)
        })
    }

    fun removeObserver(owner: LifecycleOwner) {
        oppskrifter.removeObservers(owner)
        kategorier.removeObservers(owner)
    }

    fun getTimeOfDay(callback: OppskriftClickListener) {
        this.callback = callback
        val calendar = Calendar.getInstance()

        when (calendar.get(Calendar.HOUR_OF_DAY)) {
            in 1..11 -> {
                brukerVelkomstMelding.value = "God Morgen"
                repo.getMatTypeResultat("frokost")
            }
            in 12..15 -> {
                brukerVelkomstMelding.value = "God Ettermiddag"
                repo.getMatTypeResultat("lunsj")
            }
            in 16..24 -> {
                brukerVelkomstMelding.value = "God Kveld"
                repo.getMatTypeResultat("middag")
            }
            else -> Unit
        }
    }

    fun getBrukernavn() {
        repo.getUsername()
    }

    fun getHjemKategorier() {
        repo.getHjemKategorier()
    }

    override fun onItemClick(view: View) {
        when (view.tag) {
            is OppskriftMain ->  callback.onOppskriftClick(view.tag as OppskriftMain)
            is HjemKategoriElement -> kategoriCallback.onHjemKategoriClick(view.tag as HjemKategoriElement)
        }
    }

    fun createPageTransformer() {
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page: View, position: Float ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        pageTransformer.value = compositePageTransformer
    }
}



package no.kasperi.Ui.Kategorier

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import no.kasperi.Adapters.OppskriftAdapter
import no.kasperi.Listeners.ElementClickListener
import no.kasperi.Listeners.OppskriftClickListener
import no.kasperi.Models.OppskriftMain
import no.kasperi.Models.ShimmerModel

class KategoriResultatViewModel : ViewModel(), ElementClickListener {

    private val repo = KategoriResultatRepository()
    val data = repo.data
    val isLoading = MutableLiveData<Boolean>()
    val adapter = OppskriftAdapter(this)
    private lateinit var callback: OppskriftClickListener

    init {
        adapter.submitList(
            listOf(
                ShimmerModel(),
                ShimmerModel(),
                ShimmerModel(),
                ShimmerModel(),
                ShimmerModel(),
                ShimmerModel(),
                ShimmerModel(),
                ShimmerModel()
            )
        )
    }

    fun getResults(cuisineType: String) {
        isLoading.value = true
        val newCuisineType = cuisineType.replace("\\s".toRegex(), "")
        when (newCuisineType) {
            "frokost", "lunsj","middag","mellommåltid","te" -> repo.getMealTypeResults(newCuisineType)
            "suppe", "salat", "dessert","frokostblanding","pannekake","forrett","omelett" -> repo.getDishTypeResults(newCuisineType)
            "indisk", "kinesisk", "italiensk","mexikansk","japansk" -> repo.getCuisineTypeResults(newCuisineType)
            else -> Unit
        }
    }

    fun observeData(owner: LifecycleOwner, callback: OppskriftClickListener) {
        this.callback = callback

        data.observe(owner, Observer {
            isLoading.value = false
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })
    }

    fun removeObserve(owner: LifecycleOwner) {
        data.removeObservers(owner)
    }

    fun loadMoreRecipes(cuisineType: String) {
        getResults(cuisineType)
    }

    fun clearCounters() {
        repo.clearCounters()
    }

    override fun onElementClick(view: View) {
        when (view.tag) {
            is OppskriftMain -> {
                callback.onOppskriftClick(view.tag as OppskriftMain)
            }
        }
    }
}
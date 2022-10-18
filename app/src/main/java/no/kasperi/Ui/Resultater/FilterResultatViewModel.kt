package no.kasperi.Ui.Resultater

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import no.kasperi.Adapters.OppskriftAdapter
import no.kasperi.Listeners.ElementClickListener
import no.kasperi.Listeners.OppskriftClickListener
import no.kasperi.Models.OppskriftMain
import no.kasperi.Models.ShimmerModel

class FilterResultatViewModel : ViewModel(), ElementClickListener {

    private val repo = FilterResultatRepository()
    val data = repo.data
    val errorCase = repo.errorData
    private lateinit var callback: OppskriftClickListener
    val adapter = OppskriftAdapter(this)

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

    fun getRecipeData(
        kcalMinValue: Int,
        kcalMaxValue: Int,
        mealType: String,
        dietType: String,
        callback: OppskriftClickListener
    ) {
        this.callback = callback
        repo.getDataFromApi(kcalMinValue, kcalMaxValue, mealType, dietType)
    }

    fun observeData(owner: LifecycleOwner) {
        data.observe(owner, Observer {
            when(it.size){
                0 -> Unit
                else -> {
                    adapter.submitList(it)
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }

    fun removeObservers(owner: LifecycleOwner) {
        data.removeObservers(owner)
    }

    fun clearCounters() {
        repo.clearCounters()
    }

    override fun onElementClick(view: View) {
        when (view.tag) {
            is OppskriftMain -> callback.onOppskriftClick(view.tag as OppskriftMain)
        }
    }
}

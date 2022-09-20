package no.kasperi.Ui.Detaljer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import no.kasperi.Models.OppskriftMain

class DetaljerViewModel : ViewModel() {

    private val repo = DetaljerRepository()
    val erFavorittOppskrift = repo.erFavorittOppskrift
    val matlaging_varighet = MutableLiveData<String>()
    val adapter = OppskriftIngredienserAdapter()

    fun getCookTime(totalTime: Double) {
        val timer: Int = totalTime.toInt() / 60
        val minutter: Int = totalTime.toInt() % 60

        if (timer < 1 && minutter > 1) {
            matlaging_varighet.value = "Varighet: $minutter minutter"
        } else if (timer > 1 && minutter < 1) {
            matlaging_varighet.value = "Varighet: $timer"
        } else if (timer < 1 && minutter < 1) {
            matlaging_varighet.value = "Varighet: N/A"
        } else {
            matlaging_varighet.value = "Varighet: $timer timer og $minutter minutter"
        }
    }

    fun erFavorittOppskrift(recipe: OppskriftMain) {
        repo.erFavorittOppskrift(recipe)
    }

    fun leggTilSomFavoritt(recipe: OppskriftMain) {
        repo.leggTilSomFavoritt(recipe)
    }

    fun fjernOppskriftFraFavoritt(recipe: OppskriftMain) {
        repo.fjernOppskriftFraFavoritt(recipe)
    }
}
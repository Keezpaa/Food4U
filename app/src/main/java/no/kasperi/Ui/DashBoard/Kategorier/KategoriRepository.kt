package no.kasperi.Ui.DashBoard.Kategorier

import androidx.lifecycle.MutableLiveData
import no.kasperi.Models.ForslagsElement
import no.kasperi.Models.ForslagsModel
import no.kasperi.food4u.R

class KategoriRepository {

    val kategorier = MutableLiveData<List<ForslagsModel>>()

    fun getKategorier() {
        kategorier.value = listOf(
            ForslagsModel(
                "Meal Types", listOf(
                    ForslagsElement("Breakfast", R.drawable.ikon_frokost),
                    ForslagsElement("Lunch", R.drawable.ikon_lunsj),
                    ForslagsElement("Dinner", R.drawable.ikon_frokost),
                    ForslagsElement("Snack", R.drawable.ikon_snacks),
                    ForslagsElement("Tea Time", R.drawable.ikon_te)
                )
            ),
            ForslagsModel(
                "Dish Types", listOf(
                    ForslagsElement("Soup", R.drawable.ikon_suppe),
                    ForslagsElement("Salad", R.drawable.ikon_salat),
                    ForslagsElement("Dessert", R.drawable.ikon_dessert),
                    ForslagsElement("Cereals", R.drawable.ikon_frokostblanding),
                    ForslagsElement("Pancake", R.drawable.ikon_pannekake),
                    ForslagsElement("Starter", R.drawable.ikon_forrett),
                    ForslagsElement("Omelet", R.drawable.ikon_omelett)
                )
            ),
            ForslagsModel(
                "Cuisine Types", listOf(
                    ForslagsElement("Indian", R.drawable.ikon_indisk),
                    ForslagsElement("Chinese", R.drawable.ikon_kinesisk),
                    ForslagsElement("Italian", R.drawable.ikon_italiensk),
                    ForslagsElement("Mexican", R.drawable.ikon_meksikansk),
                    ForslagsElement("Japanese", R.drawable.ikon_sushi)
                )
            )
        )
    }
}
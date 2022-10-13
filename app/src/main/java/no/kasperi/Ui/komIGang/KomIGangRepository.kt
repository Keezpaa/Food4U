package no.kasperi.Ui.komIGang

import androidx.lifecycle.ViewModel
import no.kasperi.Models.KomIGangElement
import no.kasperi.Ui.komIGang.Adapter.KomIGangAdapter
import no.kasperi.food4u.R

class KomIGangRepository : ViewModel() {

    val adapter = KomIGangAdapter()

    init {
        createOnboardingItems()
    }

    fun createOnboardingItems() {
        adapter.submitList(
            listOf(
                KomIGangElement(
                    R.drawable.komigang_ikon1,
                    "Find your next recipe",
                    "You can now find your next recipe according to what you desire.Search over 2 million recipes by diets, calories and nutrient ranges"
                ),
                KomIGangElement(
                    R.drawable.komigang_ikon2,
                    "Add recipes to your favorites",
                    "You can now save your favorite recipes. You can now access your favorite recipes in the Favorites section."
                ),
                KomIGangElement(
                    R.drawable.komigang_ikon3,
                    "Share a recipe",
                    "Do you want to send a recipe to someone? We got you covered. You can share a recipe by clicking the share button."
                )
            )
        )
    }
}
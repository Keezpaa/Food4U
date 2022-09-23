package no.kasperi.Ui.DashBoard.Filter

import androidx.lifecycle.ViewModel

class FilterViewModel : ViewModel() {

    fun checkIfValid(
        kaloriMin: String,
        kaloriMax: String,
        matType: String,
        diettType: String
    ): Any {
        return kaloriMin.isNotEmpty() && kaloriMax.isNotEmpty() && matType.isNotEmpty() && diettType.isNotEmpty()
    }
}
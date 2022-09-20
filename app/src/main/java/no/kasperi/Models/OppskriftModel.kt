package no.kasperi.Models

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import kotlinx.android.parcel.Parcelize

@Parcelize
class OppskriftModel(

    val tittel: String,
    val bilde: String?,
    val url: String,
    val delSom: String,
    val ingrediensLinjer: List<String>,
    val ingredienser: List<IngrediensModel>,
    val kalorier: Double,
    val totalVekt: Double,
    val totalTid: Double,

    @PropertyName("totaleNaeringsstoffer")
    val totaleNaeringsstoffer : TotaleNaeringsstoffMainModel

) : LocalModel, Parcelable {
    constructor() : this(
        "", "", "", "", emptyList(), emptyList(), 0.0, 0.0, 0.0,
        TotaleNaeringsstoffMainModel(
        NaeringsstoffModel("", 0.0, ""),
        NaeringsstoffModel("", 0.0, ""),
        NaeringsstoffModel("", 0.0, ""),
        NaeringsstoffModel("", 0.0, ""),
        NaeringsstoffModel("", 0.0, ""),
        NaeringsstoffModel("", 0.0, ""),
        NaeringsstoffModel("", 0.0, ""),
        NaeringsstoffModel("", 0.0, ""),
        NaeringsstoffModel("", 0.0, ""),
        NaeringsstoffModel("", 0.0, ""),
        NaeringsstoffModel("", 0.0, ""),
        NaeringsstoffModel("", 0.0, ""),
        NaeringsstoffModel("", 0.0, ""),
        NaeringsstoffModel("", 0.0, ""),
        NaeringsstoffModel("", 0.0, ""),
        NaeringsstoffModel("", 0.0, ""),
        NaeringsstoffModel("", 0.0, ""),
        NaeringsstoffModel("", 0.0, ""),
        NaeringsstoffModel("", 0.0, ""),
        NaeringsstoffModel("", 0.0, ""),
        NaeringsstoffModel("", 0.0, ""),
        NaeringsstoffModel("", 0.0, ""),
        NaeringsstoffModel("", 0.0, ""),
        NaeringsstoffModel("", 0.0, ""),
        NaeringsstoffModel("", 0.0, ""),
        NaeringsstoffModel("", 0.0, ""),
        NaeringsstoffModel("", 0.0, ""),
        NaeringsstoffModel("", 0.0, ""),
        NaeringsstoffModel("", 0.0, ""),
        NaeringsstoffModel("", 0.0, ""),
        NaeringsstoffModel("", 0.0, ""),
        NaeringsstoffModel("", 0.0, "")
        )
    )
}
package no.kasperi.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class IngrediensModel (

    val text: String,
    val vekt: Double,
    val matKategori: String?,
    val matId: String,
    val bilde: String?
) : LocalModel, Parcelable {
    constructor() : this(
        "", 0.0, "", "", ""
    )
}
package no.kasperi.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class NaeringsstoffModel(

    val tittel: String,
    val antall: Double,
    val enhet: String
) : LocalModel, Parcelable {
    constructor() : this(
        "", 0.0, ""
    )
}
package no.kasperi.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class OppskriftMain(

    val oppskrift: OppskriftModel
) : LocalModel, Parcelable {
    constructor() : this(
        OppskriftModel(
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
    )
}
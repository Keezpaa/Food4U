package no.kasperi.Models

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import kotlinx.android.parcel.Parcelize

@Parcelize
class TotaleNaeringsstoffMainModel (
    @PropertyName("enerc_KCAL")
    val ENERC_KCAL: NaeringsstoffModel?,
    @PropertyName("fat")
    val FAT: NaeringsstoffModel?,
    @PropertyName("fasat")
    val FASAT: NaeringsstoffModel?,
    @PropertyName("fatrn")
    val FATRN: NaeringsstoffModel?,
    @PropertyName("fams")
    val FAMS: NaeringsstoffModel?,
    @PropertyName("fapu")
    val FAPU: NaeringsstoffModel?,
    @PropertyName("chocdf")
    val CHOCDF: NaeringsstoffModel?,
    @PropertyName("fibtg")
    val FIBTG: NaeringsstoffModel?,
    @PropertyName("sugar")
    val SUGAR: NaeringsstoffModel?,
    @PropertyName("procnt")
    val PROCNT: NaeringsstoffModel?,
    @PropertyName("chole")
    val CHOLE: NaeringsstoffModel?,
    @PropertyName("na")
    val NA: NaeringsstoffModel?,
    @PropertyName("ca")
    val CA: NaeringsstoffModel?,
    @PropertyName("mg")
    val MG: NaeringsstoffModel?,
    @PropertyName("k")
    val K: NaeringsstoffModel?,
    @PropertyName("fe")
    val FE: NaeringsstoffModel?,
    @PropertyName("zn")
    val ZN: NaeringsstoffModel?,
    @PropertyName("p")
    val P: NaeringsstoffModel?,
    @PropertyName("vita_RAE")
    val VITA_RAE: NaeringsstoffModel?,
    @PropertyName("vitc")
    val VITC: NaeringsstoffModel?,
    @PropertyName("thia")
    val THIA: NaeringsstoffModel?,
    @PropertyName("ribf")
    val RIBF: NaeringsstoffModel?,
    @PropertyName("nia")
    val NIA: NaeringsstoffModel?,
    @PropertyName("vitb76A")
    val VITB6A: NaeringsstoffModel?,
    @PropertyName("foldfe")
    val FOLDFE: NaeringsstoffModel?,
    @PropertyName("folfd")
    val FOLFD: NaeringsstoffModel?,
    @PropertyName("folac")
    val FOLAC: NaeringsstoffModel?,
    @PropertyName("vitb12")
    val VITB12: NaeringsstoffModel?,
    @PropertyName("vitd")
    val VITD: NaeringsstoffModel?,
    @PropertyName("tocpha")
    val TOCPHA: NaeringsstoffModel?,
    @PropertyName("vitk1")
    val VITK1: NaeringsstoffModel,
    @PropertyName("water")
    val WATER: NaeringsstoffModel?
) : LocalModel, Parcelable {
    constructor() : this (
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
}

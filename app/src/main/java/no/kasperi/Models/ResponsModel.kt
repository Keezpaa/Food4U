package no.kasperi.Models

class ResponsModel(
    val q: String,
    val from: Int,
    val to: Int,
    val more: Boolean,
    val count: Int,
    val hits: List<OppskriftMain>
) : LocalModel
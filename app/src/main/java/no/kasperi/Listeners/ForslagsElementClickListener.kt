package no.kasperi.Listeners

import no.kasperi.Models.ForslagsElement
import no.kasperi.Models.HjemKategoriElement

interface ForslagsElementClickListener {

    fun onKategoriClick(kategori : ForslagsElement)
    fun onHjemKategoriClick(kategori : HjemKategoriElement)
}
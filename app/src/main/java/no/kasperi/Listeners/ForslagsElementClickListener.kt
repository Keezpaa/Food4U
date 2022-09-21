package no.kasperi.Listeners

import no.kasperi.Models.ForslagsElement
import no.kasperi.Models.HjemKategoriElement

interface ForslagsElementClickListener {

    fun onKategoriClick(category : ForslagsElement)
    fun onHjemKategoriClick(category : HjemKategoriElement)
}
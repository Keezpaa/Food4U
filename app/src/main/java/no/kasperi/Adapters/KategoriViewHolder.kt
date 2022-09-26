package no.kasperi.Adapters

import android.view.View
import kotlinx.android.synthetic.main.holder_kategori_element_rad.view.*
import kotlinx.android.synthetic.main.holder_kategori_forelder_element_rad.view.*
import no.kasperi.Abstraction.AbstractViewHolder
import no.kasperi.Listeners.ElementClickListener
import no.kasperi.Models.ForslagsElement
import no.kasperi.Models.ForslagsModel
import no.kasperi.Models.HjemKategoriElement
import no.kasperi.Models.LocalModel

class KategoriViewHolder(itemView : View, callback : ElementClickListener) : AbstractViewHolder(itemView,callback){

    private var callback : ElementClickListener = callback

    override fun presentData(data: LocalModel) {
        when(data){
            is ForslagsElement -> {
                itemView.kategori_bilde.setImageResource(data.kategoriBilde)
                itemView.kategori_txt.text = data.kategoriNavn
            }

            is HjemKategoriElement -> {
                itemView.kategori_bilde.setImageResource(data.kategoriBilde)
                itemView.kategori_txt.text = data.kategoriNavn
            }

            is ForslagsModel -> {
                itemView.kategorier_forelder_header.text = data.header
                val adapter = KategoriChildAdapter(callback)
                itemView.kategorier_barn_recycler.adapter = adapter
                adapter.submitList(data.list)
            }
        }
    }
}
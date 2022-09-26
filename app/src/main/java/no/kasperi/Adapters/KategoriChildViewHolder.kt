package no.kasperi.Adapters

import android.view.View
import kotlinx.android.synthetic.main.holder_kategori_element_rad.view.*
import no.kasperi.Abstraction.AbstractViewHolder
import no.kasperi.Listeners.ElementClickListener
import no.kasperi.Models.ForslagsElement
import no.kasperi.Models.LocalModel

class KategoriChildViewHolder(itemView : View, callback : ElementClickListener) : AbstractViewHolder(itemView,callback) {

    override fun presentData(data: LocalModel) {
        when(data){
            is ForslagsElement -> {
                itemView.kategori_bilde.setImageResource(data.kategoriBilde)
                itemView.kategori_txt.text = data.kategoriNavn
            }
        }
    }
}

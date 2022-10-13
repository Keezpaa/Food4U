package no.kasperi.Adapters

import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.holder_sok_resultat.view.*
import kotlinx.android.synthetic.main.holder_sok_resultat_kort.view.*
import no.kasperi.Abstraction.AbstractViewHolder
import no.kasperi.Listeners.ElementClickListener
import no.kasperi.Models.LocalModel
import no.kasperi.Models.OppskriftMain
import no.kasperi.Models.QueryModel
import no.kasperi.food4u.R

class SokViewHolder (itemView: View, callback: ElementClickListener) :
    AbstractViewHolder(itemView, callback) {

    override fun presentData(data: LocalModel) {
        when (data) {
            is OppskriftMain -> {
                Glide.with(itemView).load(data.oppskrift.bilde).placeholder(R.mipmap.food4u_app_logo).into(itemView.oppskrift_kort_element_bilde)
                itemView.oppskrift_kort_element_tittel.text = data.oppskrift.tittel
            }

            is QueryModel -> {
                itemView.sok_resultat_element_tittel.text = data.queryNavn
            }
        }
    }
}
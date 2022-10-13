package no.kasperi.Adapters

import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.holder_favoritt_element.view.*
import no.kasperi.Abstraction.AbstractViewHolder
import no.kasperi.Listeners.ElementClickListener
import no.kasperi.Models.LocalModel
import no.kasperi.Models.OppskriftMain
import no.kasperi.food4u.R

class FavorittViewHolder (itemView : View, callback : ElementClickListener) : AbstractViewHolder(itemView,callback){

    override fun presentData(data: LocalModel) {
        when(data){
            is OppskriftMain -> {
                itemView.oppskrift_element_tittel.text = data.oppskrift.tittel
                Glide.with(itemView).load(data.oppskrift.bilde).placeholder(R.mipmap.food4u_app_logo)
                    .into(itemView.oppskrift_element_bilde)
            }
        }
    }
}
package no.kasperi.Adapters

import android.view.View
import com.bumptech.glide.Glide
import no.kasperi.Abstraction.AbstractViewHolder
import no.kasperi.Models.IngrediensModel
import no.kasperi.Models.LocalModel

class OppskriftIngredienserViewHolder(itemView : View) : AbstractViewHolder(itemView) {

    override fun presentData(data: LocalModel) {
        when(data){
            is IngrediensModel -> {
                Glide.with(itemView).load(data.bilde).into(itemView.ingrediens_bilde)
                itemView.ingrediens_navn.text = data.text
            }
        }
    }
}
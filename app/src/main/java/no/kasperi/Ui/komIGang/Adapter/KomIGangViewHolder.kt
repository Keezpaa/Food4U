package no.kasperi.Ui.komIGang.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.holder_komigang_element.view.*
import no.kasperi.Models.KomIGangElement
import no.kasperi.Models.LocalModel

class KomIGangViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun present(item: LocalModel) {
        when (item) {
            is KomIGangElement -> {
                itemView.komigang_bilde.setImageResource(item.komigangBilde)
                itemView.komigang_tittel.text = item.tittel
                itemView.komigang_beskrivelse.text = item.beskrivelse
            }
        }
    }
}
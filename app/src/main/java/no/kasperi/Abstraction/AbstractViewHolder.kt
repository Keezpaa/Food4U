package no.kasperi.Abstraction

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import no.kasperi.Listeners.ItemClickListener
import no.kasperi.Models.LocalModel

abstract class AbstractViewHolder(itemView: View, callback: ItemClickListener? = null) :
    RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            callback?.onItemClick(it)
        }
    }

    fun bindData(data: LocalModel) {
        itemView.tag = data
        presentData(data)
    }

    abstract fun presentData(data: LocalModel)
}
package no.kasperi.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import no.kasperi.Abstraction.DiffUtilClass
import no.kasperi.Listeners.ElementClickListener
import no.kasperi.Models.LocalModel
import no.kasperi.Models.OppskriftMain
import no.kasperi.Models.QueryModel
import no.kasperi.Models.TomModel
import no.kasperi.food4u.R

class SokAdapter (private val callback: ElementClickListener) :
    ListAdapter<LocalModel, SokViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SokViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)
        return SokViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: SokViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is OppskriftMain -> R.layout.holder_sok_resultat_kort
        is QueryModel -> R.layout.holder_sok_resultat
        is TomModel -> R.layout.holder_tomt_query_element
        else -> R.layout.holder_tomt_element
    }
}
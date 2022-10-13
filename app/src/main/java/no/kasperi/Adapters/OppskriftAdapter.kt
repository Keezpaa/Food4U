package no.kasperi.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import no.kasperi.Abstraction.DiffUtilClass
import no.kasperi.Listeners.ElementClickListener
import no.kasperi.Models.LocalModel
import no.kasperi.Models.OppskriftMain
import no.kasperi.Models.ShimmerModel
import no.kasperi.food4u.R

class OppskriftAdapter (private val callback: ElementClickListener) :
    ListAdapter<LocalModel, OppskriftViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OppskriftViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return OppskriftViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: OppskriftViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when(getItem(position)){
        is OppskriftMain -> R.layout.holder_oppskrift_element_rad
        is ShimmerModel -> R.layout.holder_shimmer_oppskrift_element_rad
        else -> R.layout.holder_tomt_element
    }
}
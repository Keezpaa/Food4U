package no.kasperi.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import no.kasperi.Abstraction.DiffUtilClass
import no.kasperi.Listeners.ElementClickListener
import no.kasperi.Models.LocalModel
import no.kasperi.Models.OppskriftMain
import no.kasperi.Models.ShimmerModel
import no.kasperi.Models.TomModel
import no.kasperi.food4u.R

class FavorittAdapter(private val callback: ElementClickListener) :
    ListAdapter<LocalModel, FavorittViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavorittViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return FavorittViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: FavorittViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is OppskriftMain -> R.layout.holder_favoritt_element
        is ShimmerModel -> R.layout.holder_shimmer_favoritt_element
        is TomModel -> R.layout.holder_ingen_favoritt_element
        else -> R.layout.holder_tomt_element
    }
}
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

class HjemsideAdapter(private val callback: ElementClickListener) :
    ListAdapter<LocalModel, HjemsideViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HjemsideViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return HjemsideViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: HjemsideViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is OppskriftMain -> R.layout.holder_hjemside_karusell_element
        is ShimmerModel -> R.layout.holder_shimmer_hjemside_karusell_element
        else -> R.layout.holder_tomt_element
    }
    }
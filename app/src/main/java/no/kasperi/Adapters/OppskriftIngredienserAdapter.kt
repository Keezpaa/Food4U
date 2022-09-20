package no.kasperi.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import no.kasperi.Abstraction.DiffUtilClass
import no.kasperi.Models.LocalModel
import no.kasperi.food4u.R

class OppskriftIngredienserAdapter() :
    ListAdapter<LocalModel, OppskriftIngredienserViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OppskriftIngredienserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout., parent, false)
        return OppskriftIngredienserViewHolder(view)
    }

    override fun onBindViewHolder(holder: OppskriftIngredienserViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}
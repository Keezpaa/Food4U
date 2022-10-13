package no.kasperi.Ui.komIGang.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import no.kasperi.Abstraction.DiffUtilClass
import no.kasperi.Models.LocalModel
import no.kasperi.food4u.R

class KomIGangAdapter : ListAdapter<LocalModel, KomIGangViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KomIGangViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.holder_komigang_element, parent, false)
        return KomIGangViewHolder(view)
    }

    override fun onBindViewHolder(holder: KomIGangViewHolder, position: Int) =
        holder.present(getItem(position))
}
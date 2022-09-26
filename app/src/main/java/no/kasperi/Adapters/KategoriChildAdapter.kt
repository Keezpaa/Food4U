package no.kasperi.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import no.kasperi.Abstraction.DiffUtilClass
import no.kasperi.Listeners.ElementClickListener
import no.kasperi.Models.LocalModel
import no.kasperi.food4u.R

class KategoriChildAdapter(private val callback: ElementClickListener) :
    ListAdapter<LocalModel, KategoriChildViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KategoriChildViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.holder_kategori_barn_element_rad, parent, false)
        return KategoriChildViewHolder(view,callback)
    }

    override fun onBindViewHolder(holder: KategoriChildViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}
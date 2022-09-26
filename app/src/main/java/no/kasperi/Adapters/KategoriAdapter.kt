package no.kasperi.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import no.kasperi.Abstraction.DiffUtilClass
import no.kasperi.Listeners.ElementClickListener
import no.kasperi.Models.ForslagsElement
import no.kasperi.Models.ForslagsModel
import no.kasperi.Models.HjemKategoriElement
import no.kasperi.Models.LocalModel
import no.kasperi.food4u.R

class KategoriAdapter (private val callback: ElementClickListener) :
        ListAdapter<LocalModel, KategoriViewHolder>(DiffUtilClass<LocalModel>()) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KategoriViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
            return KategoriViewHolder(view, callback)
        }

        override fun onBindViewHolder(holder: KategoriViewHolder, position: Int) {
            holder.bindData(getItem(position))
        }

        override fun getItemViewType(position: Int): Int = when (getItem(position)) {
            is ForslagsElement -> R.layout.holder_kategori_element_rad
            is ForslagsModel -> R.layout.holder_kategori_forelder_element_rad
            is HjemKategoriElement -> R.layout.holder_kategori_element_rad
            else -> R.layout.holder_tomt_element
        }
    }
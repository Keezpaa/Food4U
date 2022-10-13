package no.kasperi.Ui.DashBoard.Filter

import android.content.Intent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_filter.*
import no.kasperi.Abstraction.AbstractFragment
import no.kasperi.Ui.Resultater.FilterResultatActivity
import no.kasperi.food4u.R

class FilterFragment : AbstractFragment(R.layout.fragment_filter) {
    private lateinit var viewModel: FilterViewModel

    override fun init(view: View) {
        viewModel = ViewModelProvider(this).get(FilterViewModel::class.java)
    }

    override fun running() {
        kalorier_slider.setValues(300.0f, 2700.0f)

       // val matTypeValg = resources.getStringArray(R.array.mat_type_valg)
    //    val matTypeAdapter =
   //         ArrayAdapter(requireContext(), R.layout.dropdown_element, matTypeValg)
    //    mat_type_autocomplete.setAdapter(matTypeAdapter)

    //    val diettTypeValg = resources.getStringArray(R.array.diett_type_valg)
   //     val diettTypeAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_element, diettTypeValg)
     //   diet_autocomplete.setAdapter(diettTypeAdapter)

        finn_oppskrift_btn.setOnClickListener {
            when (viewModel.checkIfValid(
                kalorier_slider.values[0].toString(),
                kalorier_slider.values[1].toString(),
                mat_type_autocomplete.text.toString(),
                diet_autocomplete.text.toString()
            )) {
                true -> {
                    startActivity(Intent(requireContext(), FilterResultatActivity::class.java).also {
                        it.putExtra("KCAL_MIN_VERDI", kalorier_slider.values[0].toDouble())
                        it.putExtra("KCAL_MAX_VERDI", kalorier_slider.values[1].toDouble())
                        it.putExtra("MAT_TYPE", mat_type_autocomplete.text.toString())
                        it.putExtra("DIETT_TYPE", diet_autocomplete.text.toString())
                    })
                }
                false -> Toast.makeText(context, resources.getText(R.string.fyll_alle_felt), Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun stop() {}
}
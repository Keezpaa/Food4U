package no.kasperi.Ui.Resultater

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import no.kasperi.Abstraction.AbstractActivity
import no.kasperi.food4u.R

class FilterResultatActivity : AbstractActivity(R.layout.activity_filter_resultat) {

    private lateinit var viewModel: FilterResultatViewModel
    private var kcalMinValue = 0.0
    private var kcalMaxValue = 0.0
    private lateinit var mealType: String
    private lateinit var dietType: String

    override fun init() {
        viewModel = ViewModelProvider(this).get(FilterResultsViewModel::class.java)
    }

    @SuppressLint("SetTextI18n")
    override fun running() {
        kcalMinValue = intent.getDoubleExtra("KCAL_MIN_VALUE", 0.0)
        kcalMaxValue = intent.getDoubleExtra("KCAL_MAX_VALUE", 0.0)
        mealType = intent.getStringExtra("MEAL_TYPE") ?: ""
        dietType = intent.getStringExtra("DIET_TYPE") ?: ""

        calories_min_max_subheader.text =
            "Calorie range : ${kcalMinValue.toInt()} up to ${kcalMaxValue.toInt()} kcal"
        meal_type_desc.text = "Meal Type : $mealType"
        diet_type_desc.text = "Diet Type : ${dietType.capitalize()}"

        results_screen_recycler.adapter = viewModel.adapter

        getData()

        swipe_refresh_layout.setOnRefreshListener {
            getData()
        }

        viewModel.observeData(this)

        viewModel.errorCase.observe(this, Observer {
            when(it){
                true -> {
                    Snackbar.make(findViewById(android.R.id.content), application.resources.getText(R.string.snackbar_no_results), Snackbar.LENGTH_LONG)
                        .setAction("Go back") {
                            onBackPressed()
                        }
                        .show()
                }

                false -> Unit
            }
        })
    }

    override fun stopped() {
        viewModel.removeObservers(this)
    }

    private fun getData() {
        viewModel.clearCounters()

        viewModel.getRecipeData(
            kcalMinValue.toInt(),
            kcalMaxValue.toInt(),
            mealType,
            dietType,
            object : RecipeClickListener {
                override fun onRecipeClick(recipe: RecipeMain) {
                    startActivity(
                        Intent(this@FilterResultsActivity, DetailsActivity::class.java)
                            .putExtra("RECIPE", recipe)
                    )
                }
            })

        swipe_refresh_layout.isRefreshing = false

        observeDataPaging()
    }

    fun observeDataPaging() {
        results_screen_recycler.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (!results_screen_recycler.canScrollVertically(1)) {
                viewModel.getRecipeData(
                    kcalMinValue.toInt(),
                    kcalMaxValue.toInt(),
                    mealType,
                    dietType,
                    object : RecipeClickListener {
                        override fun onRecipeClick(recipe: RecipeMain) {
                            startActivity(
                                Intent(this@FilterResultsActivity, DetailsActivity::class.java)
                                    .putExtra("RECIPE", recipe)
                            )
                        }
                    })
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
    }
}
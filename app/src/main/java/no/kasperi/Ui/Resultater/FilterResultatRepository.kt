package no.kasperi.Ui.Resultater

import androidx.lifecycle.MutableLiveData
import no.kasperi.APP_ID
import no.kasperi.APP_KEY
import no.kasperi.Models.OppskriftMain
import no.kasperi.Models.ResponsModel
import no.kasperi.Nettverk.ApiKlient
import no.kasperi.Utils.ApiSideHjelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilterResultatRepository {

    val data = MutableLiveData<List<OppskriftMain>>()
    val errorData = MutableLiveData<Boolean>()
    private var recipes: MutableList<OppskriftMain> = arrayListOf()
    private val apiSide = ApiSideHjelper()

    fun getDataFromApi(
        kcalMinVerdi: Int,
        kcalMaxVerdi: Int,
        matType: String,
        diettType: String
    ) {
        apiSide.incrementCounters()

        ApiKlient.getCustomRecipes(apiSide.from,apiSide.to,
            "",
            "${kcalMinVerdi}-${kcalMaxVerdi}",
            matType,
            diettType,
            APP_ID,
            APP_KEY
        ).enqueue(object : Callback<ResponsModel> {
            override fun onResponse(
                call: Call<ResponsModel>,
                response: Response<ResponsModel>
            ) {
                response.body()!!.hits.forEach {
                    recipes.add(it)
                }

                when(recipes.size){
                    0 -> errorData.value = true
                    else -> {
                        data.value = recipes
                        errorData.value = false
                    }
                }
            }

            override fun onFailure(call: Call<ResponsModel>, t: Throwable) {
                errorData.value = true
            }
        })
    }

    fun clearCounters() {
        apiSide.clearCounters()
    }
}
package no.kasperi.Ui.Kategorier

import android.provider.UserDictionary.Words.APP_ID
import androidx.lifecycle.MutableLiveData
import no.kasperi.APP_KEY
import no.kasperi.Models.OppskriftMain
import no.kasperi.Models.ResponsModel
import no.kasperi.Nettverk.ApiKlient
import no.kasperi.Utils.ApiSideHjelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KategoriResultatRepository {

    val data = MutableLiveData<List<OppskriftMain>?>()
    private var oppskrifter: MutableList<OppskriftMain> = arrayListOf()
    private val apiSide = ApiSideHjelper()

    fun getCuisineTypeResults(cuisineType: String) {
        apiSide.incrementCounters()

        ApiKlient.getCuisineTypeResults(
            apiSide.from,
            apiSide.to,
            "",
            cuisineType,
            APP_ID,
            APP_KEY
        ).enqueue(object : Callback<ResponsModel> {
            override fun onResponse(call: Call<ResponsModel>, response: Response<ResponsModel>) {
                response.body()!!.hits.forEach {
                    oppskrifter.add(it)
                }

                data.value = oppskrifter
            }

            override fun onFailure(call: Call<ResponsModel>, t: Throwable) {
                data.value = null
            }
        })
    }

    fun getMealTypeResults(mealType: String) {
        apiSide.incrementCounters()

        ApiKlient.getMealTypeResults(apiSide.from, apiSide.to, "", mealType, APP_ID, APP_KEY)
            .enqueue(object : Callback<ResponsModel> {
                override fun onResponse(
                    call: Call<ResponsModel>,
                    response: Response<ResponsModel>
                ) {
                    response.body()!!.hits.forEach {
                        oppskrifter.add(it)
                    }

                    data.value = oppskrifter
                }

                override fun onFailure(call: Call<ResponsModel>, t: Throwable) {
                    data.value = null
                }
            })
    }

    fun getDishTypeResults(dishType: String) {
        apiSide.incrementCounters()

        ApiKlient.getDishTypeResults(apiSide.from, apiSide.to, "", dishType, APP_ID, APP_KEY)
            .enqueue(object : Callback<ResponsModel> {
                override fun onResponse(
                    call: Call<ResponsModel>,
                    response: Response<ResponsModel>
                ) {
                    response.body()!!.hits.forEach {
                        oppskrifter.add(it)
                    }

                    data.value = oppskrifter
                }

                override fun onFailure(call: Call<ResponsModel>, t: Throwable) {
                    data.value = null
                }
            })
    }

    fun clearCounters() {
        apiSide.clearCounters()
    }
}

package no.kasperi.Ui.DashBoard.Søk

import android.provider.UserDictionary.Words.APP_ID
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import no.kasperi.APP_KEY
import no.kasperi.Models.OppskriftMain
import no.kasperi.Models.QueryModel
import no.kasperi.Models.ResponsModel
import no.kasperi.Nettverk.ApiKlient
import no.kasperi.Utils.ApiSideHjelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SokRepository {

    private val databaseReference by lazy { FirebaseDatabase.getInstance().reference }
    private var oppskrifter: MutableList<OppskriftMain> = arrayListOf()
    val data = MutableLiveData<List<OppskriftMain>?>()
    val tommeQueries = MutableLiveData<Boolean>()
    val nyligQueriesListe = mutableListOf<QueryModel>()
    val nyligQueries = MutableLiveData<List<QueryModel>>()
    private val apiSide = ApiSideHjelper()

    fun getDataFromApi(query: String) {
        apiSide.incrementCounters()

        ApiKlient.getRecipesByPage(apiSide.from, apiSide.to, query, APP_ID, APP_KEY)
            .enqueue(object : Callback<ResponsModel> {
                override fun onResponse(
                    call: Call<ResponsModel>,
                    response: Response<ResponsModel>
                ) {
                    response.body()!!.hits.forEach {
                        oppskrifter.add(it)
                    }

                    data.value = oppskrifter
                    tommeQueries.value = false
                }

                override fun onFailure(call: Call<ResponsModel>, t: Throwable) {
                    data.value = null
                }
            })
    }

    fun addQueryToDb(query: QueryModel) {
        databaseReference.child("users")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .child("userSearchQueries")
            .child(query.queryNavn).setValue(query)
    }

    fun getRecentUserQueries() {
        databaseReference.child("users")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .child("userSearchQueries")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    //
                }

                override fun onDataChange(p0: DataSnapshot) {
                    when (p0.exists()) {
                        true -> {
                            nyligQueriesListe.clear()
                            p0.children.forEach {
                                val query = it.getValue(QueryModel::class.java)
                                nyligQueriesListe.add(query!!)
                            }

                            nyligQueries.value = nyligQueriesListe
                        }

                        false -> tommeQueries.value = true
                    }
                }
            })
    }

    fun clearCounters() {
        apiSide.clearCounters()
    }
}
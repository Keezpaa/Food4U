package no.kasperi.Ui.DashBoard.Main

import android.provider.UserDictionary.Words.APP_ID
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import no.kasperi.APP_KEY
import no.kasperi.Models.HjemKategoriElement
import no.kasperi.Models.ResponsModel
import no.kasperi.Nettverk.ApiKlient
import no.kasperi.food4u.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragmentRepository {

    val databaseReference by lazy { FirebaseDatabase.getInstance().reference }
    val oppskrifter = MutableLiveData<ResponsModel?>()
    val brukernavn = MutableLiveData<String?>()
    val hjemKategorier = MutableLiveData<List<HjemKategoriElement>>()

    fun getUsername() {
        databaseReference.child("brukere")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .child("brukerData")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        brukernavn.value = snapshot.value.toString()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    brukernavn.value = null
                }
            })
    }

    fun getMatTypeResultat(matType: String) {
        ApiKlient.getMatTypeResultat(0,10,"", matType, APP_ID, APP_KEY).enqueue(object :
            Callback<ResponsModel> {
            override fun onResponse(call: Call<ResponsModel>, response: Response<ResponsModel>) {
                oppskrifter.value = response.body()
            }

            override fun onFailure(call: Call<ResponsModel>, t: Throwable) {
                oppskrifter.value = null
            }
        })
    }

    fun getHjemKategorier(){
        hjemKategorier.value = listOf(
            HjemKategoriElement("Frokost", R.drawable.ikon_frokost),
            HjemKategoriElement("Lunsj", R.drawable.ikon_lunsj),
            HjemKategoriElement("Suppe", R.drawable.ikon_suppe),
            HjemKategoriElement("Salat", R.drawable.ikon_salat),
            HjemKategoriElement("Dessert", R.drawable.ikon_dessert),
            HjemKategoriElement("Indisk", R.drawable.ikon_indisk),
            HjemKategoriElement("Kinesisk", R.drawable.ikon_kinesisk),
            HjemKategoriElement("Italiensk", R.drawable.ikon_italiensk)
        )
    }
}
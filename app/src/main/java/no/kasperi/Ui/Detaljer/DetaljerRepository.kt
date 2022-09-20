package no.kasperi.Ui.Detaljer

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import no.kasperi.Models.OppskriftMain

class DetaljerRepository {

    private var databaseReference : DatabaseReference = FirebaseDatabase.getInstance().reference
    val erFavorittOppskrift = MutableLiveData<Boolean?>()

    fun erFavorittOppskrift(recipe: OppskriftMain) {
        databaseReference.child("users")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .child("favorittOppskriftListe")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    erFavorittOppskrift.value = null
                }

                override fun onDataChange(p0: DataSnapshot) {
                    if (p0.exists()) {
                        p0.children.forEach {
                            val fav = it.getValue(OppskriftMain::class.java)
                            if (fav?.oppskrift?.tittel == recipe.oppskrift.tittel) {
                                erFavorittOppskrift.value = true
                            }
                            Log.d("it", it.toString())
                        }
                    }
                }
            })
    }

    fun leggTilSomFavoritt(oppskrift: OppskriftMain) {
        databaseReference.child("users")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .child("favorittOppskriftListe")
            .child(oppskrift.oppskrift.tittel).setValue(oppskrift)
        erFavorittOppskrift.value = true
    }

    fun fjernOppskriftFraFavoritt(oppskrift: OppskriftMain) {
        databaseReference.child("users")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .child("favorittOppskriftListe")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    //
                }

                override fun onDataChange(p0: DataSnapshot) {
                    if (p0.exists()) {
                        p0.children.forEach {
                            val favoritt = it.getValue(OppskriftMain::class.java)
                            if (favoritt?.oppskrift?.tittel == oppskrift.oppskrift.tittel) {
                                it.ref.removeValue()
                                erFavorittOppskrift.value = false
                            }
                        }
                    }
                }
            })
    }
}
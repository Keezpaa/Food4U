package no.kasperi.Ui.DashBoard.Profil

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import no.kasperi.Models.OppskriftMain
import java.io.ByteArrayOutputStream

class ProfilRepository {

    private val databaseReference: DatabaseReference by lazy { FirebaseDatabase.getInstance().reference }
    val data = MutableLiveData<List<OppskriftMain>?>()
    val emptyFavorites = MutableLiveData<Boolean>()
    private val favoriteRecipeList = mutableListOf<OppskriftMain>()
    val userEmail = MutableLiveData<String>()
    val userImageLink = MutableLiveData<String?>()
    val username = MutableLiveData<String?>()

    suspend fun getUserFavorites() {
        databaseReference.child("users")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .child("favoriteRecipeList")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    data.postValue(null)
                }

                override fun onDataChange(p0: DataSnapshot) {
                    favoriteRecipeList.clear()
                    if (p0.exists()) {
                        p0.children.forEach {
                            val fav = it.getValue(OppskriftMain::class.java)
                            favoriteRecipeList.add(fav!!)
                            Log.d("it", it.toString())
                        }
                        data.postValue(favoriteRecipeList)
                        emptyFavorites.postValue(false)
                    } else {
                        emptyFavorites.postValue(true)
                    }
                }
            })
    }

    suspend fun getUserProfileData() {
        userEmail.postValue(FirebaseAuth.getInstance().currentUser?.email.toString())

        databaseReference.child("users")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .child("userData")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    if (p0.exists()) {
                        username.postValue(p0.value.toString())
                    }
                }

                override fun onCancelled(p0: DatabaseError) {
                    username.postValue(null)
                }
            })
    }

    fun savePhotoToDb(string: String) {
        databaseReference.child("users")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .child("userPhoto").setValue(string)
        getUserPhoto()
    }

    fun getUserPhoto() {
        databaseReference.child("users")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .child("userPhoto")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    if (p0.exists()) {
                        val imgUrl = p0.value.toString()
                        Log.d("URL", imgUrl)
                        userImageLink.postValue(imgUrl)
                    }
                }

                override fun onCancelled(p0: DatabaseError) {
                    userImageLink.postValue(null)
                }

            })
    }

    fun saveCameraPhotoToDb(bitmap: Bitmap) {
        val baos = ByteArrayOutputStream()
        val storageRef = FirebaseStorage.getInstance()
            .reference.child("pics/${FirebaseAuth.getInstance().currentUser?.uid}")
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val image = baos.toByteArray()
        val upload = storageRef.putBytes(image)
        upload.addOnSuccessListener {
            val result = it.metadata!!.reference!!.downloadUrl
            result.addOnSuccessListener {
                val imageLink = it.toString()
                Log.d("MSG", imageLink)
                savePhotoToDb(imageLink)
            }
        }
    }

    fun saveGalleryPhotoToDb(uri: Uri) {
        val storageRef = FirebaseStorage.getInstance()
            .reference.child("pics/${FirebaseAuth.getInstance().currentUser?.uid}")
        val upload = storageRef.putFile(uri)
        upload.addOnSuccessListener {
            val result = it.metadata!!.reference!!.downloadUrl
            result.addOnSuccessListener {
                val imageLink = it.toString()
                Log.d("MSG", imageLink)
                savePhotoToDb(imageLink)
            }
        }
    }
}
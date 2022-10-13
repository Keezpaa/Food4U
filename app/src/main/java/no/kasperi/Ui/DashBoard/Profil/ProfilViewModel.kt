package no.kasperi.Ui.DashBoard.Profil

import android.graphics.Bitmap
import android.net.Uri
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import no.kasperi.Adapters.FavorittAdapter
import no.kasperi.Listeners.ElementClickListener
import no.kasperi.Listeners.OppskriftClickListener
import no.kasperi.Models.OppskriftMain
import no.kasperi.Models.ShimmerModel
import no.kasperi.Models.TomModel

class ProfilViewModel : ViewModel(), ElementClickListener {

    private val repo = ProfilRepository()
    val data = repo.data
    val emptyFavorites = repo.emptyFavorites
    val userEmail = repo.userEmail
    val userImageLink = repo.userImageLink
    val username = repo.username
    val adapter = FavorittAdapter(this)
    private lateinit var callback: OppskriftClickListener

    init {
        adapter.submitList(listOf(ShimmerModel(), ShimmerModel(), ShimmerModel()))
    }

    fun getFavoriteData(callback: OppskriftClickListener) {
        this.callback = callback

        CoroutineScope(Dispatchers.IO).launch { repo.getUserFavorites() }
    }

    override fun onItemClick(view: View) {
        when (view.tag) {
            is OppskriftMain -> callback.onOppskriftClick(view.tag as OppskriftMain)
        }
    }

    fun observe(owner: LifecycleOwner) {
        data.observe(owner, Observer {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })

        emptyFavorites.observe(owner, Observer {
            when (it) {
                true -> {
                    adapter.submitList(listOf(TomModel()))
                    adapter.notifyDataSetChanged()
                }
                false -> Unit
            }
        })
    }

    fun removeObservers(owner: LifecycleOwner) {
        data.removeObservers(owner)
        emptyFavorites.removeObservers(owner)
    }

    fun saveCameraPhotoToDb(imgBitmap: Bitmap) {
        repo.saveCameraPhotoToDb(imgBitmap)
    }

    fun saveGalleryPhotoToDb(imageUri: Uri) {
        repo.saveGalleryPhotoToDb(imageUri)
    }

    fun getUserPhoto() {
        repo.getUserPhoto()
    }

    fun getUserProfileData() {
        CoroutineScope(Dispatchers.IO).launch {
            repo.getUserProfileData()
        }
    }
}

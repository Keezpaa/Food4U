package no.kasperi.Ui.DashBoard.Profil

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_profil.*
import kotlinx.android.synthetic.main.last_opp_profilbilde_valg.view.*
import no.kasperi.Abstraction.AbstractFragment
import no.kasperi.Listeners.OppskriftClickListener
import no.kasperi.Models.OppskriftMain
import no.kasperi.Ui.Detaljer.DetaljerActivity
import no.kasperi.Ui.redigerProfil.RedigerProfilActivity
import no.kasperi.food4u.R


class ProfilFragment: AbstractFragment(R.layout.fragment_profil) {

    private lateinit var viewModel: ProfilViewModel
    private val REQUEST_IMAGE_CAPTURE = 100
    private val IMAGE_PICK_CODE = 200
    private val PERMISSION_CODE = 201

    override fun init(view: View) {
        viewModel = ViewModelProvider(this).get(ProfilViewModel::class.java)
    }

    override fun running() {
        viewModel.getUserPhoto()
        viewModel.getUserProfileData()

        profil_email.text = getString(R.string.profil_dummy_email)

        profil_bilde.setOnClickListener {
            showUploadPhotoOptions()
        }

        rediger_profil_btn.setOnClickListener {
            startActivity(Intent(requireContext(), RedigerProfilActivity::class.java))
        }

        profil_favoritter_recycler.adapter = viewModel.adapter

        viewModel.getFavoriteData(object : OppskriftClickListener {
            override fun onOppskriftClick(oppskrift: OppskriftMain) {
                startActivity(
                    Intent(requireContext(), DetaljerActivity::class.java)
                        .putExtra("RECIPE", oppskrift)
                )
            }
        })

        viewModel.observe(this)
        viewModel.userEmail.observe(this, Observer {
            profil_email.text = it
        })

        viewModel.userImageLink.observe(this, Observer {
            Glide.with(this).load(it).into(profil_bilde)
        })

        viewModel.username.observe(this, Observer {
            profil_brukernavn.text = it
        })
    }

    override fun stop() {
        viewModel.removeObservers(this)
        viewModel.userEmail.removeObservers(this)
        viewModel.userImageLink.removeObservers(this)
        viewModel.username.removeObservers(this)
    }

    private fun showUploadPhotoOptions() {
        val view = layoutInflater.inflate(R.layout.last_opp_profilbilde_valg, null)
        val dialog = BottomSheetDialog(view.context)
        dialog.setContentView(view)
        dialog.show()
        view.lastOppFraKamera.setOnClickListener {
            takePictureIntent()
            dialog.dismiss()
        }
        view.lastOppFraGalleri.setOnClickListener {
            if (activity?.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                requestPermissions(permissions, PERMISSION_CODE)
            } else {
                uploadFromGallery()
            }
            dialog.dismiss()
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun takePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { pictureIntent ->
            pictureIntent.resolveActivity(activity?.packageManager!!)?.also {
                startActivityForResult(pictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    private fun uploadFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == AppCompatActivity.RESULT_OK) {
            val imgBitmap = data?.extras?.get("data") as Bitmap
            viewModel.saveCameraPhotoToDb(imgBitmap)

        } else if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            // I have to save the url to the db
            val imageUri = data?.data
            if (imageUri != null) {
                viewModel.saveGalleryPhotoToDb(imageUri)
                viewModel.getUserPhoto()
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    //permission from popup granted
                    uploadFromGallery()
                } else {
                    //permission from popup denied
                    Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
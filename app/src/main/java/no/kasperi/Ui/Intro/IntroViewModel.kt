package no.kasperi.Ui.Intro

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class IntroViewModel : ViewModel() {

    var auth: FirebaseAuth = FirebaseAuth.getInstance()
}
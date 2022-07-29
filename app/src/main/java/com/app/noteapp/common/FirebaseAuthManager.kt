package com.app.noteapp.common

import android.app.Activity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

object FirebaseAuthManager {

    fun getClient(activity: Activity): GoogleSignInClient {
        return GoogleSignIn.getClient(activity, getOptions())
    }

    fun firebaseAuthWithGoogle(idToken: String) {
        val firebaseAuth = FirebaseAuth.getInstance()
        val credentials = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credentials)
    }

    private fun getOptions() = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("154565551251-lkt8msgcd0uu6erp97usabq3knfslr5u.apps.googleusercontent.com")
        .requestEmail()
        .build()

}
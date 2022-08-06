package com.app.noteapp.common

import android.content.Intent
import android.util.Log
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks

object FirebaseLinkHandler {

    private val firebaseDynamicLinks = FirebaseDynamicLinks.getInstance()

    fun handle(intent: Intent?, action: () -> Unit = {}) {
        firebaseDynamicLinks
            .getDynamicLink(intent)
            .addOnSuccessListener { data ->
                Log.d("FirebaseLinkHandler", "Link received")
                data?.let {
                    val dataLink = data.link
                    dataLink?.let { link ->
                        Log.d("FirebaseLinkHandler", "Link: $link")
                        val number = link.getQueryParameter("number")
                        Log.d("FirebaseLinkHandler", "number: $number")
                    }
                }
                action()
            }
            .addOnFailureListener {
                Log.d("FirebaseLinkHandler", "Link failed")
            }
    }

}
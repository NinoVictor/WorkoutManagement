package com.uv.workoutmanagement

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.squareup.picasso.Picasso
import com.uv.workoutmanagement.ui.login.LoginActivity

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var textName = findViewById<TextView>(R.id.textNameProfile)
        var textEmail = findViewById<TextView>(R.id.textEmailProfile)
        var textId = findViewById<TextView>(R.id.textIdProfile)
        var imageView = findViewById<ImageView>(R.id.imageProfile)
        var btnSignOut = findViewById<Button>(R.id.buttonSingOut)


        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        val googleApiClient = GoogleSignIn.getClient(this, gso)
        val acct = GoogleSignIn.getLastSignedInAccount(this)

        if(acct != null){
            var personName = acct.displayName
            var personEmail = acct.email
            var personPhoto = acct.photoUrl
            var personId = acct.id

            //Toast.makeText(this, personEmail, Toast.LENGTH_LONG).show()
            textName.text = personName
            textEmail.text = personEmail
            textId.text = personId
            Glide.with(this).load(personPhoto).into(imageView)

            btnSignOut.setOnClickListener {
                googleApiClient.signOut().addOnCompleteListener {
                    Toast.makeText(this, "Successfully signed out", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, LoginActivity::class.java).apply {

                    }
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}

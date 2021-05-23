package com.example.memeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    var currentImageUrl : String? = null
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadMeme()
    }
    private  fun loadMeme() {
        // Instantiate the RequestQueue.

        val url ="https://i.redd.it/f7ibqp1dmiv51.gif"

        // Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url,null,
            Response.Listener { response ->
                // Display the first 500 characters of the response string.
                currentImageUrl = response.getString("url")
                Glide.with(this).load(currentImageUrl).into(findViewById(R.id.memeImageView))
            },
            Response.ErrorListener {
                Toast.makeText(this,"something went wrong",Toast.LENGTH_LONG).show()
            })

// Add the request to the RequestQueue.
      // MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }
        fun shareMeme(view: View) {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type="text/plain"
            //intent.putExtra(Intent.EXTRA_TEXT,"Hey , checkout this cool meme $currentImageUrl")
            val chooser = Intent.createChooser(intent,"Share this meme using ....")
            startActivity(chooser)
        }
        fun nextMeme(view: View) {
            loadMeme()
        }

}
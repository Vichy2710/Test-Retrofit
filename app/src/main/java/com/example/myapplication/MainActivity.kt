package com.example.myapplication

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.api.RetrofitHelper
import com.example.myapplication.api.TourApi
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.Image
import com.example.myapplication.model.Tour
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // create an implemetation of the API endpoints defined by the service interface
        val tourApi = RetrofitHelper.getInstance().create(TourApi::class.java)
        val imageApi = RetrofitHelper.getImage().create(TourApi::class.java)

        binding.autofill.setOnClickListener {
            // lauching new coroutine
            CoroutineScope(Dispatchers.IO).launch {
                val result = tourApi.getTours()
                val resultImages = imageApi.getImages()

                val tour: Tour? = result.body()
                val image: MutableList<Image>? = resultImages.body()
                if (tour != null) {
                    setTextForField(tour)
                }
                if (image != null) {
                    setImage(image)
                }
            }
        }

        binding.reset.setOnClickListener {
            binding.email.text.clear()
            binding.location.text.clear()
            binding.firstName.text.clear()
        }
    }

    private fun setTextForField(res: Tour) {
        val i = res.data?.size?.let { Random.nextInt(it) }
        binding.email.setText(i?.let { res.data[it].tourist_email })
        binding.location.setText(i?.let { res.data[it].tourist_location })
        binding.firstName.setText(i?.let { res.data[it].tourist_name })
    }

    private fun setImage(img: MutableList<Image>) {
        val i = img.size.let { Random.nextInt(it) }
        val uiHandler = Handler(Looper.getMainLooper())
        uiHandler.post {
            Picasso.get().load(img[i].url).into(binding.avatar)
        }
    }
}
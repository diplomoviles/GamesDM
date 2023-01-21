package com.amaurypm.gamesdm.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.amaurypm.gamesdm.databinding.ActivityDetailsBinding
import com.amaurypm.gamesdm.model.GameDetail
import com.amaurypm.gamesdm.network.GamesApi
import com.amaurypm.gamesdm.network.RetrofitService
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Thread.sleep

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras

        val id = bundle?.getString("id", "0")

        CoroutineScope(Dispatchers.IO).launch {

            val call = RetrofitService.getRetrofit().create(GamesApi::class.java).getGameDetail(id)

            call.enqueue(object : Callback<GameDetail> {
                override fun onResponse(call: Call<GameDetail>, response: Response<GameDetail>) {

                    //Toast.makeText(this@DetailsActivity, "OK", Toast.LENGTH_SHORT).show()

                    binding.apply {

                        tvTitle.text = response.body()?.title

                        tvLongDesc.text = response.body()?.longDesc

                        Glide.with(this@DetailsActivity)
                            .load(response.body()?.image)
                            .into(ivImage)

                        pbConexion.visibility = View.INVISIBLE

                    }




                }

                override fun onFailure(call: Call<GameDetail>, t: Throwable) {
                    Toast.makeText(this@DetailsActivity, "Error: ${t.message}", Toast.LENGTH_SHORT)
                        .show()
                    binding.pbConexion.visibility = View.INVISIBLE
                }

            })
        }
    }
}
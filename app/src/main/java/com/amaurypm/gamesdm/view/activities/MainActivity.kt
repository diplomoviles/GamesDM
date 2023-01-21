package com.amaurypm.gamesdm.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.amaurypm.gamesdm.databinding.ActivityMainBinding
import com.amaurypm.gamesdm.model.Game
import com.amaurypm.gamesdm.network.GamesApi
import com.amaurypm.gamesdm.network.RetrofitService
import com.amaurypm.gamesdm.util.Constants
import com.amaurypm.gamesdm.view.adapters.GamesAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).launch {

            Log.d(Constants.LOGTAG, "Hilo al iniciar la corrutina: ${Thread.currentThread().name}")

            val call =
                RetrofitService.getRetrofit().create(GamesApi::class.java)
                    .getGames("cm/games/games_list.php") //En el servidor serverbpw y en localhost
            //Constants.getRetrofit().create(GamesApi::class.java).getGames("games/games_list")  //Con Apiary

            call.enqueue(object : Callback<ArrayList<Game>> {
                override fun onResponse(
                    call: Call<ArrayList<Game>>,
                    response: Response<ArrayList<Game>>
                ) {
                    Log.d(Constants.LOGTAG, "Respuesta del servidor: ${response.toString()}")
                    Log.d(Constants.LOGTAG, "Datos: ${response.body().toString()}")

                    /*val gameTmp: Game

                    for (gameTmp in response.body()!!) {
                        Toast.makeText(
                            this@MainActivity,
                            "Juego: ${gameTmp.title}",
                            Toast.LENGTH_LONG
                        ).show()
                    }*/

                    Log.d(Constants.LOGTAG, "Hilo en el onResponse: ${Thread.currentThread().name}")

                    binding.pbConexion.visibility = View.GONE

                    binding.rvMenu.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.rvMenu.adapter = GamesAdapter(this@MainActivity, response.body()!!)

                }

                override fun onFailure(call: Call<ArrayList<Game>>, t: Throwable) {
                    Toast.makeText(
                        this@MainActivity,
                        "No hay conexión. Error: ${t.message}",
                        Toast.LENGTH_LONG
                    ).show()
                    binding.pbConexion.visibility = View.GONE
                }
            })

        }

    }

    fun selectedGame(game: Game) {
        val parametros = Bundle().apply {
            putString("id", game.id)
        }

        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtras(parametros)
        }

        startActivity(intent)
    }

}
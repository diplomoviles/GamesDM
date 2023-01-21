package com.amaurypm.gamesdm.network

import com.amaurypm.gamesdm.model.Game
import com.amaurypm.gamesdm.model.GameDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Creado por Amaury Perea Matsumura el 14/01/23
 */
interface GamesApi {

    @GET
    fun getGames(
        @Url url: String?
    ): Call<ArrayList<Game>>


    @GET("cm/games/game_detail.php")
    fun getGameDetail(
        @Query("id") id: String?
    ): Call<GameDetail>

    //games/game_detail/98746/amaury
    @GET("games/game_detail/{id}")
    fun getGameDetailApiary(
        @Path("id") id: String?
    ): Call<GameDetail>

}
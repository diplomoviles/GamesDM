package com.amaurypm.gamesdm.model

import com.google.gson.annotations.SerializedName

/**
 * Creado por Amaury Perea Matsumura el 14/01/23
 */
data class GameDetail(

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("image")
    var image: String? = null,

    @SerializedName("long_desc")
    var longDesc: String? = null

)

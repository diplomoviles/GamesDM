package com.amaurypm.gamesdm.model

import com.google.gson.annotations.SerializedName

/**
 * Creado por Amaury Perea Matsumura el 14/01/23
 */
data class Game(
    @SerializedName("id")
    var id: String? = null,

    @SerializedName("thumbnail")
    var thumbnail: String? = null,

    @SerializedName("title")
    var title: String? = null
)

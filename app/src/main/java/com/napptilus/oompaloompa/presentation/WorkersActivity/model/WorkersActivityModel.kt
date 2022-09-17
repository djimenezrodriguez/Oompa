package com.napptilus.oompaloompa.presentation.WorkersActivity.model

import com.google.gson.annotations.SerializedName

data class WorkersActivityModel(
    @SerializedName("id") val id:String,
    @SerializedName("firstName") val firstName:String,
    @SerializedName("last_name") val last_name: String,
    @SerializedName("favourite") val favourite_thinks: FavouriteItems,
    @SerializedName("gender") val gender:String,
    @SerializedName("image") val image:String,
    @SerializedName("profession") val profession:String,
    @SerializedName("email") val email:String,
    @SerializedName("age") val age:Int,
    @SerializedName("country") val country:String,
    @SerializedName("height") val height:String
)
data class FavouriteItems(
    @SerializedName("color") val color:String,
    @SerializedName("food") val food:String,
    @SerializedName("song") val song: String
)
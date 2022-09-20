package com.napptilus.oompaloompa.Utils;

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.napptilus.oompaloompa.presentation.workersListFragment.model.FavouriteItems
import java.lang.reflect.Type

object ConverterFavourite {
    @TypeConverter
    fun fromString(favouriteItem: String?): FavouriteItems {
        val listType: Type = object : TypeToken<FavouriteItems>() {}.getType()
        return Gson().fromJson(favouriteItem, listType)
    }

    @TypeConverter
    fun fromArrayList(favouriteItem: FavouriteItems): String {
        val gson = Gson()
        return gson.toJson(favouriteItem)
    }
}
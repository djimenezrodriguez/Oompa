package com.napptilus.oompaloompa.presentation.workersListFragment.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.napptilus.oompaloompa.database.model.WorkersRoomModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Entity
data class WorkersNetworkModel(
    @PrimaryKey @Json(name = "current") val current: String? = "UNKNOWN",
    @Json(name = "total") val total: Int,
    @Json(name = "results") val results: MutableList<WorkersNetworkModelInfo>,

    )

@JsonClass(generateAdapter = true)
@Entity
@Parcelize
data class WorkersNetworkModelInfo(
    @PrimaryKey @Json(name = "id") var id: String? = null,
    @Json(name = "first_name") val firstName: String? = "UNKNOWN",
    @Json(name = "last_name") val last_name: String? = "UNKNOWN",
    @Json(name = "favorite") val favourite_thinks: FavouriteItems? = null,
    @Json(name = "gender") val gender: String? = "UNKNOWN",
    @Json(name = "image") val image: String? = "UNKNOWN",
    @Json(name = "profession") val profession: String? = "UNKNOWN",
    @Json(name = "email") val email: String? = "UNKNOWN",
    @Json(name = "age") val age: String? = "UNKNOWN",
    @Json(name = "description") val description: String? = "UNKNOWN",
    @Json(name = "country") val country: String? = "UNKNOWN",
    @Json(name = "height") val height: String? = "UNKNOWN"
) : Parcelable

@Parcelize
data class FavouriteItems(
    @Json(name = "color") val color: String? = "UNKNOWN",
    @Json(name = "food") val food: String? = "UNKNOWN",
    @Json(name = "song") val song: String? = "UNKNOWN"
) : Parcelable

fun List<WorkersNetworkModelInfo>.asDatabaseModel(): List<WorkersRoomModel> {
    return map {
        it.asSingleDatabaseModel()
    }
}

fun WorkersNetworkModelInfo.asSingleDatabaseModel(): WorkersRoomModel {
    return WorkersRoomModel(
        id = id!!,
        firstName = firstName ?: "UNKNOWN",
        last_name = last_name ?: "UNKNOWN",
        favourite_thinks = favourite_thinks!!,
        gender = gender ?: "UNKNOWN",
        image = image ?: "UNKNOWN",
        profession = profession ?: "UNKNOWN",
        email = email ?: "UNKNOWN",
        description = description ?: "UNKNOWN",
        age = age ?: "UNKNOWN",
        country = country ?: "UNKNOWN",
        height = height ?: "UNKNOWN"
    )

}
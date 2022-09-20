package com.napptilus.oompaloompa.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.napptilus.oompaloompa.presentation.workersListFragment.model.FavouriteItems
import com.napptilus.oompaloompa.presentation.workersListFragment.model.WorkersNetworkModelInfo

@Entity
data class WorkersRoomModel(

    @PrimaryKey var id: String,
    val firstName: String,
    val last_name: String,
    val favourite_thinks: FavouriteItems,
    val gender: String,
    val image: String,
    val profession: String,
    val email: String,
    val age: String,
    val country: String,
    val description: String,
    val height: String
)

fun List<WorkersRoomModel>.asDomainModel(): List<WorkersNetworkModelInfo> {
    return map {
        it.asDomainSingleModel()
    }
}

fun WorkersRoomModel.asDomainSingleModel(): WorkersNetworkModelInfo {
    return WorkersNetworkModelInfo(
        id = id,
        firstName = firstName,
        last_name = last_name,
        favourite_thinks = favourite_thinks,
        gender = gender,
        image = image,
        profession = profession,
        description = description,
        email = email,
        age = age,
        country = country,
        height = height
    )
}
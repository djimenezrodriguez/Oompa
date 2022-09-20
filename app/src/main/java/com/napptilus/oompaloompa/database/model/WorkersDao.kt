package com.napptilus.oompaloompa.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.napptilus.oompaloompa.Utils.ConverterFavourite
import com.napptilus.oompaloompa.database.model.WorkersRoomModel

@Dao
interface WorkersOompaDao {
    // user List
    @Query("select * from WorkersRoomModel")
    fun getDatabaseWorkers(): LiveData<List<WorkersRoomModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllWorkers(users: List<WorkersRoomModel>)

    // single user
    @Query("select * from WorkersRoomModel WHERE id LIKE :id")
    fun getWorkerDetails(id: String): LiveData<WorkersRoomModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkerDetails(databaseUserDetails: WorkersRoomModel)

}

@Database(entities = [WorkersRoomModel::class], version = 1, exportSchema = false)
@TypeConverters(ConverterFavourite::class)
abstract class WorkersDatabase : RoomDatabase() {
    abstract val workerDao: WorkersOompaDao
}
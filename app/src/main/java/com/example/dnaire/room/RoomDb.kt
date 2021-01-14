package com.example.dnaire.room


import android.content.Context
import android.util.Log
import androidx.room.*
import com.example.dnaire.classes.OwnTrait
import com.example.dnaire.classes.Strain
import com.example.dnaire.classes.Trait

@Database(
    entities = [
        NewRoom::class,
        Trait::class,
        OwnTrait::class,
        Strain::class,
    ],
    version = 12,
    exportSchema = false
)
abstract class RoomDb : RoomDatabase() {
    // daos
    abstract val daoTrait : DaoTrait
    abstract val daoOwnTrait : DaoOwnTrait
    abstract val daoStrain : DaoStrain
    abstract val daoNew: DaoNew

    companion object {
        @Volatile
        private var INSTANCE: RoomDb? = null

        fun getInstance(context: Context): RoomDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RoomDb::class.java,
                        "a_db_of_ice_and_fire")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                Log.i("route", "UserDatabase getInstance called, the INSTANCE is $INSTANCE")
                return instance
            }
        }
    }
}

@Entity
class NewRoom(
    @PrimaryKey
    @ColumnInfo
    val name: String = "new",
    @ColumnInfo var traitsUploaded : Boolean = false,
    @ColumnInfo var strainsUploaded : Boolean = false
)
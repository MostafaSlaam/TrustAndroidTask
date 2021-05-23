package trust.androidtask.db

import androidx.room.Database
import androidx.room.RoomDatabase
import trust.androidtask.model.Job

@Database(entities = [Job::class],version = 1)
abstract class JobsDataBase:RoomDatabase() {
    abstract fun getJobDao():JobDao
}
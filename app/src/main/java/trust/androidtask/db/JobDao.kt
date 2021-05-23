package trust.androidtask.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import trust.androidtask.model.Job

@Dao
interface JobDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addJobs(jobs:List<Job>)

    @Query("select * from job_table")
    suspend fun getAllJobs(): List<Job>


}
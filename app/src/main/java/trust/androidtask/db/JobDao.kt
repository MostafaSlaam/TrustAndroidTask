package trust.androidtask.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import trust.androidtask.model.Job

@Dao
interface JobDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addJobs(jobs:List<Job>)

    @Query("select * from job_table")
     fun getAllJobs(): Flow<List<Job>>

    @Update()
    suspend fun updateFavJobs(jobs:List<Job>)

    @Query("select * from job_table where company like '%' || :companyName || '%' or  title like '%' || :jobTitle || '%'")
    fun getSearchJobs(companyName:String,jobTitle:String): Flow<List<Job>>
}
package trust.androidtask.repository

import androidx.lifecycle.LiveData
import trust.androidtask.model.Job
import trust.androidtask.util.AppResult

interface JobsRepository {
    suspend fun getAllJobs() : AppResult<List<Job>>
}

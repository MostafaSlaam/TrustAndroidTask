package trust.androidtask.views.jobs

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import trust.androidtask.db.JobDao
import trust.androidtask.repository.JobsRepository
import trust.androidtask.util.AppResult
import trust.androidtask.util.SingleLiveEvent

class JobsViewModel(private val repository: JobsRepository, val dao: JobDao) : ViewModel() {
    val showLoading = ObservableBoolean()
    val stopRefresh = SingleLiveEvent<Any?>()
    var jobsList = dao.getAllJobs().asLiveData()
    val showError = SingleLiveEvent<String?>()

    fun getAllJobsApi() {
        viewModelScope.launch {
            stopRefresh.call()
            showLoading.set(true)
            val result = repository.getAllJobs()
            showLoading.set(false)
            when (result) {
                is AppResult.Success -> {
                    dao.addJobs(result.successData!!)
                    showError.value = null

                }
                is AppResult.Error ->
                    showError.value = result.exception.message
            }
        }
    }
    fun addToFav(pos:Int)
    {
        viewModelScope.launch {
            dao.updateFavJobs(jobsList.value!!)
        }
    }

//    fun getAllJobsDb() {
//        viewModelScope.launch {
//            jobsList =
//                dao.getAllJobs() as LiveData
//
//        }
//    }

}
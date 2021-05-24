package trust.androidtask.views.jobs

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import trust.androidtask.db.JobDao
import trust.androidtask.repository.JobsRepository
import trust.androidtask.util.AppResult
import trust.androidtask.util.SingleLiveEvent

class JobsViewModel(private val repository: JobsRepository, val dao: JobDao) : ViewModel() {
    val showLoading = ObservableBoolean()
    val stopRefresh = SingleLiveEvent<Any?>()
    val refreshView=SingleLiveEvent<Any?>()
    val showError = SingleLiveEvent<String?>()

    val searchQuery = MutableStateFlow("")
    private val searchedJobs = searchQuery.flatMapLatest {
        dao.getSearchJobs(it, it)
    }

    //    var jobsList = dao.getAllJobs().asLiveData()
    var jobsList = searchedJobs.asLiveData()
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
                    refreshView.call()

                }
                is AppResult.Error -> {
                    showError.value = result.exception.message
                    refreshView.call()
                }
            }
        }
    }

    fun addToFav(pos: Int) {
        viewModelScope.launch {
            dao.updateFavJobs(jobsList.value!!)
        }
    }


}
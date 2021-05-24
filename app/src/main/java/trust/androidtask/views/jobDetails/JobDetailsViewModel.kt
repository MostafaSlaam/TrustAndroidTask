package trust.androidtask.views.jobDetails

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import trust.androidtask.db.JobDao
import trust.androidtask.model.Job
import trust.androidtask.repository.JobsRepository
import trust.androidtask.util.AppResult
import trust.androidtask.util.SingleLiveEvent

class JobDetailsViewModel(private val repository: JobsRepository, val dao: JobDao) : ViewModel() {
    val searchQuery = MutableStateFlow("")
    private val searchedJobs = searchQuery.flatMapLatest {
        dao.getSearchJobs(it, it)
    }

    var jobsList = searchedJobs.asLiveData()
    var jobItem=MutableLiveData<Job>()
    fun toggleFav()
    {
        jobItem.value!!.isFav=!jobItem.value!!.isFav
        viewModelScope.launch {
            dao.updateFavJobs(jobsList.value!!)
        }
    }


}
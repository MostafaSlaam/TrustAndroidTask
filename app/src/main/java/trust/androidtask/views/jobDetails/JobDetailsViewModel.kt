package trust.androidtask.views.jobDetails

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import trust.androidtask.db.JobDao
import trust.androidtask.model.Job
import trust.androidtask.repository.JobsRepository
import trust.androidtask.util.AppResult
import trust.androidtask.util.SingleLiveEvent

class JobDetailsViewModel(private val repository: JobsRepository, val dao: JobDao) : ViewModel() {
    var jobsList = dao.getAllJobs().asLiveData()
    var jobItem=MutableLiveData<Job>()
    val refreshView = SingleLiveEvent<Any?>()
    fun toggleFav()
    {
        jobItem.value!!.isFav=!jobItem.value!!.isFav
        viewModelScope.launch {
            dao.updateFavJobs(jobsList.value!!)
        }
//        refreshView.call()
    }


}
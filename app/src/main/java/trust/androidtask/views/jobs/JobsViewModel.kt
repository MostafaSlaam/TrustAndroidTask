package trust.androidtask.views.jobs

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import trust.androidtask.model.Job
import trust.androidtask.repository.JobsRepository
import trust.androidtask.util.AppResult
import trust.androidtask.util.SingleLiveEvent

class JobsViewModel(private val repository : JobsRepository):ViewModel() {
    val showLoading = ObservableBoolean()
    val stopRefresh = SingleLiveEvent<Any?>()
    val jobsList = MutableLiveData<List<Job>?>()
    val showError = SingleLiveEvent<String>()

    fun getAllJobs() {
        viewModelScope.launch {
            stopRefresh.call()
            showLoading.set(true)
            val result =  repository.getAllJobs()
            showLoading.set(false)
            when (result) {
                is AppResult.Success -> {
                    jobsList.value = result.successData!!
                    showError.value = null

                }
                is AppResult.Error ->
                    showError.value = result.exception.message
            }
        }
    }

}
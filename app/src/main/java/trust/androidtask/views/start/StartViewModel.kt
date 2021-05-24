package trust.androidtask.views.start

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import trust.androidtask.repository.JobsRepository
import trust.androidtask.util.AppResult
import trust.androidtask.util.SingleLiveEvent

class StartViewModel():ViewModel() {

     val navigateToJobs = SingleLiveEvent<Any>()
    fun onStartBtnClick()
    {
        navigateToJobs.call()
    }

}
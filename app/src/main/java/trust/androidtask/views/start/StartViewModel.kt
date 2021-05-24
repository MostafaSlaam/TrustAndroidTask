package trust.androidtask.views.start

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import trust.androidtask.repository.JobsRepository
import trust.androidtask.util.AppResult
import trust.androidtask.util.SingleLiveEvent

class StartViewModel(private val repository : JobsRepository):ViewModel() {

     val navigateToJobs = SingleLiveEvent<Any>()
    fun onStartBtnClick()
    {
        navigateToJobs.call()
    }
    fun getAllCountries() {
        viewModelScope.launch {
            val result =  repository.getAllJobs()

            when (result) {
                is AppResult.Success -> {
                    Log.d("slaam", result.successData.toString())

                }
                is AppResult.Error ->
                    Log.d("error",  result.exception.message.toString())
            }
        }
    }

}
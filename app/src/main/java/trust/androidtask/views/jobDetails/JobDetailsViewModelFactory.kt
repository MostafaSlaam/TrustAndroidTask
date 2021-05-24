package trust.androidtask.views.jobDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import trust.androidtask.db.JobDao
import trust.androidtask.repository.JobsRepository

class JobDetailsViewModelFactory(
    private val repository: JobsRepository,
    private val dao: JobDao
): ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel> create(modelClass:Class<T>): T {
        return JobDetailsViewModel(repository,dao) as T
    }
}
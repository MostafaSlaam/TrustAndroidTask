package trust.androidtask.views.jobs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import trust.androidtask.db.JobDao
import trust.androidtask.repository.JobsRepository

class JobsViewModelFactory(
    private val repository: JobsRepository,
    private val dao: JobDao
): ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel> create(modelClass:Class<T>): T {
        return JobsViewModel(repository,dao) as T
    }
}
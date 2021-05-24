package trust.androidtask.views.jobDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import org.koin.android.ext.android.get
import trust.androidtask.R
import trust.androidtask.adapter.OnRecyclerItemClickListener
import trust.androidtask.adapter.RecyclerJobsAdapter
import trust.androidtask.databinding.FragmentJobsBinding
import trust.androidtask.databinding.FragmentJobsDetailsBinding
import trust.androidtask.model.Job
import trust.androidtask.views.jobs.JobsViewModel
import trust.androidtask.views.jobs.JobsViewModelFactory

class JobDetailsFragment:Fragment(R.layout.fragment_jobs_details) {
    private lateinit var viewModel : JobDetailsViewModel
    private lateinit var binding: FragmentJobsDetailsBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentJobsDetailsBinding.bind(view)

        viewModel= ViewModelProvider(
            this,
            JobDetailsViewModelFactory(get(),get())
        ).get(JobDetailsViewModel::class.java)
        binding.viewModel = viewModel
        var index=arguments!!.getInt("job_index")

        viewModel.jobsList.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                if (it.isNotEmpty()) {
                    viewModel.jobItem.value=it.get(index)
                    binding.invalidateAll()
                }
            }
        })
    }


}
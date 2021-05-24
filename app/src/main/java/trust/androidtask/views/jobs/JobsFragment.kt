package trust.androidtask.views.jobs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import org.koin.android.ext.android.get
import trust.androidtask.R
import trust.androidtask.adapter.OnRecyclerItemClickListener
import trust.androidtask.adapter.RecyclerJobsAdapter
import trust.androidtask.databinding.FragmentJobsBinding
import trust.androidtask.model.Job

class JobsFragment:Fragment(R.layout.fragment_jobs) {
    private lateinit var jobsViewModel : JobsViewModel
    private lateinit var binding: FragmentJobsBinding
    private lateinit var adapter:RecyclerJobsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentJobsBinding.bind(view)
        jobsViewModel= ViewModelProvider(
            this,
            JobsViewModelFactory(get(),get())
        ).get(JobsViewModel::class.java)
        binding.viewModel = jobsViewModel
        jobsViewModel.stopRefresh.observe(viewLifecycleOwner, Observer {
            binding.swipeRefresh.isRefreshing=false
        })
        adapter= RecyclerJobsAdapter(ArrayList(),object :OnRecyclerItemClickListener{
            override fun onRecyclerItemClickListener(position: Int) {
                var bundle=Bundle()
                bundle.putInt("job_index",position)
                findNavController().navigate(R.id.jobDetailsFragment,bundle)
            }

            override fun toggleFavourite(position: Int) {
                jobsViewModel.addToFav(position)
            }
        })
        binding.rvJobs.adapter=adapter

        jobsViewModel.jobsList.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                if (it.isNotEmpty()) {
                    adapter.setList(it as ArrayList<Job>)
                }
                else
                    jobsViewModel.getAllJobsApi()
            }
        })
    }


}
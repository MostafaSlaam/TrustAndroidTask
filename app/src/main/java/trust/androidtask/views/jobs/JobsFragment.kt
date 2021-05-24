package trust.androidtask.views.jobs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_jobs, container, false
        )
        val mRootView = binding.root
        binding.lifecycleOwner = this
        return mRootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        jobsViewModel= JobsViewModel(repository = get())
        binding.viewModel = jobsViewModel
        jobsViewModel.stopRefresh.observe(viewLifecycleOwner, Observer {
            binding.swipeRefresh.isRefreshing=false
        })
        adapter= RecyclerJobsAdapter(ArrayList(),object :OnRecyclerItemClickListener{
            override fun onRecyclerItemClickListener(position: Int) {

            }
        })
        binding.rvJobs.adapter=adapter
        jobsViewModel.getAllJobs()
        jobsViewModel.jobsList.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                if (it.isNotEmpty()) {
                    adapter.setList(it as ArrayList<Job>)
                }
            }
        })

    }
}
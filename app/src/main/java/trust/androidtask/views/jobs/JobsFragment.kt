package trust.androidtask.views.jobs

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
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
import trust.androidtask.util.onQueryTextChanged

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
        jobsViewModel.refreshView.observe(viewLifecycleOwner, Observer {
            binding.invalidateAll()
        })
        adapter= RecyclerJobsAdapter(ArrayList(),object :OnRecyclerItemClickListener{
            override fun onRecyclerItemClickListener(position: Int) {
                var bundle=Bundle()
                bundle.putString("search_word",jobsViewModel.searchQuery.value)
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
                else if (jobsViewModel.searchQuery.value.isNotEmpty())
                {
                    jobsViewModel.searchQuery.value=""
                    showToast()
                }
                else
                    jobsViewModel.getAllJobsApi()
            }
        })
        setHasOptionsMenu(true)
    }

    fun showToast()
    {
        Toast.makeText(requireContext(),getString(R.string.no_jobs),Toast.LENGTH_SHORT).show()
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_fragment_jobs,menu)
        val searchItem=menu.findItem(R.id.search_actioin)
        val searchView=searchItem.actionView as SearchView
        searchView.onQueryTextChanged{
            jobsViewModel.searchQuery.value=it
        }

    }


}
package trust.androidtask.views.start

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import trust.androidtask.R
import trust.androidtask.databinding.FragmentStartBinding

class StartFragment : Fragment(R.layout.fragment_start) {
    private lateinit var startViewModel :StartViewModel
    private lateinit var binding: FragmentStartBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentStartBinding.bind(view)
        startViewModel= ViewModelProvider(this).get(StartViewModel::class.java)
        binding.viewModel = startViewModel
        startViewModel.navigateToJobs.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.jobsFragment)
        })
    }

}
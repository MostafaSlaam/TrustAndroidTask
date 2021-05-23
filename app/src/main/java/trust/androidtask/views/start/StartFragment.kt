package trust.androidtask.views.start

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import org.koin.androidx.viewmodel.ext.android.viewModel
import trust.androidtask.R
import trust.androidtask.databinding.FragmentStartBinding

class StartFragment : Fragment(R.layout.fragment_start) {
    private val startViewModel :StartViewModel by viewModels()
    private lateinit var binding: FragmentStartBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_start, container, false
        )
        val mRootView = binding.root
        binding.lifecycleOwner = this
        return mRootView
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = startViewModel


    }
}
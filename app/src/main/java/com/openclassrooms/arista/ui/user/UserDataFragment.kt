package com.openclassrooms.arista.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.openclassrooms.arista.databinding.FragmentUserDataBinding
import com.openclassrooms.arista.domain.model.User
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch


@AndroidEntryPoint
class UserDataFragment : Fragment() {
    private lateinit var binding: FragmentUserDataBinding
    private val viewModel: UserDataViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.userFlow.collect { user: User? ->
                user?.let {
                    binding.etName.setText(it.name)
                    binding.etEmail.setText(it.email)
                }
            }
        }
    }
}

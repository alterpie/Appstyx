package com.appstyx.authtest.ui.signup

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.appstyx.authtest.R
import com.appstyx.authtest.databinding.FragmentSignupBinding
import com.appstyx.authtest.di.AppInjector
import com.appstyx.authtest.ui.base.BaseFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Provider

class SignupFragment : BaseFragment<FragmentSignupBinding>() {

    @Inject
    internal lateinit var vmProvider: Provider<SignupViewModel>
    private val viewModel by viewModels<SignupViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return vmProvider.get() as T
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AppInjector.getAppComponent().inject(this)
    }

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSignupBinding {
        return FragmentSignupBinding.inflate(inflater, container, false)
    }

    override fun initLifecycle(binding: FragmentSignupBinding): LifecycleObserver {
        return object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                initViews(binding)
                viewModel.loadGenders()

                viewModel.state
                    .onEach { render(it, binding) }
                    .launchIn(viewLifecycleOwner.lifecycleScope)
            }
        }
    }

    private fun render(
        state: SignupState,
        binding: FragmentSignupBinding
    ) = with(binding) {
        val names = state.genders.map { it.name }
        (genderSelector.editText as AutoCompleteTextView).apply {
            setAdapter(ArrayAdapter(requireContext(), R.layout.list_item, names))
            setOnItemClickListener { _, _, position, _ ->
                viewModel.selectGender(position)
            }
        }
    }

    private fun initViews(binding: FragmentSignupBinding) = with(binding) {
        signupButton.setOnClickListener {
            viewModel.onSignupClick()
        }
    }

    companion object {
        fun newInstance() = SignupFragment()
    }

}
package com.appstyx.authtest.ui.signup

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
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
import com.appstyx.authtest.ui.main.MainViewModel
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
    @Inject
    internal lateinit var mainVmProvider: Provider<MainViewModel>
    private val mainViewModel by activityViewModels<MainViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return mainVmProvider.get() as T
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

                viewModel.events
                    .onEach { onEvent(it, binding) }
                    .launchIn(viewLifecycleOwner.lifecycleScope)
                viewModel.state
                    .onEach { render(it, binding) }
                    .launchIn(viewLifecycleOwner.lifecycleScope)
            }
        }
    }

    private fun onEvent(event: Any, binding: FragmentSignupBinding) = with(binding) {
        when (event) {
            SignupEvent.EmailEmpty -> inputLayoutEmail.error = getString(R.string.signup_gender_validation_empty_field)
            SignupEvent.FirstNameEmpty -> inputLayoutFirstName.error =
                getString(R.string.signup_gender_validation_empty_field)
            SignupEvent.LastNameEmpty -> inputLayoutLastName.error =
                getString(R.string.signup_gender_validation_empty_field)
            SignupEvent.GenderEmpty -> genderSelector.error =
                getString(R.string.signup_gender_validation_empty_field)
            SignupEvent.SignupSuccess -> mainViewModel.navigate(MainViewModel.Destination.Home)
            is SignupEvent.ApiError -> {
                val inputLayout = when (event.key) {
                    InputKey.Email -> inputLayoutEmail
                    InputKey.FirstName -> inputLayoutFirstName
                    InputKey.LastName -> inputLayoutLastName
                }
                inputLayout.error = event.message
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
                genderSelector.error = null
            }
        }
    }

    private fun initViews(binding: FragmentSignupBinding) = with(binding) {
        signupButton.setOnClickListener {
            viewModel.onSignupClick()
        }
        inputLayoutEmail.editText?.doAfterTextChanged {
            inputLayoutEmail.error = null
            viewModel.onEmailChanged(it?.toString() ?: "")
        }
        inputLayoutFirstName.editText?.doAfterTextChanged {
            inputLayoutFirstName.error = null
            viewModel.onFirstNameChanged(it?.toString() ?: "")
        }
        inputLayoutLastName.editText?.doAfterTextChanged {
            inputLayoutLastName.error = null
            viewModel.onLastNameChanged(it?.toString() ?: "")
        }
    }

    companion object {
        fun newInstance() = SignupFragment()
    }

}
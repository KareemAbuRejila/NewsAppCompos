package edu.training.myapplication.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.training.myapplication.domain.usecases.app_entry.AppEntryUseCases
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) :  ViewModel() {

    fun onEvent(event: OnBoardingEvent){
        when(event){
            is OnBoardingEvent.SaveAppEntry ->{
                saveEntry()
            }
        }
    }

    private fun saveEntry() {
        viewModelScope.launch {
            appEntryUseCases.saveAppEntry()
        }
    }
}
package com.bawender.creations

import androidx.lifecycle.*

class LoginViewModel : ViewModel() {
    private val _userEmail = MutableLiveData<String>()
    private val _userPassword = MutableLiveData<String>()

    private val _loginEnabled = getEnabledLiveData(_userEmail, _userPassword)
    val loginEnabled: LiveData<Boolean>
        get() = _loginEnabled


    /*
    setter function that updates the value of the email field.
     */
    fun updateUserEmail(email: String) {
        _userEmail.value = email
    }

    /*
    setter function that updates the value of the password field.
     */
    fun updateUserPassword(password: String) {
        _userPassword.value = password
    }
}

/**
 * Generator function that helps in creating a live data that is dependent on other
 * livedata values.
 */
fun getEnabledLiveData(source1: LiveData<String>, source2: LiveData<String>): LiveData<Boolean> {
    val result = MediatorLiveData<Boolean>()

    //Function that needs to be called on each live-data observable change.
    val calculateEnabled = Observer<String> {
        result.value =
            source1.value?.isNotEmpty() == true && source2.value?.isNotEmpty() == true
    }

    //Adding sources and function to be triggered for change.
    result.addSource(source1, calculateEnabled)
    result.addSource(source2, calculateEnabled)

    return result
}







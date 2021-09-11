package go.deyu.composesample.ui.component.location

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.intersense.myneighbor.navi.NavigationCommand
import com.intersense.myneighbor.navi.NavigationDirections
import com.intersense.myneighbor.navi.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import go.deyu.composesample.ktx.awaitLastLocation
import go.deyu.composesample.ktx.locationFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ShowLocationViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val navigationManager: NavigationManager,
    private val fusedLocationProviderClient: FusedLocationProviderClient
) : ViewModel() {


    val myLocation = MutableLiveData<Location>()
    fun navigate(command: NavigationCommand) {
        navigationManager.navigate(command)
    }

    fun getLastLocation() {
        viewModelScope.launch {
            val locaiton = fusedLocationProviderClient.awaitLastLocation()
            Timber.d("getLastLocation Location : $locaiton")

            myLocation.value = locaiton
        }
    }

    fun startLocationFlow() {
        Timber.d(" startLocationFlow : ")

        viewModelScope.launch {
            fusedLocationProviderClient.locationFlow().collect {
                Timber.d("startLocationFlow Location : $it")
                myLocation.value = it
            }
        }
    }

    fun openSetting() {
        navigationManager.navigate(NavigationDirections.open_location_permission)
    }

}
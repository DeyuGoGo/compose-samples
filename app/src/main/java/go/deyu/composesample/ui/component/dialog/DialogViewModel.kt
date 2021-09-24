package go.deyu.composesample.ui.component.dialog

import android.location.Location
import android.os.Looper
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
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DialogViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val navigationManager: NavigationManager
) : ViewModel() {

}
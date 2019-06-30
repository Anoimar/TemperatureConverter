package com.thernat.tempconverter

import android.util.Log
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.thernat.tempconverter.data.TemperatureRepository
import kotlinx.coroutines.*

/**
 * Created by m.rafalski on 2019-06-28.
 */
class MainPresenter(private val temperatureRepository: TemperatureRepository): MvpBasePresenter<MainView>(){

    var fromCelsiusToFahrenheitJob: Job? = null

    fun convertCelsiusToFahrenheit(celsius: Double){
        ifViewAttached { view -> view.showLoading() }
        ifViewAttached { view ->
           fromCelsiusToFahrenheitJob =   CoroutineScope(Dispatchers.IO).launch{
                try {
                    val result = temperatureRepository.convertFromCelsiusToFahrenheit(celsius)
                    withContext(Dispatchers.Main) {
                        ifViewAttached {view.showConversionToFahrenheitResult(result)}
                    }
                } catch (e: Exception) {
                    Log.e("MainPresenter","Failed to convert Celsius to Fahrenheit",e)
                }
            }
        }
    }

    override fun detachView() {
        super.detachView()
        fromCelsiusToFahrenheitJob?.cancel()
    }
}
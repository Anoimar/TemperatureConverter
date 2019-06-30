package com.thernat.tempconverter

import android.os.Bundle
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateActivity
import com.thernat.tempconverter.data.TemperatureRepository
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : MvpViewStateActivity<MainView,MainPresenter,MainViewState>(),MainView {

    private val mainPresenter : MainPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bSend.setOnClickListener {
            etToConvert.text.toString().toDoubleOrNull()?.let { temperature ->
                presenter.convertCelsiusToFahrenheit(temperature)
            }
        }
    }

    override fun onNewViewStateInstance() {

    }

    override fun createViewState() = MainViewState()

    override fun createPresenter(): MainPresenter {
        return mainPresenter
    }

    override fun showLoading() {

    }

    override fun showConversionToFahrenheitResult(result: String) {
        tvResult.text = getString(R.string.fahrenheit_result,result)
    }
}

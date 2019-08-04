package com.thernat.tempconverter

import android.os.Bundle
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : MvpViewStateActivity<MainView,MainPresenter,MainViewState>(),MainView {

    private val mainPresenter : MainPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bToFahrenheit.setOnClickListener {
            etToConvert.text.toString().toDoubleOrNull()?.let { temperature ->
                presenter.convertCelsiusToFahrenheit(temperature)
            }
        }
        bToCelsius.setOnClickListener {
            etToConvert.text.toString().toDoubleOrNull()?.let { temperature ->
                presenter.convertFahrenheitToCelsius(temperature)
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
        tvResult.text = getString(R.string.conversion_fahrenheit_result,result)
    }

    override fun showConversionToCelsiusResult(result: String) {
        tvResult.text = getString(R.string.conversion_celsius_result,result)
    }
}

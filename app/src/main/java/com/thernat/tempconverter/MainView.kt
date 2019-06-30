package com.thernat.tempconverter

import com.hannesdorfmann.mosby3.mvp.MvpView

/**
 * Created by m.rafalski on 2019-06-28.
 */
interface MainView: MvpView {

    fun showLoading()

    fun showConversionToFahrenheitResult(result: String)

}
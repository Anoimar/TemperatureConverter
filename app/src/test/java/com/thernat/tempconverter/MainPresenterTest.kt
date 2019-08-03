package com.thernat.tempconverter


import com.thernat.tempconverter.data.TemperatureDataSource
import org.junit.Test
import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import com.nhaarman.mockitokotlin2.mock
import com.thernat.tempconverter.di.netModule
import kotlinx.coroutines.Dispatchers
import com.nhaarman.mockitokotlin2.then
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.koin.dsl.module

/**
 * Created by m.rafalski on 2019-07-28.
 */
@ExperimentalCoroutinesApi
class MainPresenterTest: KoinTest {

    companion object{
        const val TWENTY_ONE_CELSIUS = 21.00
        const val TWENTY_ONE_CELSIUS_DEGREE_IN_FAHRENHEIT = "69.8"
    }

    private val view: MainView = mock()
    private val repository: TemperatureDataSource by inject()
    private val presenter: MainPresenter by inject ()
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)



    @Before
    fun before(){
        setTestCoroutines()
        configureKoinWithMocks()
        presenter.attachView(view)
    }

    private fun configureKoinWithMocks() {
        val mockRepo = mock<TemperatureDataSource>{
            onBlocking{
                convertFromCelsiusToFahrenheit(TWENTY_ONE_CELSIUS)}.thenReturn(TWENTY_ONE_CELSIUS_DEGREE_IN_FAHRENHEIT)
        }

        val mockedKoinModule = module {
            single { mockRepo }
            factory { MainPresenter(get()) }
        }

        startKoin {
            modules(listOf(mockedKoinModule, netModule))
        }
    }

    private fun setTestCoroutines() {
        Dispatchers.setMain(testDispatcher)

    }

    @Test
    fun `should show loading call repository with correct param and display result when presenter convert method called`() {
        presenter.convertCelsiusToFahrenheit(TWENTY_ONE_CELSIUS)
        then(view).should().showLoading()
        testScope.runBlockingTest {
            then(repository).should().convertFromCelsiusToFahrenheit(TWENTY_ONE_CELSIUS)
        }
        then(view).should().showConversionToFahrenheitResult(TWENTY_ONE_CELSIUS_DEGREE_IN_FAHRENHEIT)

    }

    @After
    fun after() {
        stopKoin()
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }
}
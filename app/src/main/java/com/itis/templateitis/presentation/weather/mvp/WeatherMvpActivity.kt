package com.itis.templateitis.presentation.weather.mvp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.google.android.material.snackbar.Snackbar
import com.itis.templateitis.databinding.ActivityMainBinding
import com.itis.templateitis.domain.usecase.GetWeatherUseCase
import com.itis.templateitis.presentation.weather.WeatherActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import moxy.MvpAppCompatActivity
import moxy.MvpView
import moxy.ktx.moxyPresenter
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Provider

class WeatherMvpActivity : MvpAppCompatActivity(), WeatherView, HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any?>

    @Inject
    @InjectPresenter
    lateinit var presenter: WeatherPresenter

    @ProvidePresenter
    fun providePresenter(): WeatherPresenter = presenter

    private var binding: ActivityMainBinding? = null

    override fun androidInjector() = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        binding?.btnGetWeather?.setOnClickListener {
            presenter.onGetWeatherClick("Paris")
        }

//        val disposable = findViewById<EditText>(R.id.btn_get_weather).observeText()
//            .filter { it.length > 2 }
//            .distinctUntilChanged()
//            .debounce(500L, TimeUnit.MILLISECONDS)
//            .subscribeOn(AndroidSchedulers.mainThread())
//            .subscribeBy(onNext = {
////                viewModel.search(it)
//            }, onError = {
//
//            })
    }

    override fun showCurrentTemp(temp: String) {
        Snackbar.make(
            findViewById(android.R.id.content),
            "Temp: ${temp} C",
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun showError(throwable: Throwable) {
        Log.e("tr", throwable.message.orEmpty())
        when (throwable) {
            is HttpException -> {

            }
            is NullPointerException -> {

            }
            else -> {

            }
        }
    }

    override fun showLoading() {
        binding?.progress?.isVisible = true
    }

    override fun hideLoading() {
        binding?.progress?.isVisible = false
    }

    override fun openDetailsScreen() {
        Intent(this, WeatherActivity::class.java).also {
            startActivity(it)
        }
    }
}

package io.github.karadkar.popularmovies

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home_screen.*
import org.koin.android.ext.android.inject

class HomeScreenActivity : AppCompatActivity() {

    // inject viewModel
    val vm: MovieListViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        tv_homescreen_message.text = vm.sayHello()
    }
}

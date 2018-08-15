package io.github.karadkar.popularmovies

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.karadkar.popularmovies.utils.AppConstants
import kotlinx.android.synthetic.main.activity_home_screen.*
import org.koin.android.architecture.ext.getViewModel

class HomeScreenActivity : AppCompatActivity() {

    /**
     * create view-model for activity (by lazy)
     * val viewModel: MovieListViewModel by viewModel()
     *
     * access  this view-model from child fragments
     * val viewModel: MovieListViewModel by sharedViewModel()
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        // movie name
        val movieGenre = "Action / Comedy"

        // inject viewModel
        val viewModel = getViewModel<MovieListViewModel> {
            // sample parameter provided for constructor
            mapOf(AppConstants.KEY_MOVIE_GENRE to movieGenre)
        }

        tv_homescreen_message.text = viewModel.sayHello()
    }
}

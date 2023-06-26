# MoviesExplorer

This is a movie catalog application that uses a puplic API from [TheMovieDB](https://developer.themoviedb.org/), home screen contains of three section each represents a horizontal carousel movies, by clicking on the movie page you will be navigated to its detail.

In order to run you need an API_Key that can be generated from her https://www.themoviedb.org/settings/api , once you generate it add it to the **local.properties** as **MOVIE_API_KEY**.

<p align="center">
      <img src="screenshots/movie loading.png" width="200">
      <img src="screenshots/movie error.png" width="200">
      <img src="screenshots/movie list.png" width="200">
      <img src="screenshots/movie details.png" width="200">
</p>

### Built with:
- Jetpack Compose
- Navigation Graph
- Retrofit for Networking
- Hilt for DI
- Kotlin Coroutine + Flow
- Coil for image loading
- Paging3
- MVVM

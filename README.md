Giphy app
===========================================================

Introduction
-------------

### Building
Download project from github and open in Android Studio 3.4.2+
You should add at the very bottom of the gradle.properties file next fields
 * API_KEY = "api_key" where api_key is your key  which you can get in https://developers.giphy.com/
 * API_URL = "api_url" where api_url is url from where we'll get gifs data 
(it's not secret it must be "https://api.giphy.com/")
Than you should sync your project and press run to deploy.

### Testing
The project uses both instrumentation tests that run on the device
and local unit tests that run on your computer.

#### Device Tests
##### UI Tests
The projects uses Espresso for UI testing.Fragment is limited to a ViewModel, 
each test mocks related ViewModel to run the tests.

#### Local Unit Tests
##### ViewModel Tests
ViewModel is tested using local unit tests with mock Repository
implementations.
##### Repository Tests
Repository is tested using local unit tests with mock web service and
mock database.
##### Webservice Tests
The project uses [MockWebServer][mockwebserver] project to test REST api interactions.


### Libraries
* [AndroidX Library][androidx-lib] is a major improvement to the original Android Support Library
* [Architecture][architecture] - a collection of libraries that help you design robust, testable, and
  maintainable apps. Start with classes for managing your UI component lifecycle and handling data
  persistence.
  * [Data Binding][data-binding] - declaratively bind observable data to UI elements.
  * [Lifecycles][lifecycles] - create a UI that automatically responds to lifecycle events.
  * [LiveData][liveData] - build data objects that notify views when the underlying database changes.
  * [ViewModel][viewModel] - store UI-related data that isn't destroyed on app rotations. Easily schedule
     asynchronous tasks for optimal execution.
* [Dagger 2][dagger2] for dependency injection
* [Retrofit][retrofit] for REST api communication
* [RxJava2][rxjava] for making asynchronous internet calls
* [Moshi][moshi] for easy parsing JSON into Java and Kotlin objects:
* [Glide][glide] for image loading
* [Espresso][espresso] for UI tests
* [Mockito][mockito] for mocking in tests
* [Multidex][multidex] for enabling multi dex in app


### Plagins
* [kotlin-allopen][kotlin-allopen] for making classes open for testing

[mockwebserver]: https://github.com/square/okhttp/tree/master/mockwebserver
[androidx-lib]: https://developer.android.com/jetpack/androidx?gclid=Cj0KCQjwvdXpBRCoARIsAMJSKqLVomkMrfgAXKCInkkevWE8v6qij9x6Xg-YBNPvVbbMPECISjqIJd0aAjPXEALw_wcB
[architecture]: https://developer.android.com/jetpack/arch/
[data-binding]:https://developer.android.com/topic/libraries/data-binding/
[lifecycles]: https://developer.android.com/topic/libraries/architecture/lifecycle
[liveData]: https://developer.android.com/topic/libraries/architecture/livedata
[viewModel]: https://developer.android.com/topic/libraries/architecture/viewmodel
[data-binding]: https://developer.android.com/topic/libraries/data-binding/index.html
[espresso]: https://google.github.io/android-testing-support-library/docs/espresso/
[dagger2]: https://google.github.io/dagger
[retrofit]: http://square.github.io/retrofit
[rxjava]: https://github.com/ReactiveX/RxJava
[moshi]: https://github.com/square/moshi
[glide]: https://github.com/bumptech/glide
[mockito]: http://site.mockito.org
[multidex]: https://developer.android.com/studio/build/multidex
[kotlin-allopen]: https://kotlinlang.org/docs/reference/compiler-plugins.html



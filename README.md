# Movies List - Android приложеине со списком популярных фильмов

## Приложение работает с [Kinopoisk API Unofficial](https://kinopoiskapiunofficial.tech/) и показывает список популярных фильмов с названием, обложкой и отображением жанра и года выпуска.
Для взаимодействия с API использовалась библиотека [Retrofit](https://square.github.io/retrofit/). Запросы отправляются с помощью Kotlin corutines и ответ обрабатывается в виде JSON сериализацией [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization).
Так же приложение покрыто Unit-тестами благодаря фреймворку [JUnit](https://junit.org/junit5/)
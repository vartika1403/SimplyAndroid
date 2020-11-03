package entertainment.simplykotlin

object ExampleSingelton {
    val singletonUser: User by lazy {
        User("vartika.1403@gmail.com", "vartika", "image.png")
    }
}
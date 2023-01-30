package edu.robertconstantin.animeappcliient.core.util


sealed class Resource<T>(val data: T? = null, val text: UiText? = null) { // Tiene instancias de success y error, que son clases que heredan de Resource
    class Success<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(text: UiText) : Resource<T>(null, text)

    // Se usa para saber si la respuesta es exitosa o no

    inline fun <R> mapResourceData(success: (T?) -> R, error: (text: UiText?) -> R): R {  // Se usa para mapear los datos de la respuesta
        return when(this) {
            is Success -> success(this.data)
            is Error -> error(this.text)
        }
    }

//    fun <R> mapSuccess(success: (T?) -> R): Resource<R> {
//        return when(this) {
//            is Success -> Success(success.invoke(this.data))
//            is Error -> Error(this.text)
//        }
//    }
}

package edu.robertconstantin.animeappcliient.core.util

import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

inline fun <T> callApi(call: () -> Response<T>): Resource<T> { // No se guarda en memoria, se ejecuta cada vez que se llama, eso es lo que hacen las funciones inline
    return try {

        val response = call.invoke() //Se ejecuta la funcion que se le pasa como parametro y alli se guarda una funcion

        when {
            response.isSuccessful && response.body() != null -> Resource.Success(response.body()) //Si la respuesta es exitosa y el body no es nulo (me llegan cosas del jason
            // entonces se crea un objeto de tipo Resource.Success y se le pasa el body (que es la lista de heroes)
            else -> Resource.Error(UiText.DynamicString(response.message())) //Si no es exitoso se crea un objeto de tipo Resource.Error y se le pasa el mensaje de error (response.message()))
        }
    }catch (exception: IOException) { //Si hay un error de tipo IOException se crea un objeto de tipo Resource.Error y se le pasa el mensaje de error (exception.message)
        Resource.Error(UiText.DynamicString(exception.message))
    }catch (exception: HttpException) { //Si hay un error de tipo HttpException se crea un objeto de tipo Resource.Error y se le pasa el mensaje de error (exception.message)
        Resource.Error(UiText.DynamicString(exception.message))
    }
}

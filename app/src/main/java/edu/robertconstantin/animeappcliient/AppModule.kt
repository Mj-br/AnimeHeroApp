package edu.robertconstantin.animeappcliient

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//Hilt mira todas las clases que lleva la etiqueta module en di.HeroModule y busca el tipo devuelto (HeroApi) y cuando pasa por toodo ese modulo pues
// lo guarda en la clase AppModule ya montado y listo para usarlo en cualquier parte de la app
@HiltAndroidApp
class AppModule: Application() {
}
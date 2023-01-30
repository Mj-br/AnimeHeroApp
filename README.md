# AnimeHeroApp

Esta aplicación la hemos hecho en clase con el profesor con principios S.O.L.I.D

Se trata de un gestor de hérores donde puedes elegir entre una variedad de ellos, puedes añadirlos a tu lista de favoritos y

tambien puedes quitarlos de esta misma lista.

Tambien se usó una arquitectura separada por capas.

Esta dividido de la siguiente manera:

1) Presentación: se encarga de la interfaz con el usuario, se ocupa de la recolección y visualización de datos.

2) Dominio: esta capa contiene la lógica de negocios y las reglas del sistema. Aquí se define el modelo de datos y se implementan las funciones de negocios.

3) Datos: se encarga de la persistencia y acceso a los datos, proporcionando una interfaz para el almacenamiento y recuperación de datos.

Esta separación permite una mayor claridad en la estructura del sistema, mejora la probabilidad de reutilización y facilita la modificación o actualización de una capa sin afectar las otras.

Tambien hemos usado Jetpack Compose para la creación de la interfaz de usuario en Android Studio.

Hemos implementado todo lo que se nos ha enseñado en el curso como RecyclerViews, Layouts, Navigation, Intents, Dialogs, Time y Data Pickers,

uso de Fragments, uso de ViewPagers, Corrutinas y tareas Asíncronas.

Esta aplicacion se usa junto a la API local AnimeAppServer el cual es la que usamos para la gestión de base de datos.


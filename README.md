# Ap4_Pronosticos_Deportivos
Trabajo integrador del curso JAVA Inicial - Argentina Programa 4.0

*CONSIGNA*

Se solicita el desarrollo de un programa de _pronósticos deportivos_, en el que una persona propone el resultado de un partido (que un equipo gane, pierda o empate), de un deporte X.
Por cada acierto, el participante obtiene una cantidad de puntos.
La idea es implementar un programa por consola con las apuestas de muchas personas, ordenando el listado final en base a los resultados obtenidos por cada una.

*ALCANCE*

Este trabajo solo se limita a pronosticar el resultado de un equipo para un partido dado (apuesta dada por una persona X), sin importar los goles o la estructura del torneo. Simplemente se sumarán los puntos y se emitirá un listado final.


****
*ENTREGA 1*

* Se debé presentar un programa que lea un archivo de partidos (con resultados) y otro con los pronósticos. Es decir, los resultados de una única ronda y las apuestas para los partidos de la misma, dados por una única persona.
* Cada ronda debe tener una cantidad fija de partidos.
* Tomar como argumento dos (2) rutas a cada archivo que se necesita.
* Al leer las líneas de los archivos, debe instanciar objetos de las clases propuestas.
* Debe imprimir por pantalla el puntaje de la persona.

El formato de los archivos utilizados en esta etapa es:

_ronda.txt_


| rondaId | partidoId | LOCAL | VISITANTE | golesLocal | golesVisitante |
|--------:|----------:|------:|----------:|-----------:|----------------|
| ronda1  | partido1  | EQUIPO1 | EQUIPO2 | 2 | 3 |


* rondaId: String para identificar el número de ronda (sería el número de fecha en un torneo de todos contra todos).
* partidoId: String para identificar el número de partido (número fijo de partidos dentro de una ronda/fecha).
* LOCAL y VISITANTE: Implementados como enumeraciones. Para simplificar, el jugador puede ver el nombre de los equipos pero los elige en base de su localía.
* golesLocal y golesVisitante: Valor entero.


_pronostico.txt_
| personaId | rondaId | partidoId | equipoElegido | resultado |
|----------:|--------:|----------:|--------------:|----------:|
|  Julián   | ronda1  | partido1  |   VISITANTE   |  GANADOR  |

* personaId: Identifica a cada persona. Se usará el nombre u otro valor (cadena de texto) para generar a cada jugador de manera única.
* rondaId: Identifica a cada ronda, coincidente con el valor de las rondas dentro de los objetos del torneo.
* partidoId: Mismo identificador para los partidos en las rondas.
* equipoElegido: Aquí se usa la enumeración de localía (EnumLocalia) -> LOCAL o VISITANTE.
* resultado: Aquí se usa la enumeración de resultado (EnumResultado) -> GANADOR, EMPATE, PERDEDOR.


***

*ENTREGA 2*

* Se debe ampliar la información de los archivos de datos y adaptar el programa para que soporten la carga de muchas rondas y muchas personas.
* Al leer cada línea del archivo con información de los resultados, se debe verificar la validez de los datos.
* Cada ronda puede tener cualquier cantidad de partidos.
* Al finalizar el programa, se debe imprimir un listado de los puntajes de cada persona que participa.
* Utilizar la herramienta Maven y su estructura de proyecto.
* Imprimir por pantalla el nombre de cada persona, el puntaje total y la cantidad de pronósticos acertados.
* Implementar un test (al menos uno) que calcule el puntaje de una persona en 2 (dos) rondas consecutivas.

En este caso, las clases creadas en la etapa anterior estaban preparadas para recorrer sendos archivos de texto, con el formato establecido, y generando automáticamente lan N-1 rondas con sus N/2 partidos, como también el número P de personas en el otro. (N es el número de equipos, el cual debe ser par).
El archivo de texto con el torneo/campeonato se creo usando el fixture generado en https://www.campioni.biz/spanish/calendario/crea_calendario10.php?pais=Spain.
Para el caso del archivo de pronósticos de los jugadores, se generó con un pequeño programa aparte, asignando apuestas de manera aleatoria.

***

*ENTREGA 3*

* Los pronósticos deben poder leerse desde una base de datos MySQL.
* Se debe poder configurar la cantidad de puntos que se otorgan cuando se acierta un resultado.
* Se agregan dos reglas para la asignación de puntajes de los personajes:
_Se suman puntos extra cuando se aciertan todos los resultados de una ronda._
_Se suman puntos extra cuando se aciertan todos los resultados de una fase (Modificar los archivos para agregar este dato) sobre un equipo. Se debe considerar que una fase es un conjunto de rondas._
 
Se recomienda analizar qué estrategia se puede aplicar para incluir otras nuevas reglas con el menor impacto posible, de forma simple.

En esta entrega, el programa debe:
 * Estar actualizado en el repositorio de GitHub
 * Recibir como argumento un archivo con los resultados y otro con configuración (conexión a la BBDD, puntaje por partido ganado, puntos extra, etc)
 
 Los pronósiticos se leen desde la base de datos local, utilizando las librerías de conexión MySQL para java, a través de métodos dentro de la clase Publico.
 Para ello, también se crea el paquete "persistencia", en donde se genera una clase abstracta DAO, con las variables que hacen a la configuración de la conextión, el driver y la ruta. También se implementan los métodos de conexión, consulta y desconexión. Luego, para el caso de las entradas de la base de datos, se crea una subclase de DAO: ApuestaDAO, desde la cual se consulta a la tabla correspondiente en la BBDD (con este objeto, se reutiliza la rutina para crear objetos Jugador)
 
 La generación del Juego final se hace desde la misma clase Juego, a través de métodos preparados para pedir los archivos de "resultados" y uno de "configuracion" (txt y json respectivamente). Para la generación de los jugadores (Publico), se utiliza el método de consulta a la BBDD mencionado en el párrafo anterior.
 
 Para leer el archivo de configuraciones (JSON), se agrega un método a la clase Importador, la cual lee el archivo y devuelve un JSONObject, para procesarse dentro de la clase Juego y ser pasado a otro método de ApuestaDAO.
 
 ****
 *FUTURAS IMPLEMENTACIONES
 - Suma de puntos extra por acierto de todos los partidos de una ronda.
 - Testing
 - Iteración con el usuario al ingresar archivos no válidos o inexistentes
 - Manejo de casos en que los datos inconsistentes en el archivo "resultado"
 

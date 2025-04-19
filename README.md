#HISTORIA DE ESPAÑA 

Una API REST con Spring Boot que perimite ver eventos de la historia de España y realizar pequeños test de preguntas 
sobre la historia de España.

Los endpoints de eventos devuelven: 
  - Todos los eventos por fecha de inicio.
  - Todos los eventos por nombre de evento. 
  - Los personajes de un evento.

Los eventos tienen localizaciones con coordenadas para mostrar los eventos sobre un mapa.

##Quiz
Los quizzes(tests) se pueden crear según una época dada o rellenando los con preguntas aleatorias de cualquier época.
Los quizzes se guardan por usuario.

#Tecnologías
  - Java 21
  - Spring Boot
  - Spring Security (JWT)
  - PostgreSQL
  - Maven


La base de datos inicial esta e4n el proyecto. Tiene unos cuantos eventos, personajes, periodos, localizaciones,
preguntas, respuestas.

#ENDPOINT PÚBLICOS
POST-> "/historia-espanha/login/**"
GET-> "/historia-espanha/eventos/**"
GET-> "/historia-espanha/personajes/**"

Todos los demás endpoints son protegidos (hace falta estar autorizado)



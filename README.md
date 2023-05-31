# Buscador de Noticias - Java 11 + Spring Boot + Angular 16 + PostgreSQL + Docker
Este proyecto es una aplicación web que permite visualizar las últimas noticias y almacenarlas en una lista de favoritos. La aplicación está desarrollada utilizando Java Spring Boot para el backend, Angular para el frontend y PostgreSQL como base de datos. Además, el proyecto se encuentra dockerizado para facilitar la instalación y despliegue.

### Requisitos previos
Asegúrate de tener instalados los siguientes componentes en tu máquina:

- Docker: [Instrucciones de instalación de Docker](https://docs.docker.com/get-docker/)
- Docker Compose [Instrucciones de instalación de Docker compose](https://docs.docker.com.zh.xy2401.com/v17.12/compose/install/)
- Java 11
- Maven 3.6.3 +

### Configuración del proyecto
- Clona este repositorio en tu máquina local:
`$ git clone https://github.com/sazlain/amaris-news.git`

- Navega hasta el directorio raíz del proyecto:
`$ cd amaris-news`

- Iniciar el contenedor postgres:
`$ docker-compose up -d postgres-amaris-news`

- Compilar el proyecto java:
`$ mvn clean install`

- Iniciar los contenedores des servidor web del front y el api :
`$ docker-compose up -d api-amaris-news`
`$ docker-compose up -d web-server-amaris-news`

## Documentacion swagger backend
-[http://localhost:8080/amaris-news/swagger-ui/index.html](http://localhost:8080/amaris-news/swagger-ui/index.html)

## Frontend
-[http://localhost](http://localhost/news/bookmarks)

## Autor
##### Azlain Saavedra
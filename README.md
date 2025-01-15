# poc-java-app-products

---

> ## 📄 Endpoints
> #### Consultar todos los productos: `get/sneakers`
> ```bash
> echo get/sneakers | ncat localhost 8081
> ```
> 
> ---
> 
> #### Consultar un producto por su ID: `get/sneakers/{id}`
> ```bash
> echo get/sneakers/1 | ncat localhost 8081
> ```
> 
> ---
> 
> #### Eliminar un producto por su ID: `delete/sneakers/{id}`
> ```bash
> echo delete/sneakers/1 | ncat localhost 8081
> ```
>
> ---
>
> #### Guardar un nuevo producto: `post/sneakers/{base64JSON}`
> ```bash
> echo post/sneakers/eyJicmFuZCI6IlB1bWJhIiwicHJpY2UiOjY5Ljk5LCJxdWFsaWZpY2F0aW9uIjoxLCJwcm92aWRlciI6Ik1hcmtldHBsYWNlIiwiZ2VuZGVyIjoiSG9tYnJlIiwic2l6ZSI6NDQsInR5cGUiOiJVcmJhbmEifQ== | ncat localhost 8081
> ```
> ---
>
> #### Consultar productos por query param: `get/sneakers?{key}={value}`
> ```bash
> echo get/sneakers?provider=Tottus | ncat localhost 8081
> ```
>

> ## ⚙️ Tecnologías
> - `Lombok`: Simplifica la escritura de código repetitivo.
> - `Jackson`: Facilita la conversión entre objetos Java y formato JSON.
> - `Google Guice`: Ayuda a manejar la inyección de dependencias.

> ## 📌 Pre requisitos
> 
> > ### Instalar ncat
> > - Descargar e instalar ncat para Windows desde: https://nmap.org/download.html#windows
> > - Añadir `ncat` a las variables de entorno.
>
> > ### Construir artefacto
> > ```bash
> > mvn clean install
> > ```


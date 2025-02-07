# poc-java-app-ecommerce

---

> # ✅ Productos

### Consultar todos los productos:
```bash
echo get/products | ncat localhost 8081
```


### Consultar un producto por su ID:
```bash
echo get/products/1 | ncat localhost 8081
```

### Consultar productos por categoría:
```bash
echo get/products?category=Accessories | ncat localhost 8081
```

### Consultar productos con un precio menor a:
```bash
echo get/products?lessThanPrice=145 | ncat localhost 8081
```

### Consultar productos que incluyan la palabra:
```bash
echo get/products?nameMatches=phone | ncat localhost 8081
```

### Eliminar un producto por su ID:
```bash
echo delete/products/1 | ncat localhost 8081
```

### Guardar un nuevo producto:
Enviar el JSON encodeado en base 64.
```bash
echo post/products/ew0KCSJuYW1lIjogIkF1ZMOtZm9ub3MiLA0KCSJzdG9jayI6IDE1LA0KCSJ1bml0UHJpY2UiOiAxMzkuOTksDQoJImNhdGVnb3J5IjogIlRlY2hub2xvZ3kiLA0KCSJkZXNjcmlwdGlvbiI6ICJBdWTDrWZvbm9zIGdhbWVyIg0KfQ== | ncat localhost 8081
```

<br><br><br>

> # ✅ Carrito de compras

### Consultar carrito de compras:
```bash
echo get/shopping-carts?documentNumber=32165498 | ncat localhost 8081
```

<br><br><br>


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


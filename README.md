## Album-Collector Back-end

Manual de despliegue del back-end de Album Collector.



### 1. Clonar el repositorio

Nos dirigimos a la carpeta en la que queramos instalar el back-end de Album Collector, y clonamos el repositorio.

````
git clone https://github.com/admorsus/AlbumCollector-Back
````



### 2. Instalar las dependencias

Abrimos Eclipse, nos dirigimos a Eclipse Marketplace, e instalamos Spring Tools 4.



### 3. Importar repositorio en Eclipse

Desde el menú _Importar proyectos desde el Sistema de Archivos_, seleccionamos la ruta de AlbumCollector-Back.

Debemos esperar a que se importen todas las dependencias y termine de compilar el proyecto.

En caso de que no se encuentre la API de validación de Java, entrar en el menú de _Project Set-up_, e importar desde ahí el archivo `validation-api-2.0.1.Final` ... click [aquí](https://repo1.maven.org/maven2/javax/validation/validation-api/2.0.1.Final/validation-api-2.0.1.Final.jar) para descargar.



### 4. Lanzar la aplicación

Abriendo el menú contextual del proyecto en eclipse, lanzamos el programa como una aplicación de Spring Boot.



## Copyright

Copyright 2021, Miguel Bautista Pérez y Alonso Viñé Barrancos

Todos los derechos reservados
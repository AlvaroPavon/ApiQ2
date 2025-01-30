# Usa una imagen base compatible con JDK 23
FROM eclipse-temurin:23-jdk-alpine

# Etiqueta de autores
LABEL authors="alvar"

# Crea un directorio de trabajo en el contenedor
WORKDIR /usr/src/app

# Copia el archivo JAR generado por tu aplicación desde la carpeta por defecto
COPY out/artifacts/ApiQr2_jar/ApiQr2.jar .

# Expone el puerto en el que la aplicación estará corriendo
EXPOSE 4567

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "ApiQr2.jar"]
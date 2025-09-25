# Visor Base de datos (Java + SQLite)

Proyecto de **visor de base de datos y selector** con interfaz gráfica en **Java Swing**, utilizando **SQLite** como base de datos.
---

## Requisitos previos
- **Java JDK 8+** instalado  
  Verifica con:
  ```bash
  java --version

## Driver JDBC de SQLite:
- Descarga el .jar desde Xerial SQLite JDBC, Ejemplo: sqlite-jdbc-3.50.3.0.jar.
- Coloca el archivo .jar en el mismo directorio del proyecto.

## Ejecución del proyecto

Clonar el repositorio (Recomiendo esta opcion)
```bash
git clone https://github.com/cakri99/Semana4.git
cd Formulario_Crud
```
Tambien puedes copiar y pegar el codigo de los archivos, como tambien descargar el driver JDBC en tu ordenador.

# Compilar

```
javac *.java
```

# Ejecutar

Windows
```
java -cp ".;sqlite-jdbc-3.50.3.0.jar" Main
```

Linus/MacOs
```
java -cp ".:sqlite-jdbc-3.50.3.0.jar" Main
```

# Makefile simple para HolaMundo.java

# Nombre del archivo fuente
SOURCES = NoJavaSky.java Planeta.java Helado.java Oceanico.java Radioactivo.java Volcanico.java MapaGalactico.java Tieneasentamientos.java CentroGalactico.java Inventario.java

# Nombre de la clase principal
MAIN_CLASS = NoJavaSky Planeta Helado Oceanico Radioactivo Volcanico MapaGalactico Tieneasentamientos CentroGalactico Inventario

# Regla principal
all: $(SOURCES)
	javac $(SOURCES)

# Regla para ejecutar el programa
run: all
	java $(MAIN_CLASS)

# Regla para limpiar los archivos .class generados
clean:
	rm -f *.class

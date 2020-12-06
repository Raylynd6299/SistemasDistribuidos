#!/bin/bash/

if [ -f "./Servicio.zip" ]
then
	echo "Desempacando el servicio"
	mkdir Servicio
	cd Servicio
	unzip Servicio.zip
else
	echo "No se encuentra el archivo Servicio.zip"
fi

echo "Compilando Servicio.java"
javac -cp $CATALINA_HOME/lib/javax.ws.rs-api-2.0.1.jar:$CATALINA_HOME/lib/gson-2.3.1.jar:. negocio/Servicio.java

echo "Modificar el archivo context.html con tu auraio y contraseña"

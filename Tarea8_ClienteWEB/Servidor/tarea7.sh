#!/bin/bash

# Instalar Java8
echo "Instalando JAVA 8"
sudo add-apt-repository ppa:webupd8team/java
sudo apt update
sudo apt-get install openjdk-8-* -y
sudo update-alternatives --display java

#read ruta
echo "Creando Variable de entorno de JAVA"
ruta="/usr/lib/jvm/java-8-openjdk-amd64"
sudo echo "JAVA_HOME='$ruta'" >> /etc/environment
source /etc/environment

echo "JAVA_HOME = $JAVA_HOME"

sudo apt install unzip -y

if [ -f "./apache-tomcat-8.5.60.zip" ]
then 
	echo "Extrallendo Tomcat"
	unzip apache-tomcat-8.5.60.zip
	rm -r apache-tomcat-8.5.60/webapps/
	mkdir -p apache-tomcat-8.5.60/webapps/ROOT
else
	echo "No esta el archivo para instalart Tomcat"
fi

if [ -f "./jaxrs-ri-2.24.zip" ] 
then
	echo "Obteniendo todos los archivos *.jar de JAC-RS"
	unzip jaxrs-ri-2.24.zip
	mv ./jaxrs-ri/lib/*.jar ./apache-tomcat-8.5.60/lib/
	mv ./jaxrs-ri/api/*.jar ./apache-tomcat-8.5.60/lib/
	mv ./jaxrs-ri/ext/*.jar ./apache-tomcat-8.5.60/lib/
	rm ./apache-tomcat-8.5.60/lib/javax.servlet-api-3.0.1.jar
else
	echo "No existe el archivo jaxrs-ri"
fi

if [ -f "./gson-2.3.1.jar" ]
then
	echo "Copiando gson.jar a Tmcat/lib"
	mv ./gson-2.3.1.jar ./apache-tomcat-8.5.60/lib/
else
	echo "No existe gson.jar"
fi

if [ -f "./mysql-connector-java-8.0.22.zip" ]
then
	echo "Descomprimiendo el Driver mysql"
	unzip mysql-connector-java-8.0.22.zip
	mv mysql-connector-java-8.0.22/mysql-connector-java-8.0.22.jar ./apache-tomcat-8.5.60/lib/
else
	echo "No se encontro el driver mysql"
fi

echo "Creando Variable de entorno de Tomcat"
sudo echo "CATALINA_HOME='$(pwd)/apache-tomcat-8.5.60'" >> /etc/environment
source /etc/environment

sh $CATALINA_HOME/bin/catalina.sh start

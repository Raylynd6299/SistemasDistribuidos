#!/bin/bash/
cd ~/Ray/Servicio/
rm WEB-INF/classes/negocio/*
cp negocio/*.class WEB-INF/classes/negocio/.
jar cvf Servicio.war WEB-INF META-INF
sudo cp Servicio.war ~/Ray/apache-tomcat-8.5.60/webapps/


sudo cp ~/Ray/usuario_sin_foto.png ~/Ray/apache-tomcat-8.5.60/webapps/ROOT
sudo cp ~/Ray/WSClient.js ~/Ray/apache-tomcat-8.5.60/webapps/ROOT
sudo cp ~/Ray/prueba.html ~/Ray/apache-tomcat-8.5.60/webapps/ROOT

#!/bin/bash

# Agregar repositorio
sudo add-apt-repository ppa:webupd8team/java

sudo apt update

sudo apt-get install openjdk-8-* -y

sudo update-alternatives --display java

#read ruta
ruta="/usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java"
sudo echo "JAVA_HOME='$ruta'" >> /etc/environment

sudo source /etc/environment

echo $JAVA_HOME
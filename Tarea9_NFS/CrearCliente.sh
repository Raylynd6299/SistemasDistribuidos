#! /bin/bash

directorio=$1
diripS=$2
directoioServ=$3

echo "Creando Cliente"

sudo apt update
sudo apt install nfs-common

sudo mkdir -p /nfs/$directorio 

echo "Montando directorio"
sudo mount -v -t nfs $diripS:/var/nfs/$directoioServ /nfs/$directorio




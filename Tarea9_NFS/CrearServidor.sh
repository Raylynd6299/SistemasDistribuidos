#! /bin/bash

directorio=$1
dirip=$2
echo "Creando Servidor"


sudo apt update
sudo apt install nfs-kernel-server -y


echo "Creando directorio compartido"
sudo mkdir /var/nfs/$directorio -p

echo "Cambiando permisos"
sudo chown nobody:nogroup /var/nfs/$directorio
sudo chmod 777 /var/nfs/$directorio

sudo echo "/var/nfs/$directorio $dirip(rw,sync,no_subtree_check)" >> /etc/exports

sudo exportfs -ra
sudo exportfs

sudo systemctl restart nfs-kernel-server


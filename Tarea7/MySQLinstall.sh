#!/bin/bash

echo "Instalando MySQL"

sudo apt update
sudo apt install mysql-server
sudo mysql_secure_installation


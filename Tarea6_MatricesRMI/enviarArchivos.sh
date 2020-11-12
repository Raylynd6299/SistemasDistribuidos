#!/bin/bash

Nodo0="52.248.71.206"
Nodo1="13.65.236.95"
Nodo2="23.98.176.195"
Nodo3="40.84.211.227"

Pass="Ray4802250_09"
user="Ray"

archivos="*.java"
javaa="instalJava.sh"

echo "Enviando copia de los archivos del proyecto a ..."

echo "Nodo0: $Nodo0 "
sshpass -p $Pass scp $archivos $user@$Nodo0:~/Ray/
echo "Envio Finalizado..."

echo "Nodo1: $Nodo1 "
sshpass -p $Pass scp $archivos $user@$Nodo1:~/Ray/
echo "Envio Finalizado..."

echo "Nodo2: $Nodo2 "
sshpass -p $Pass scp $archivos $user@$Nodo2:~/Ray/
echo "Envio Finalizado..."

echo "Nodo3: $Nodo3 "
sshpass -p $Pass scp $archivos $user@$Nodo3:~/Ray/
echo "Envio Finalizado..."

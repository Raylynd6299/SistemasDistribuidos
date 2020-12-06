#!/bin/bash/

IPMV="52.183.252.147"
Archivos="*.sh *.html *.js *.zip *.jar *.png"
Password="Ray4802250_09"
Usuario="Ray"
DireccionDest="~/Ray"

sshpass -p $Password scp $Archivos $Usuario@$IPMV:$DireccionDest




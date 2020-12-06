#!/bin/bash/

IPMV="13.84.172.18"
Archivos="*.sh *.html *.js *.zip *.jar *.png"
Password="Ray4802250_09"
Usuario="Ray"
DireccionDest="~/Ray"

sshpass -p $Password scp $Archivos $Usuario@$IPMV:$DireccionDest




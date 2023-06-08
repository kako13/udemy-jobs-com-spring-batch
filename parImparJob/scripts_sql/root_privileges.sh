#!/bin/bash

sleep 10

# Aguarda até que o serviço do MySQL esteja disponível
#until mysqladmin ping -h localhost:3306 -u root -proot; do
#while ! mysqladmin ping -h0.0.0.0 --silent; do
#while ! mysqladmin ping -proot --protocol tcp ; do
until telnet localhost:3306 ; do
    echo "Aguardando o MySQL iniciar..."
    sleep 1
done

# Executa a query SQL desejada
echo ">>>EXECUTANDO QUERY DE PRIVILEGIOS DE ROOT<<<"
mysql -u root -proot -e "update mysql.user set host='%' where user='root'; flush privileges;"
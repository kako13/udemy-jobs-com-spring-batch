#!/bin/bash

until mysql -u root -proot -e " update mysql.user set host='%' where user='root'; flush privileges; "; do
    echo "Aguardando o MySQL iniciar..."
    sleep 1
done

echo -e "\n>>>PRIVILÉGIOS CONCEDIDOS AO USER ROOT DURANTE A INICIALIZAÇÃO<<<"

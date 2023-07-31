#!/bin/bash

sleep 1

until mysql -u root -proot -e "update mysql.user set host='%' where user='root'; update mysql.user set host='%' where user='user'; GRANT ALL PRIVILEGES ON *.* TO 'user'@'%'; flush privileges;"; do
    echo "Aguardando o MySQL iniciar..."
    sleep 1
done

echo -e "\n>>>PRIVILÉGIOS CONCEDIDOS AO USER 'root' e 'user' DURANTE A INICIALIZAÇÃO<<<"

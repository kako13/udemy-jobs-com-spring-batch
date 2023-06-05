FROM mysql/mysql-server:8.0
# Definir variáveis de ambiente para a configuração do MySQL
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=spring_batch
ENV MYSQL_USER=user
ENV MYSQL_PASSWORD=123456

# Copiar arquivos SQL para serem executados na inicialização
#COPY ./arquivos_sql/ /docker-entrypoint-initdb.d/

# Expor a porta padrão do MySQL
EXPOSE 3306
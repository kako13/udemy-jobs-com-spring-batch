FROM mysql/mysql-server:8.0
# Definir variáveis de ambiente para a configuração do MySQL
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=spring_batch
ENV MYSQL_USER=user
ENV MYSQL_PASSWORD=123456

# Expor a porta padrão do MySQL
EXPOSE 3306:3306/tcp

# Copia o script para dentro do contêiner
COPY ./scripts_sql/root_privileges_manual.sh /docker-entrypoint-initdb.d/

# Define permissões de execução para o script
RUN chmod +x /docker-entrypoint-initdb.d/root_privileges_manual.sh
# Use a imagem oficial do MySQL como base
FROM mysql:8.0

# Defina variáveis de ambiente para o banco de dados
ENV MYSQL_ROOT_PASSWORD=medvoll
ENV MYSQL_DATABASE=medvoll
ENV MYSQL_USER=medvolluser
ENV MYSQL_PASSWORD=medvollpassword

# Copie um arquivo SQL inicial, se necessário (opcional)
# COPY ./init.sql /docker-entrypoint-initdb.d/

# Exponha a porta padrão do MySQL
EXPOSE 3306
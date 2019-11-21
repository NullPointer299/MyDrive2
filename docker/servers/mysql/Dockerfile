FROM mysql:latest

RUN mkdir -p /var/log/mysql/
RUN touch /var/log/mysql/mysqld.log

ADD initdb.d/ /docker-entrypoint-initdb.d/
ADD conf.d/ /etc/mysql/conf.d/

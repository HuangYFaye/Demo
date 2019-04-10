FROM centos
MAINTAINER huangyifei huangyifei@sina.com

ADD target/user-provider-bin.tar.gz /usr/local/
ADD jdk-8u201-linux-x64.tar.gz /usr/local/
#ADD ./teller-provider-bin.tar.gz /usr/local/

RUN yum -y install vim
RUN yum -y install net-tools

#ENV DEFAULT_PATH /usr/local/teller-provider/bin
ENV DEFAULT_PATH /usr/local/user-provider/bin

WORKDIR $DEFAULT_PATH

ENV JAVA_HOME /usr/local/jdk1.8.0_201
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV PATH $PATH:$JAVA_HOME/bin
ENV LC_ALL en_US.UTF-8

RUN chmod +x /usr/local/user-provider/bin/start.sh

EXPOSE 20880

#CMD ./start.sh start&&tailf /usr/local/teller-provider/logs/stdout.log
CMD ./start.sh start&&tailf /usr/local/user-provider/logs/stdout.log


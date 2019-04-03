FROM centos
MAINTAINER huangyifei justin_yf@sina.com

ADD apache-tomcat-9.0.17.tar.gz /usr/local/
ADD jdk-8u201-linux-x64.tar.gz /usr/local/
RUN yum -y install vim 

ENV DEFAULT_PATH /usr/local 

WORKDIR $DEFAULT_PATH

ENV JAVA_HOME /usr/local/jdk1.8.0_201
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV CATALINA_HOME /usr/local/apache-tomcat-9.0.17
ENV CATALINA_BASE /usr/local/apache-tomcat-9.0.17
ENV PATH $PATH:$JAVA_HOME/bin:$CATALINA_HOME/lib:$CATALINA_HOME/bin
ENV LC_ALL en_US.UTF-8

EXPOSE 8080

CMD /usr/local/apache-tomcat-9.0.17/bin/startup.sh && tail -F /usr/local/apache-tomcat-9.0.17/logs/catalina.out

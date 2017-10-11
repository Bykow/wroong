FROM jboss/wildfly:latest

COPY data/wroong-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/

COPY data/standalone.xml /opt/jboss/wildfly/standalone/configuration/

RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]

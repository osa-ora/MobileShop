FROM jboss/wildfly
ADD target/TestSession-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/

# create war file
mvn clean package

# remove old ROOT folder and war file
rm -rf /home/mohajel/Desktop/tomcat/apache-tomcat-9.0.86/webapps/ROOT.war
rm -rf /home/mohajel/Desktop/tomcat/apache-tomcat-9.0.86/webapps/ROOT

# copy war file to tomcat and rename it to ROOT.war
cp target/*.war /home/mohajel/Desktop/tomcat/apache-tomcat-9.0.86/webapps/ROOT.war


# open chromium browser localhost:8686
chromium http://localhost:8686/hello


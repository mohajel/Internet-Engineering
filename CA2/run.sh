# create war file
mvn clean package -DskipTests

# shutdown tomcat
/home/mohajel/Desktop/tomcat/apache-tomcat-9.0.86/bin/shutdown.sh

# remove old ROOT folder and war file
rm -rf /home/mohajel/Desktop/tomcat/apache-tomcat-9.0.86/webapps/ROOT.war
rm -rf /home/mohajel/Desktop/tomcat/apache-tomcat-9.0.86/webapps/ROOT

# strat tomcat
/home/mohajel/Desktop/tomcat/apache-tomcat-9.0.86/bin/startup.sh

# copy war file to tomcat and rename it to ROOT.war
cp target/*.war /home/mohajel/Desktop/tomcat/apache-tomcat-9.0.86/webapps/ROOT.war

# open chromium browser localhost:8686
chromium http://localhost:8686/login


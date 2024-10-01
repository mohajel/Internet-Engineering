# create war file
mvn clean package -DskipTests

# shutdown tomcat
/opt/homebrew/Cellar/tomcat@9/9.0.95/libexec/bin/shutdown.sh

# remove old ROOT folder and war file
rm -rf /opt/homebrew/Cellar/tomcat@9/9.0.95/libexec/webapps/ROOT.war
rm -rf /opt/homebrew/Cellar/tomcat@9/9.0.95/libexec/webapps/ROOT

# strat tomcat
/opt/homebrew/Cellar/tomcat@9/9.0.95/libexec/bin/startup.sh

# copy war file to tomcat and rename it to ROOT.war
cp target/*.war /opt/homebrew/Cellar/tomcat@9/9.0.95/libexec/webapps/ROOT.war

# open localhost:8080 in default browser
open http://127.0.0.1:8080/


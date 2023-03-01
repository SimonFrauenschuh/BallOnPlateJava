# SisyphosJava
**Contains the Code / Workflow for the frontend and the Web Application of the Sisyphos 2.0 Project. Written in Java EE**

## Install Wildfly as standalone
1. Navigate to the BallOnPlateJava", where "wildfly-install.sh" is located
	Alternative download from "https://gist.github.com/sukharevd/6087988"
2. Execute "sudo bash wildfly-install.sh"
3. Check status via "sudo systemctl status wildfly"
4. Navigate to the new downloaded folder (not the one from GIT (BallOnPlateJava)) to standalone/configuration and change the standalone.xml to the one, provided in Git
5. Copy the ssl / https certificate from "BallOnPlateJava" to the wildfly-folder/standalone/configuration
	1. It could be that the certificate has exceeded it's period of validity, so you might create a new one (or request at "letsencrypt")
5. Navigate to the bin-folder and execute "sudo bash add-user.sh"
6. Start the server:

	**Option 1**
	1. Stop the service via "sudo systemctl stop wildfly"
	2. Navigate to the bin-folder and execute "sudo bash standalone.sh"
	3. !Don't close the cmd-line!

	**Option 2**
	1. Start the service via "sudo service wildfly start"
		Alternative "sudo systemctl enable wildfly"
		Alternative "sudo systemctl enable wildfly.service"
	2. Restart the service via "sudo systemctl restart wildfly"
		Alternative "sudo systemctl restart wildfly.service"
	
7. Add the postgresql-Driver
- go to the adress, that is shown after wildfly-startup in the cmd-line (like "http://127.0.0.1:9990/console") and navigate to "Deployments"
- Klick on the "+" and select the driver "postgresql-42.2.23", that is provided in Git "BallOnPlateJava/src/main/resources"
- Klick on "next", make sure, that the driver is enabled and "Finish".
- Navigate to "Configuration" - "Subsystems" - "Datasources & Drivers" - "JDBC Drivers" and you should see a Driver called "postgresql-42.2.23.jar"

	
## Install PostgreSQL
1. Execute "sudo apt update" and "sudo apt full-upgrade"
1. Execute "sudo apt install postgresql" to install
2. Execute "sudo su postgres" to change to superuser
3. Execute "creatuser pi -p --interactive" to give your Raspberry admin-rights
	Use Passwort BallOnPlateDSOwner
4. Execute "psql"
5. In the PostgreS cmd-line, execute "CREATE DATABASE pi;"
6. Execute "\q" and "exit"
7. Now go into the Postgres-cmd-line "psql"
8. Execute "CREATE DATABASE BallOnPlateDS;"
9. Connect to the database via "\connect ballonplateds;" to check, if all worked well

## "Start" the application
1. Go to "BallOnPlateJava/target" and copy the file "Sisyphos.war", that is provided in GIT
2. Go to "wildfly-22.0.1.Final/standalone/deployments" and paste the file
3. There should be something going on in the cmd-line, where you started the server
	"WFLYSRV0010: Deployed "Sisyphos.war" (runtime-name : "Sisyphos.war")" is good, errors are bad
4. Go to the web-console - "Deployments". There you should see "Sisyphos.war".
5. To open the application: click on "Sisyphos.war" and use the the link provided (Context root)


### Note!
**This is a RESTful application, which means, that it does nothing, until you open it. Your connection will be closed, after you close the tab, but the data will be safe (Stateless Programming style). So you're device-independent, but you can cause unexpected behaviour if more than one client is sending values!!**

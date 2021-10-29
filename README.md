# BallOnPlateJava
## Contains the Code / Workflow for the frontend and the Web Application of the BallOnPlate Project. Written in Java EE

### Install Wildfly as standalone
1. Navigate to the folder, where "wildfly-install.sh" is located
2. Execute "sudo bash wildfly-install.sh"
3. Check status via "sudo systemctl status wildfly"
4. Navigate to standalone/configuration and change the standalone.xml to the one, provided in Git
5. Move the .war file to standalone/deployments
6. Start the service via "sudo service wildfly start"
	Alternative "sudo systemctl enable wildfly"
	Alternative "sudo systemctl enable wildfly.service"
7. Restart the service via "sudo systemctl restart wildfly"
	Alternative "sudo systemctl restart wildfly.service"
	
### Install PostgreSQL

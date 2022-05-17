# mongo-db-tutorial
Spring Boot Tutorial - Build a Rest Api with MongoDB


#Passos:

Repo: https://github.com/amigoscode/mongodb-course

#Passos:
1. Criar um projeto Spring Bot com
	a. Lombox
	b. Spring Web
	c. Spring Data MongoDB
2. Run Docker to start MongDB
	a. Criar o arquivo docker-compose.yaml
		i. Mongdb
			1) :27017
		ii. mong-express
			1) http://localhost:8081/
				a) rootuser
				b) rootpassword
		iii. Rodar o docker-composite dentro do InteliJ
3. Configurar o application.properites conforme abaixo:
	spring.data.mongodb.authentication-database=admin
	spring.data.mongodb.username=rootuser
	spring.data.mongodb.password=rootpass
	spring.data.mongodb.database=amigoscode
	spring.data.mongodb.port=27017
	spring.data.mongodb.hostname=localhost
	spring.data.mongodb.auto-index-creation=true 
4. CRUD Example iwth MongoTemplate

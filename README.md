# JPAImplementaion
JPA and Docker


docker command

#Docker Version & System
docker --version            # Docker version
docker version              # Client + server version
docker info                # Docker system info


#Docker Images
docker search imageName  # search docker image in docker hub
docker images              # List images
docker image ls            # Same as above
docker pull nginx          # Download image
docker build -t app .      # Build image from Dockerfile
docker tag app app:v1      # Tag image
docker rmi app              # Remove image
docker rmi -f app          # Force remove image
docker image prune          # Remove unused images


#Docker Containers
docker ps                  # Running containers
docker ps -a               # All containers
docker run app              # Run container
docker run -d app           # Run in background
docker run -p 8080:8080 app   # Port mapping
docker run --name myapp app   # Name container
docker stop myapp             # Stop container
docker start myapp            # Start container
docker restart myapp          # Restart container
docker rm myapp               # Remove container
docker rm -f myapp            # Force remove


docker logs myapp             # View logs
docker logs -f myapp          # Follow logs
docker exec -it myapp sh      # Enter container
docker exec -it myapp bash    # Bash shell
docker inspect myapp          # Container details

# remove file from jdk tmp
docker exec 683bc8ac6c08 rm /tmp/filename.txt

#command to jdk file
docker exec 683bc8ac6c08 ls -a

# inside temp file jdk
docker exec 683bc8ac6c08 ls /tmp
# command to copy jar file in docker
docker cp target/docker-1.jar 683bc8ac6c08:/tmp

# commit
docker commit 683bc8ac6c08 sunil-app/docker-demo-3.5.9.jar:v1

# commit file configuration for windows
docker commit --change "CMD [\"java\",\"-jar\",\"/tmp/docker-1.jar\"]" musing_ishizaka sunil-app/docker:v2
docker commit --change "CMD [\"java\",\"-jar\",\"/tmp/docker-demo-3.5.9.jar\"]" priceless_albattani sunil-app/docker:v2

# run container with application
docker run -p 8080:8080 sunil-app/docker:v2

# to build dockerfile of spring boot app
docker build -t sunil-app/docker:v5 .




# Steps to add Docker in Springboot app
1. Install Docker desktop application
2. Pull openjdk image form docker hub using command
	-docker pull openjdk:26-ea-29
3. Run command -> docker Images
4. Run images  -> docker run -it <image name>
5. /exit from jshell
6. To see container -> docker ps -a
7. Start Container -> docker start <container name>
8. Make Spring Boot app
9. Make a Jar file of spring boot <Maven>
10.Copy the jar and paste in container tmp directory using command
	docker cp target/docker-1.jar musing_ishizaka:/tmp  
11. commit and make image  using this command
	docker commit --change "CMD [\"java\",\"-jar\",\"/tmp/docker-1.jar\"]" musing_ishizaka sunil-app/docker:v2   for windows
	
12. Run container with application
	docker run -p 8080:8080 sunil-app/docker:v2

13. Now make a docker file in springboot maven project
	write this code in Dockerfile

	FROM openjdk:26-ea-29
	WORKDIR /tmp
	ADD target/docker-1.jar docker-1.jar
	ENTRYPOINT ["java","-jar","/tmp/docker-1.jar"]

14. To build dockerfile of Spring boot app  run command
	docker build -t sunil-app/docker:v5 .

15. Run container with application
	docker run -p 8080:8080 sunil-app/docker:v2
	
> java -jar jarfile_path       - to run jar file	

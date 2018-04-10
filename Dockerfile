FROM openjdk:8-jre
EXPOSE 8080
ADD ./dataDashboard /dataDashboard
WORKDIR /dataDashboard
CMD ["java","-jar","./build/libs/dataDashboard.jar"]

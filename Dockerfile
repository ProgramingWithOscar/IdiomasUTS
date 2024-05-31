FROM openjdk:17
COPY "./target/ParcialPruebasSaber-1.jar.original" "app.jar"
EXPOSE 8098
ENTRYPOINT ["java", "-jar","app.jar"]
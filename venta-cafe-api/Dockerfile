####
# Este Dockerfile se usa para construir una imagen de contenedor que ejecuta la aplicación Quarkus en modo JVM
#
# Antes de construir la imagen del contenedor, ejecute:
#
# ./mvnw package
#
# Luego, construya la imagen con:
#
# docker build -f src/main/docker/Dockerfile.jvm -t venta-cafe-api .
#
# Luego ejecute el contenedor usando:
#
# docker run -i --rm -p 8080:8080 venta-cafe-api
#
###
FROM registry.access.redhat.com/ubi8/openjdk-17:1.18

ENV LANGUAGE='en_US:en'

# Configuramos el directorio de trabajo
WORKDIR /work/

# Copiamos el directorio target/quarkus-app
COPY --chown=185 target/quarkus-app/lib/ /work/lib/
COPY --chown=185 target/quarkus-app/*.jar /work/
COPY --chown=185 target/quarkus-app/app/ /work/app/
COPY --chown=185 target/quarkus-app/quarkus/ /work/quarkus/

EXPOSE 8080
USER 185

ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV JAVA_APP_JAR="/work/quarkus-run.jar"

ENTRYPOINT [ "java", "-jar", "/work/quarkus-run.jar" ]

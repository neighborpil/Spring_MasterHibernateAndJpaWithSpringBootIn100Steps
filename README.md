# Spring_MasterHibernateAndJpaWithSpringBootIn100Steps
 - Example codes hands on
 - https://github.com/in28minutes/jpa-with-hibernate


# maven build option
clean install

![image](https://user-images.githubusercontent.com/22423285/170939372-95f0424b-a146-4927-ad9a-7d264c0851f3.png)


## application.properties(로깅 설정)
logging.level.org.springframework=DEBUG

## Spring boot actuator
 - pom.xml
```
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>

```
 - application.properties
```
# actuator
# management.endpoints.web.exposure.include=* #enable all
management.endpoints.web.exposure.include=health,metrics
```

## Spring boot dev tools
 - automatically restart server after code change (only java change)
 - If i change the pom.xml, then I should restart the java application
 - pom.xml
```
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
      <scope>runtime</scope>
		</dependency>

```


## jpa and jdbc

#### H2 datbase setting
 - before starting the h2, you need to configure h2 database setting in application.properties
```
spring.datasource.url=jdbc:h2:mem:testdb;NON_KEYWORDS=USER
spring.h2.console.enabled=true
spring.data.jpa.repositories.bootstrap-mode=default
spring.jpa.defer-datasource-initialization=true
```
 - h2 console url: http://localhost:8080/h2-console


#### Tips
 - To use hibernate, you should create a no args constructor.

#### Tips : show jpa sql code
 - application.properties
```
spring.jpa.show-sql=true
logging.level.org.hibernate.type=debug
```

#### Tips : Turn statistics on
 - application.properties
```
spring.jpa.properties.hibernate.generate_statistics=true
spring.jpa.properties.hibernate.format_sql=true
logging.level.org.hibernate.stat=trace
```

### Junit4 and Junit5 annotation change
![image](https://user-images.githubusercontent.com/22423285/171105428-845981dc-4e21-436b-b134-e0128e7614be.png)


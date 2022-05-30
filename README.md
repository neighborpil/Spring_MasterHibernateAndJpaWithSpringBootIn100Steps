# Spring_MasterHibernateAndJpaWithSpringBootIn100Steps
Example codes hands on


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

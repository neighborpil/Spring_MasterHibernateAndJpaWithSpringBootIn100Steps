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


#### Tips: @DirtiesContext
 - JUnit test annotation
 - spring will automatically reset the used data


#### hibernate method
 - EntityManager.detach() : DB와의 연결을 끊어버림
 - EntityManager.clear() : 모든 entity의 DB와의 연결을 끊어버림

## Mapping

### @OneToOne
 - 1:1
 - should select owing side
 - not owing side should write "mappedBy"
 - default fetch type is **eager**
```
@OneToOne(fetch = FetchType.LAZY, mappedBy="passport")
```

### @OneToMany
 - **1** : n
 - default fetch type is **lazy**
```
	@OneToMany(mappedBy = "course") // default fetchtype = lazy
//	@OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
	private List<Review> reviews = new ArrayList<>();
```

### @ManyToOne
 - 1 : **n**
 - default fetch type is **eager**
```
	@ManyToOne // default: eager fetching
	private Course course;
```
 
### @ManyToMany
 - n : n
 - default fetch type is **lazy**
 - should write join column at owing side

```
	@ManyToMany // default: lazy fetch, I can change it to eager fetch
	@JoinTable(name="STUDENT_COURSE", // owning side can add join table	 
		joinColumns = @JoinColumn(name = "STUDENT_ID"),
		inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
	private List<Course> courses = new ArrayList<>()


	
	@ManyToMany(mappedBy = "courses")
	private List<Student> students = new ArrayList<>()
```

### @Inheritance
 - @Inheritance(strategy = InheritanceType.SINGLE_TABLE) // default strategy: single_table
   If I really **worried about performance**, then I would better **use SINGLE_TABLE** option.
 - @DiscriminatorColumn(name="EmployeeType") // distinguish column, optional
 - @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // table per concrete entity class, it repeats common columns like id, name
 - @Inheritance(strategy = InheritanceType.JOINED) // create child tables and join them
   If I really **concerned about data integrity**, then I would better **use JOINED option**.
 - @MappedSuperclass // cannnot define as a @Entity, and it's not a entity, I should define retrieving subclasses separately, cannot define as Entity
   Child classes doesn't have any connections among them

```
@Getter
@Setter
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // default strategy: single_table
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // table per concrete entity class, it repeats common columns like id, name
//@Inheritance(strategy = InheritanceType.JOINED) // 
@MappedSuperclass // cannnot define as a @Entity, and it's not a entity, I should define retrieving subclasses separately
//@Entity
@DiscriminatorColumn(name="EmployeeType")
public abstract class Employee {
 ...
}
```

## JPQL
```
TypedQuery<Course> createQuery = em.createQuery("Select c from Course c where c.students is empty", Course.class);
List<Course> resultList = createQuery.getResultList();
log.info("Results -> {}", resultList);
```
```
TypedQuery<Course> createQuery = em.createQuery("Select c from Course c where size(c.students) >= 2", Course.class);
TypedQuery<Course> createQuery = em.createQuery("Select c from Course c order by size(c.students) desc", Course.class);
TypedQuery<Student> createQuery = em.createQuery("Select s from Student s where s.passport.number like '%1234%'", Student.class);


```
### Options
LIKE
BETWEEN 10 AND 1000
IS NULL
UPPPER, LOWER, TRIM, LENGTH

JOIN -> Select c, s from Course c JOIN c.students s 
LEFT JOIN -> Select c, s from Course c LEFT JOIN c.students s
CROSS JOIN -> Select c, s from Course c, Student s // 3 and 4 => 3 * 4 = 12 Rows

```
@Test
@Transactional
public void join()  {
	Query query = em.createQuery("Select c, s from Course c JOIN c.students s");
	List<Object[]> resultList = query.getResultList();
	log.info("Results -> {}", resultList.size());

	for(Object[] result : resultList) {
		log.info("Course: {}, Student: {}", result[0], result[1]);
	}

}
```
```
Query query = em.createQuery("Select c, s from Course c LEFT JOIN c.students s");
Query query = em.createQuery("Select c, s from Course c, Student s");
```


















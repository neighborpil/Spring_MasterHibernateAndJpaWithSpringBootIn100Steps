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
 - LIKE
 - BETWEEN 10 AND 1000
 - IS NULL
 - UPPPER, LOWER, TRIM, LENGTH

 - JOIN -> Select c, s from Course c JOIN c.students s 
 - LEFT JOIN -> Select c, s from Course c LEFT JOIN c.students s
 - CROSS JOIN -> Select c, s from Course c, Student s // 3 and 4 => 3 * 4 = 12 Rows

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

## Transaction

### Concepts
1. Dirty read
 - try to read modified data from transaction 2 when transaction 1 doesn't finished
2. Non Repeatable read
 - a transaction tries to retrieve the same data twice and it gets two different values duing the same transactions
3. Phanthom read
 - a transaction gets differenct rows of data in a single transaction because of inserting rows from other transaction


### Transaction types

|                  | Dirty Read | Non Repeatable Read | Phanthom Read |
|------------------|------------|---------------------|---------------|
| Read Uncommitted | Possible   | Possible            | Possible      |
| Read Committed   | Solved     | Possible            | Possible      |
| Repeatable Read  | Solved     | Solved              | Possible      |
| Serializable     | Solved     | Solved              | Solved        |

 - read committed : lock all the values during the transaction (most of the application accept this strategy)
 - Repeatalbe Read : lock all the rows which I read
 - Serializable : lock everything that is matched by this specific query condition on the table

### setting default transaction level at application.properties
```
# transaction strategy(2: read committed)
spring.jpa.properties.hibernate.connection.isolation=2 
```

### setting transaction level using annotation
```
//	@Transactional
	@Transactional(isolation = Isolation.READ_COMMITTED)
//	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
//	@Transactional(isolation = Isolation.REPEATABLE_READ)
//	@Transactional(isolation = Isolation.SERIALIZABLE)
	void retrieveCourseForReviews() {

	}
```

### Criteria Quires examples
```
package com.neighborpil.jpa.hibernate.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.neighborpil.jpa.hibernate.demo.entity.Course;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(classes=DemoApplication.class)
class CriteriaQueryTest {

	@Autowired
	EntityManager em;
	
	@Test
	void all_courses() {
		
		// "SELECT c FROM Course c"
		
		// 1. Use Criteria Builder to create a Criteria Query returning the expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		// 3. Define Predicates etc using Criteria Builder
		
		// 4. Add Predicates etc to the Criteria Query
		
		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		log.info("Typed Query -> {}", resultList);
	}
	
	@Test
	void all_courses_having_100steps() {
		
		// "SELECT c FROM Course c WHERE name like '%100 steps'"
		
		// 1. Use Criteria Builder to create a Criteria Query returning the expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		// 3. Define Predicates etc using Criteria Builder
		Predicate like100steps = cb.like(courseRoot.get("name"), "%100 steps");
		
		// 4. Add Predicates etc to the Criteria Query
		cq.where(like100steps);
		
		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		log.info("Typed Query -> {}", resultList);
	}
	
	@Test
	void all_courses_without_students() {
		
		// "SELECT c FROM Course c WHERE c.students is empty"
		
		// 1. Use Criteria Builder to create a Criteria Query returning the expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		// 3. Define Predicates etc using Criteria Builder
		Predicate studentsIsEmpty = cb.isEmpty(courseRoot.get("students"));
		
		// 4. Add Predicates etc to the Criteria Query
		cq.where(studentsIsEmpty);
		
		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		log.info("Typed Query -> {}", resultList);
	}
	
	@Test
	void join() {
		
		// "SELECT c FROM Course c JOIN c.students s"
		
		// 1. Use Criteria Builder to create a Criteria Query returning the expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		// 3. Define Predicates etc using Criteria Builder
		Join<Object, Object> join = courseRoot.join("students");
		
		// 4. Add Predicates etc to the Criteria Query
		
		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		log.info("Typed Query -> {}", resultList);
	}
	
	
	@Test
	void left_join() {
		
		// "SELECT c FROM Course c JOIN c.students s"
		
		// 1. Use Criteria Builder to create a Criteria Query returning the expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		// 3. Define Predicates etc using Criteria Builder
		Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);
		
		// 4. Add Predicates etc to the Criteria Query
		
		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		log.info("Typed Query -> {}", resultList);
	}
}
```

### Spring Data JPA Repository
 - JpaRepository<T, id>
 
```
package com.neighborpil.jpa.hibernate.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.neighborpil.jpa.hibernate.demo.entity.Course;

public interface CourseSpringDataRepository extends JpaRepository<Course, Long>{

	List<Course> findByName(String name);

	List<Course> findByNameAndId(String name, Long id);
	
	List<Course> findByNameOrderByIdDesc(String name);

	List<Course> deleteByName(String name);
	
	@Query("SELECT c FROM Course c WHERE name LIKE '%100 steps'")
	List<Course> courseWith100StepsInName();

	@Query(value="SELECT * FROM Course c WHERE name LIKE '%100 steps'", nativeQuery=true)
	List<Course> courseWith100StepsInNameUsingNativeQuery();

	@Query(name="query_get_100_courses")
	List<Course> courseWith100StepsInNameUsingNamedQuery();
}

```
 - If I want to connect to rest api directly, I should add another dependency
```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-rest</artifactId>
</dependency>
```
 - If recursive relation matters, I should add JsonIgnre annotation
```

import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonIgnore
@ManyToMany(mappedBy = "courses")
private List<Student> students = new ArrayList<>();
```

## Caching

### First Level Transaction
 - boundary of caching is single transaction
 - Ideal place of transaction is service layer
```
	@Test
	@Transactional
	void findById_firstLevelCacheDemo() {
		Course course = repository.findById(10001L);
		log.info("First Course Retrieved: {}", course);
		Course course1 = repository.findById(10001L); // first level caching occurred. It was done because of same boundary of transaction
		log.info("First Course Retrieved again: {}", course1);
		
		assertEquals("JPA in 50 steps", course.getName());
		assertEquals("JPA in 50 steps", course1.getName());
	}
```

### Second Level Transaction
 - boundary is accross multiple transactions
 - I should define which data will be cached to use second level cache
 - need to add dependency
```
<dependency>
	<groupId>org.hibernate</groupId>
	<artifactId>hibernate-ehcache</artifactId>
</dependency>
```
 - application.properties
```

# Second Level Cache - EhCache
# 1. enable second level cache
spring.jpa.properties.hibernate.cache.use_second_level_cache=true
# 2. specify the caching framework - EhCache
spring.jpa.properties.hibernate.cache.region.factory_class=org.hibernate.cache.ehcache.EhCacheRegionFactory
# 3. Only cache what I tell to cache
spring.jpa.properties.javax.persistence.sharedCache.mode=ENABLE_SELECTIVE
# for debugging
logging.level.net.sf.ehcache=debug
# 4. What data to cache?
```

 - Set classes that I want to cache as cacheable
```
@Cacheable
public class Course {
	...
}
```











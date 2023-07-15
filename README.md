<a name="readme-top"></a>

# The Weather Channel

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#references">References</a></li>
  </ol>
</details>



<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these steps.

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/Galex3/The-Weather-Channel.git
   ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage

<p align="right">(<a href="#readme-top">back to top</a>)</p>



## Notes

### Why did I use a WeatherModel as a DTO (Data Transfer Object) and a WeatherEntity?
According to SonarLint: "On one side, Spring MVC automatically bind request parameters to beans declared as arguments of methods annotated with @RequestMapping. Because of this automatic binding feature, it’s possible to feed some unexpected fields on the arguments of the @RequestMapping annotated methods.
On the other end, persistent objects (@Entity or @Document) are linked to the underlying database and updated automatically by a persistence framework, such as Hibernate, JPA or Spring Data MongoDB.
These two facts combined can lead to malicious attack: if a persistent object is used as an argument of a method annotated with @RequestMapping, it’s possible from a specially crafted user input, to change the content of unexpected fields into the database.
For this reason, using @Entity or @Document objects as arguments of methods annotated with @RequestMapping should be avoided.
In addition to @RequestMapping, this rule also considers the annotations introduced in Spring Framework 4.3: @GetMapping, @PostMapping, @PutMapping, @DeleteMapping, @PatchMapping."



<!-- References -->
## References

* [Consuming a RESTful Web Service](https://spring.io/guides/gs/consuming-rest)
* [Accessing MongoDB Data with REST](https://spring.io/guides/gs/accessing-mongodb-data-rest)
* [Creating API Documentation with Restdocs](https://spring.io/guides/gs/testing-restdocs/)
* [The error-handling-spring-boot-starter library vs Spring 6 ProblemDetail](https://www.wimdeblauwe.com/blog/2022/12/01/the-error-handling-spring-boot-starter-library-vs-spring-6-problemdetail/)
* [Difference Between @NotNull, @NotEmpty, and @NotBlank Constraints in Bean Validation](https://www.baeldung.com/java-bean-validation-not-null-empty-blank)
* [Dependency Injection](https://docs.spring.io/spring-framework/reference/6.1-SNAPSHOT/core/beans/dependencies/factory-collaborators.html)
* [Spring Data MongoDB: Projections and Aggregations](https://www.baeldung.com/spring-data-mongodb-projections-aggregations)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

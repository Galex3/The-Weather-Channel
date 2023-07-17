<a name="readme-top"></a>

![Java CI with Maven](https://github.com/Galex3/The-Weather-Channel/actions/workflows/maven.yml/badge.svg)

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
    <li><a href="#notes">Notes</a></li>
    <li><a href="#references">References</a></li>
  </ol>
</details>



## Getting Started

To get a local copy up and running follow these steps.

### Installation

1. Clone the repo:
   ```sh
   git clone https://github.com/Galex3/The-Weather-Channel.git
   ```
2. Open the Project on your IDE.
3. Make sure you're using JDK 17. Java 11 won't work!
4. Done!

<p align="right">(<a href="#readme-top">back to top</a>)</p>



## Usage

Use the Postman collection on the project's root folder to start querying data!

The database is hosted on the cloud using MongoDB Atlas, and the connection is open to all IP addresses.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



## Notes
Notes for you and me!

### Why NoSQL instead of SQL?
For this specific project, also considering the long-term, data validity and relationships are not a priority. We are using unstructured data,
so we can be flexible with our data models and prioritise performance and scalability.

### What is SensorDataServiceImplNotWorking?
My failed approach at using the tools offered by the DB to do data manipulation at the database-level.
I wanted to make this work, but I wasted too much time trying to make this work, so I did the data manipulation in code.<br>
However, in terms of scalability, I'm not sure if using Aggregations would put more strain on the DB when querying large sets of data,
possibly impacting performance. I'd love to hear your thoughts on this!

### One of the tests fails. Why?
Great question! If I knew the answer, it wouldn't be failing 😁<br>
In all seriousness though, if you can have a look and maybe figure out why, I'd love to discuss it (and learn) in our call!
I tried loads of stuff, but nothing made it work. I believe it's likely to do with the data I'm passing onto the method sensorDataService.getSensorData(),
but I didn't have enough time to look further into it.

### What could be improved?
* I never got to implement the kmh to mph converter due to time constraints. I did the Celsius to Fahrenheit, so the wind speed would follow the same approach.
* A Function<T, R> mapper from WeatherModel to WeatherEntity would be better than using the getters/setters all the time, especially if there are a lot more fields to map.
I know there's BeanUtils.copyProperties(), but I assume creating our own mapper for our custom classes is the best practice?
* Better documentation for the API endpoints, such as using a dependency like Swagger. The provided Postman collection does an OK job of explaining it,
but implementing it in code would be much better.
* More test cases, and having all of them working!

<p align="right">(<a href="#readme-top">back to top</a>)</p>



## References

* [Consuming a RESTful Web Service](https://spring.io/guides/gs/consuming-rest)
* [Accessing MongoDB Data with REST](https://spring.io/guides/gs/accessing-mongodb-data-rest)
* [Creating API Documentation with Restdocs](https://spring.io/guides/gs/testing-restdocs/)
* [The error-handling-spring-boot-starter library vs Spring 6 ProblemDetail](https://www.wimdeblauwe.com/blog/2022/12/01/the-error-handling-spring-boot-starter-library-vs-spring-6-problemdetail/)
* [Difference Between @NotNull, @NotEmpty, and @NotBlank Constraints in Bean Validation](https://www.baeldung.com/java-bean-validation-not-null-empty-blank)
* [Dependency Injection](https://docs.spring.io/spring-framework/reference/core/beans/dependencies/factory-collaborators.html#beans-constructor-injection)
* [Spring Data MongoDB: Projections and Aggregations](https://www.baeldung.com/spring-data-mongodb-projections-aggregations)
* [Testing the Web Layer](https://spring.io/guides/gs/testing-web/)
* [How to Write Doc Comments for the Javadoc Tool](https://www.oracle.com/uk/technical-resources/articles/java/javadoc-tool.html)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

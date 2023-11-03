**New Upgrade ( what's new)**

•	Incorporation of Hibernate Configuration: This latest upgrade introduces Hibernate configuration, streamlining the process of creating the database and its associated tables automatically when launching the project. This simplifies the initial setup significantly.

•	Enriched Service Classes: Service classes have been enhanced to offer reusable, consistent services throughout the entire project. This augmentation contributes to a more modular and efficient system.

•	Database Interaction with DAO Classes: The project now employs DAO (Data Access Object) classes, facilitating seamless interaction with the database. These classes enhance data management and promote a clear separation of concerns.

•	Rectification of Issues: Numerous issues have been addressed and resolved, encompassing concerns related to product images, security, and other aspects of the project. These fixes bolster the overall stability and security of the application.

•	IDE Compatibility: This update ensures compatibility with both IntelliJ IDEA and Eclipse IDEs, affording developers the flexibility to choose their preferred development environment.

•	Comprehensive Code Refinement: The codebase has undergone a comprehensive overhaul, yielding a structure that prioritizes reusability and maintainability. This restructuring aims to improve the overall project organization.

**Important Note:**

It's essential to acknowledge that active development is ongoing in this branch. Consequently, there may be some existing bugs related to endpoints, and work is in progress regarding the cart logic.

**Quickstart**

To kick off your journey with the project, follow these straightforward steps:

•	Clone the Repository: Begin by cloning the project repository to your local environment.

•	Project Setup in Your IDE: Open the project using your Integrated Development Environment (IDE) of choice, with IntelliJ IDEA highly recommended. It's important to ensure that your IDE recognizes the project as a Spring Boot project.

•	IDE Configuration (IntelliJ IDEA Specific): If you are using IntelliJ IDEA, it's essential to set up the project's working directory to enable Spring Boot to locate the views (the actual web pages). Detailed guidance on configuring web directories can be found in the "Web Directories" section within IntelliJ IDEA.

•	Location Matters: Ensure you are in the "JtProject" directory.

•	Database Connection Configuration: Navigate to the application.properties file to configure the database connection. Refer to the "Database" section below for additional information on this step.

•	Project Execution: Run the project by executing the main method found in JtSpringProjectApplication.java.

•	Explore the Web: Open your web browser and access http://localhost:8080/ to explore the project!

•	Login Credentials: If you have previously executed the basedata.sql script on the database, you can use the following credentials to log in as an admin. Otherwise, you'll need to manually create an admin user in the database:

•	Admin Login:
**Username:** root
**Password:** Sania@1060+!

•	Normal User Login:
**Username:** lisa
**Password:** 765


**Database Configuration**

For this project, you have the flexibility to use either MySQL or MariaDB as the underlying database. The process of connecting to the database can be configured by providing the appropriate values in the application.properties file for the following properties:

•	db.url: Set the JDBC connection URL, which should include the IP address of the database server, its port, and the desired database name. If the database does not exist, it will be created (e.g., jdbc:mysql://[ip address of db]:[port of db]/ecommjava?createDatabaseIfNotExist=true).

•	db.username: Specify the database username for authentication.

•	db.password: If applicable, provide the database password for authentication.

Once these configurations are in place, you'll need to populate the database with initial data. This can be achieved by running the basedata.sql script on the database. The specific steps for executing this script may vary depending on the database management tool you are using. You can find guidance on how to do this through online resources, as the process depends on your chosen database access tool.

**Managing Web Directories**

The project's views are situated within the src/main/webapp/views directory. However, for an inexplicable reason, Spring Boot may not automatically recognize this directory. To address this, you'll need to adjust the project's working directory in your Integrated Development Environment (IDE). If you're using IntelliJ IDEA, follow these steps:

•	Click on the "Edit Configurations..." button located in the top right corner of your IDE.


•	Locate the configuration for "JtSpringProjectApplication."

•	Modify the "Working directory" setting. If this option is not immediately visible, select "Modify Options" and choose it from the list of available options.

•	Set the "Working directory" to utilize the $MODULE_WORKING_DIR$ macro.

•	Apply your changes and then confirm by clicking "OK."

With these adjustments in place, when you execute the project, Spring Boot will seamlessly locate the views, ensuring that you encounter a login page when you access http://localhost:8080/ (assuming you haven't logged in previously). This configuration enables the project to function as intended.

# Workflow

- ![image](https://github.com/jaygajera17/E-commerce-project-springBoot/assets/81226571/69951cb7-65e2-4225-8681-2542859aaec6)
  
### Controller
- control the endpoint and also send data to view( we use ModelAndView method)
- ``` @GetMapping("login")
	public String adminlogin() {
		
		return "adminlogin";
	}```
- whenever /login url is hit , src->main->webapp-> adminlogin.jsp file execute
- 
### Models
- represent data as entity and relationship among them.

### View
- receive data from controller and show with frontend.

## Endpoints
- http://localhost:8080/
- http://localhost:8080/register
- http://localhost:8080/admin/products
- http://localhost:8080/admin/customers
- http://localhost:8080/admin/categories
- http://localhost:8080/admin/Dashboard


## Spring Boot

For any information about Spring Boot, here are some useful links!

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.4/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.4/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

## Preview

![image](https://github.com/jaygajera17/E-commerce-project-springBoot/assets/81226571/02a04d3c-1fc9-418c-b231-639f6525d07e)
![image](https://github.com/jaygajera17/E-commerce-project-springBoot/assets/81226571/24c4451b-43a6-4c23-a78a-786eab4303b0)
![image](https://github.com/jaygajera17/E-commerce-project-springBoot/assets/81226571/93c1baeb-326c-450f-867e-a883900a6644)


## Link
- 𝗬𝗼𝘂𝘁𝘂𝗯𝗲 𝗽𝗿𝗼𝗷𝗲𝗰𝘁 𝘃𝗶𝗱𝗲𝗼 𝗪𝗼𝗿𝗸𝗶𝗻𝗴 𝗗𝗲𝗺𝗼 + 𝘀𝘁𝗲𝗽 𝗯𝘆 𝘀𝘁𝗲𝗽 𝗲𝘅𝗲𝗰𝘂𝘁𝗶𝗼𝗻 𝗹𝗶𝗻𝗸  ( old version )::---  [  click here  ](https://youtu.be/c6WWdINWSlI) [![youtube][youtube-shield]][youtube-url]

[youtube-shield]:https://img.shields.io/youtube/views/c6WWdINWSlI?style=social
[youtube-url]:  https://youtu.be/c6WWdINWSlI

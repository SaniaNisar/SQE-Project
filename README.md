# New Upgrade: What's New?

### ✨ Hibernate Configuration:
- This upgrade introduces Hibernate configuration, allowing automatic creation of the database and tables when launching the project. This simplifies the initial setup significantly.
### 🚀 Enhanced Service Classes:
- Service classes have been improved to provide reusable, consistent services throughout the project, contributing to a more modular and efficient system.
### 🗄️ Database Interaction with DAO Classes:
- DAO (Data Access Object) classes are now employed, facilitating seamless interaction with the database. These classes enhance data management and promote a clear separation of concerns.
### 🛠️ Issue Rectifications:
- Numerous issues have been addressed and resolved, including concerns related to product images, security, and other aspects. These fixes bolster the overall stability and security of the application.
### 💻 IDE Compatibility:
- This update ensures compatibility with both IntelliJ IDEA and Eclipse IDEs, giving developers the flexibility to choose their preferred development environment.
### 🧹 Comprehensive Code Refinement:
- The codebase has undergone a thorough overhaul, prioritizing reusability and maintainability. This restructuring aims to improve overall project organization.

##### Important Note:
Active development is ongoing in this branch. There may be existing bugs related to endpoints, and work is in progress regarding the cart logic.

## Quickstart
- To kick off your journey with the project, follow these straightforward steps:

## Clone the Repository:
- Begin by cloning the project repository to your local environment.

- Project Setup in Your IDE:
Open the project using your Integrated Development Environment (IDE) of choice, with IntelliJ IDEA highly recommended. Ensure your IDE recognizes the project as a Spring Boot project.

- IDE Configuration (IntelliJ IDEA Specific):
  If using IntelliJ IDEA, set up the project's working directory to enable Spring Boot to locate the views (the actual web pages). Detailed guidance on configuring web directories can be found in the "Web Directories" section within IntelliJ IDEA.

- Location Matters:
Ensure you are in the "JtProject" directory.

- Database Connection Configuration:
Navigate to the application.properties file to configure the database connection. Refer to the "Database" section below for more information.

- Project Execution:
Run the project by executing the main method found in JtSpringProjectApplication.java.

- Explore the Web:
Open your web browser and access http://localhost:8080/ to explore the project!

- Login Credentials:
If you have previously executed the basedata.sql script on the database, you can use the following credentials to log in as an admin. Otherwise, you'll need to manually create an admin user in the database:

- Admin Login:
* Username: root
* Password: Sania@1060+!
- Normal User Login:
* Username: lisa
* Password: 765

- Database Configuration
For this project, you can use either MySQL or MariaDB as the underlying database. Configure the database connection by providing the appropriate values in the application.properties file for the following properties:

- db.url: Set the JDBC connection URL, which should include the IP address of the database server, its port, and the desired database name. If the database does not exist, it will be created (e.g., jdbc:mysql://[ip address of db]:[port of db]/ecommjava?createDatabaseIfNotExist=true).

- db.username: Specify the database username for authentication.

- db.password: If applicable, provide the database password for authentication.

Once these configurations are in place, populate the database with initial data by running the basedata.sql script on the database. The specific steps for executing this script may vary depending on the database management tool you are using.

## Managing Web Directories
The project's views are located in the src/main/webapp/views directory. To ensure Spring Boot recognizes this directory, adjust the project's working directory in your IDE. If using IntelliJ IDEA, follow these steps:

Click on the "Edit Configurations..." button in the top right corner of your IDE.
Locate the configuration for "JtSpringProjectApplication."
Modify the "Working directory" setting. If not immediately visible, select "Modify Options" and choose it from the list.
Set the "Working directory" to use the $ MODULE_WORKING_DIR$ macro.
Apply your changes and confirm by clicking "OK."
With these adjustments, when you execute the project, Spring Boot will locate the views, ensuring you see the login page when accessing http://localhost:8080/ (assuming you haven't logged in previously).

## Example
## Workflow
#### Controller
- Controls the endpoint and sends data to the view (using the ModelAndView method).
- @GetMapping("login")
	public String adminlogin() {
		
		return "adminlogin";
	}

- whenever /login url is hit , src->main->webapp-> adminlogin.jsp file execute

#### Models
- Represent data as entities and the relationships among them.
#### View
- Receives data from the controller and displays it with the frontend.

## Endpoints
- http://localhost:8080/
- http://localhost:8080/register
- http://localhost:8080/admin/products
- http://localhost:8080/admin/customers
- http://localhost:8080/admin/categories
- http://localhost:8080/admin/Dashboard

## Reference Documentation
* Official Apache Maven documentation
* Spring Boot Maven Plugin Reference Guide
* Create an OCI image
* Spring Web

## Guides
- Building a RESTful Web Service
- Serving Web Content with Spring MVC
- Building REST services with Spring

## Preview

![image](https://github.com/SaniaNisar/SQE-Project/assets/102464125/d120d1ae-4092-485e-89ad-e1468722456c)
![image](https://github.com/SaniaNisar/SQE-Project/assets/102464125/ec843ab7-1e7d-41de-938d-201776138ef7)
![image](https://github.com/SaniaNisar/SQE-Project/assets/102464125/e346a981-cb78-4c8b-99fd-7873742ec297)


## Link
- 𝗬𝗼𝘂𝘁𝘂𝗯𝗲 𝗽𝗿𝗼𝗷𝗲𝗰𝘁 𝘃𝗶𝗱𝗲𝗼 𝗪𝗼𝗿𝗸𝗶𝗻𝗴 𝗗𝗲𝗺𝗼 + 𝘀𝘁𝗲𝗽 𝗯𝘆 𝘀𝘁𝗲𝗽 𝗲𝘅𝗲𝗰𝘂𝘁𝗶𝗼𝗻 𝗹𝗶𝗻𝗸  ( old version )::
-  [  click here  ](https://youtu.be/c6WWdINWSlI) [![youtube][youtube-shield]][youtube-url]

[youtube-shield]:https://img.shields.io/youtube/views/c6WWdINWSlI?style=social
[youtube-url]:  https://youtu.be/c6WWdINWSlI

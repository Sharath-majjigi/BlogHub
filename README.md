A complete Blog App built with spring boot and spring security.</br>


# Design Document of BlogHub

## Index:
1. [Requirements](#1-requirements)
2. [Data Model | Database Design](#2-data-model--database-design)
3. [APIs](#3-apis)
4. [Technology Used](#4-technology-used)

## 1. Requirements:

- **User Registration and Login:**
  - New users can register, and existing users can log in to BlogHub.

- **Blog/Article Creation:**
  - Users can articulate their knowledge/thoughts by writing blogs/articles visible to all BlogHub users.

- **Post Operations:**
  - Create, update, delete, and get a list of posts.

- **Comment Operations:**
  - Add, update, and delete comments on a user's posts and other users' posts.

- **Categorization:**
  - Each post should belong to at least one category. Selecting a category displays a list of posts in that category.

## 2. Data Model | Database Design:

- **User Model**
- **Post Model**
- **Comment Model**
- **Category Model**
- **RoleType Model**
</br>

![DB design](https://i.ibb.co/L8KsHt5/bloghub2.png)

*Description:*
- One user can write and publish many posts → User has a OneToMany relation with Post.
- A post can have many comments → Post has a OneToMany relation with Comment.
- A post at most belongs to one Category → Post has a ManyToOne relation with Category.
- One user can have multiple roles → User has a ManyToMany relation with RoleType.

## 3. APIs:

### User Operations:

| HTTP Method | API Endpoint       | Description                       |
|-------------|--------------------|-----------------------------------|
| POST        | /register          | Register a new user.              |
| POST        | /login             | Log in an existing user.          |
| POST        | /createUser        | Create a new user profile.        |
| PUT         | /updateUser        | Update user information.          |
| GET         | /getUserDetails    | Retrieve detailed user information|
| DELETE      | /deleteUser        | Delete a user account.            |
| GET         | /getUserById       | Retrieve user information by ID.  |
| GET         | /getAllUsers       | Retrieve a list of all users.     |

### Post Operations:

| HTTP Method | API Endpoint             | Description                       |
|-------------|--------------------------|-----------------------------------|
| POST        | /createPost              | Create a new blog post.           |
| GET         | /getAllPostOfUserById    | Retrieve all posts of a user.     |
| GET         | /getAllPostOfCategory    | Retrieve all posts of a category. |
| GET         | /getAllPost              | Retrieve a list of all blog posts.|
| PUT         | /updatePost              | Update an existing blog post.     |
| DELETE      | /deletePost              | Delete a blog post.               |

### Comment Operations:

| HTTP Method | API Endpoint          | Description                         |
|-------------|-----------------------|-------------------------------------|
| POST        | /createComment        | Add a new comment to a blog post.   |
| DELETE      | /deleteComment        | Delete a comment.                   |

### Category Operations:

| HTTP Method | API Endpoint          | Description                         |
|-------------|-----------------------|-------------------------------------|
| POST        | /createCategory       | Create a new category.              |
| GET         | /getCategoryById      | Retrieve category information by ID.|
| GET         | /getAllCategories     | Retrieve a list of all categories.  |
| GET         | /getAllPostOfCategoryById| Retrieve all posts of a category.|
| PUT         | /updateCategory       | Update category information.        |
| DELETE      | /deleteCategory       | Delete a category.                  |

*All APIs are RESTful, and unit tests should be written to ensure application stability as it grows.*

## 4. Technology Used:

- **Java JDK 8**
- **Spring Boot**
- **IntelliJ IDEA**
- **Apache Tomcat (Embedded Server)**
- **Spring Core, Spring Security, Spring Data JPA (Hibernate)**
- **PostgreSQL Database**
- **Git VCS**
- **Postman Rest Client**
- **Swagger 2.0 for API Documentation**
- **Amazon Web Services (AWS) for Deployment**
  - EC2, S3, RDS
- **JWT for Authentication**
- **Additional:**
  - Create a Docker image, push the image to DockerHub, and deploy it into a Kubernetes Cluster.
</br>

*Followed Spring MVC Architecture.*

![Spring Mvc](https://i.ibb.co/zXzkMFr/bloghub1.png)

<!-- **All the Apis with Swagger Documentation available here:**
http://44.203.172.83:8081/swagger-ui/index.html -->


<!-- https://user-images.githubusercontent.com/67635598/177391025-9b91911d-9b5f-4343-a83a-5ef5a44406f3.mp4 -->





# Blueprint Application :rocket:

Blueprint is a new Project Management tool. It can be thought of as a new-age JIRA. :fire:

## Requirements

- [JAVA 11+](https://www.oracle.com/in/java/technologies/javase/jdk11-archive-downloads.html)
- [Gradle 6.8.x](https://gradle.org/install/) and above

## Usage

- Clone the code to your machine.
- ```cd``` to the root of the project and run ```gradle build```
- You can run the project in the embedded tomcat inside your IDE and run the ```jar``` inside the ```build/libs/``` directory.
## API Reference

### 1. User Story

#### Get all user stories

```http
  GET /api/v1/userstories
```

#### Get user story by id

```http
  GET /api/v1/userstories/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long` | **Required**. Id of story to fetch |

#### Create user story

```http
  POST /api/v1/userstories
```

| Body | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `JSON` | **Required**. JSON describing the story to create |

#### Update user story

```http
  PUT /api/v1/userstories/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long` | **Required**. Id of story to update |
| `Request Body`      | `JSON` | **Required**. JSON describing the story to create |

#### Get user story by id

```http
  DELETE /api/v1/userstories/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long` | **Required**. Id of story to delete |


### 2. Tasks

#### Get all tasks

```http
  GET /api/v1/tasks
```

#### Add a new task

```http
  POST /api/v1/tasks/${id}
```

| Body | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long` | **Required**. Id of story to add the task to |
| `Request Body`      | `JSON` | **Required**. JSON describing the story to create |

#### Delete a task

```http
  DELETE /api/v1/userstories/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long` | **Required**. Id of story to delete |

### 3. Users

#### Get all users

```http
  GET /api/v1/users
```

#### Get user by id

```http
  GET /api/v1/users/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long` | **Required**. Id of the user to fetch |

#### Add a new user

```http
  POST /api/v1/users
```

| Body | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Request Body`      | `JSON` | **Required**. JSON describing the user to create |

#### Delete a task

```http
  DELETE /api/v1/users/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long` | **Required**. Id of the user to delete |


## Screenshots

![Create User Story](/src/main/resources/static/screenshots/CREATE_User-Story.jpg?raw=true)
![Get all user stories](/src/main/resources/static/screenshots/GET_all_user-stories.jpg?raw=true)
![Update user story](/src/main/resources/static/screenshots/UPDATE_user-story.jpg?raw=true)
![Create new task](/src/main/resources/static/screenshots/CREATE_new_task.jpg?raw=true)
![Delete task](/src/main/resources/static/screenshots/DELETE_task.jpg?raw=true)
![Get all users](/src/main/resources/static/screenshots/GET_all_users.jpg?raw=true)
![Create New User](/src/main/resources/static/screenshots/CREATE_new_user.jpg?raw=true)

## License

[MIT](https://choosealicense.com/licenses/mit/)


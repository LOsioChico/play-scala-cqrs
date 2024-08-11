# Play Scala CQRS

This is a simple example of a CQRS implementation using Play Framework and Scala.

## Requirements

- Java (I'm using Java 17)
- sbt
- Docker
- Hoppscotch (or any other API client)

## PostgreSQL CQRS

This project uses PostgreSQL as the database, to run it you need to have Docker installed and run the following command:

```bash
  $ docker compose -f ./postgresql/compose.yml up -d
```

This will start a PostgreSQL instance on port 5432.
Then you need to create the database and the tables, you can do this by running the following command:

```bash
  $ docker exec -i postgresql-cqrs psql -U postgres -d postgres < ./postgresql/init.sql
```

Now you can run the Play application:

```bash
  $ sbt "run postgresql"
```

The application will start on port 9000.

### Endpoints

<!-- ## Every project has the same endpoints, but they have different implementations. -->

---

- `POST /post` - Create a new post `{"content": "post content"}`
- `POST /post/:postId/comment` - Create a new comment for a post `{"content": "comment content"}`
- `POST /post/:postId/comment/:commentId/reaction` - Create a new reaction for a comment `{"emoji": "👍"}`

---

- `GET /post/:id` - Get a post by id
- `GET /posts` - Get all posts

---

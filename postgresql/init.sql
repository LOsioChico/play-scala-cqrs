DROP TABLE IF EXISTS posts;
CREATE TABLE posts (
  id SERIAL PRIMARY KEY,
  content TEXT NOT NULL
);

DROP TABLE IF EXISTS comments;
CREATE TABLE comments (
  id SERIAL PRIMARY KEY,
  content TEXT NOT NULL,
  post_id INT NOT NULL
);

DROP TABLE IF EXISTS reactions;
CREATE TABLE reactions (
  id SERIAL PRIMARY KEY,
  emoji TEXT NOT NULL,
  comment_id INT NOT NULL,
  post_id INT NOT NULL
);
# https://javagit2heroku.herokuapp.com/api/save/{id}

## /api/save/{key}
```
POST https://javagit2heroku.herokuapp.com/api/save/test HTTP/1.1
Content-Type: application/json

{
    "key" : "test",
    "value" : "test"
}
```

## /hangul/syllable
```
POST https://javagit2heroku.herokuapp.com/hangul/syllable HTTP/1.1
Content-Type: application/json

{
    "CHARACTERS" : "ㅂㅑㄷ"
}
```

## Running Locally

Make sure you have Java and Maven installed.  Also, install the [Heroku CLI](https://cli.heroku.com/).

```sh
$ git clone https://github.com/heroku/java-getting-started.git
$ cd java-getting-started
$ mvn install
$ heroku local:start
```

Your app should now be running on [localhost:5000](http://localhost:5000/).

If you're going to use a database, ensure you have a local `.env` file that reads something like this:

```
JDBC_DATABASE_URL=jdbc:postgresql://localhost:5432/java_database_name
```

## Deploying to Heroku

```sh
$ heroku create
$ git push heroku master
$ heroku open
```

## Documentation

For more information about using Java on Heroku, see these Dev Center articles:

- [Java on Heroku](https://devcenter.heroku.com/categories/java)

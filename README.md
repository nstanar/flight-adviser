# Flight Adviser API

Flight adviser API consists of two modules:

- User management (Operates on "in memory" h2 database)

- Flight Management (Operates on external neo4j)

For user management one admin is pre-inserted with credentials (admin:admin123).

To start Neo4j for Flight Management module use following command:

```
docker run \                                                                                                                                ─╯
    --publish=7474:7474 --publish=7687:7687 \
    --volume=$HOME/neo4j/data:/data --env=NEO4J_AUTH=none \
    neo4j
```

For API requests use Postman collection in root of this project.



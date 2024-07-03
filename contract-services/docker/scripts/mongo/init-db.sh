#!/bin/bash

echo "Waiting for MongoDB to be ready..."
until mongo --eval "print(\"waited for connection\")"
do
    sleep 1
done

echo "MongoDB is ready. Initializing 'contract' database..."

mongo admin -u root -p example <<EOF
use contract
db.createUser({
  user: 'contract_user',
  pwd: 'contract_password',
  roles: [{ role: 'readWrite', db: 'contract' }]
})
EOF

echo "'contract' database initialized."

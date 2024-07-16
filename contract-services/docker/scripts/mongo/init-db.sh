#!/bin/bash

# Conectar ao banco de dados admin para autenticação inicial
mongo admin --eval "db.auth('admin-user', 'admin-password');"

# Selecionar o banco de dados services e criar usuário
mongo services --eval "
  db.createUser({
    user: 'services-user',
    pwd: 'services-password',
    roles: [{ role: 'readWrite', db: 'services' }]
  });
"

# Criar a coleção services_contract
mongo services --eval "db.createCollection('services_contract');"
mongo services --eval "db.createCollection('services_product');"

echo "Coleção [services_contract | services_product ] criadas com sucesso."

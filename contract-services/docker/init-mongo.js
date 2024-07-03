// init-mongo.js

print("Waiting for MongoDB to be ready...");

// Função para esperar até que o MongoDB esteja pronto
function waitForMongo() {
    try {
        var result = db.adminCommand('ping');
        return result.ok === 1;
    } catch (e) {
        return false;
    }
}

// Esperar até que o MongoDB esteja pronto para aceitar conexões
while (!waitForMongo()) {
    sleep(1000); // Espera por 1 segundo
}

print("MongoDB is ready. Initializing 'services' database...");

// Usar o banco de dados 'services'
var db = db.getSiblingDB('admin');

// Criar a coleção 'contract' dentro do banco de dados 'services'
db.createCollection('contract');

print("'services' database initialized.");

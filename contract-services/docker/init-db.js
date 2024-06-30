print("Starting MongoDB setup script...");

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
    sleep(10000); // Espera por 10 segundos
}

print("MongoDB is ready. Setting up users and databases...");

// Autenticar no banco de admin
db = db.getSiblingDB('admin');
db.auth('admin', 'senha_admin');

// Criar usuário no banco de admin
db.createUser({
    user: 'contract_user',
    pwd: 'contract_password',
    roles: [
        {
            role: 'readWrite',
            db: 'contract',
        },
    ],
});

print("User 'contract_user' created.");

// Mudar para o banco de dados 'contract'
db = db.getSiblingDB('contract');

// Criar a coleção 'contracts'
db.createCollection("contracts");
//
//print("Collection 'contracts' created.");
//
//print("Setup complete.");

//print("Starting MongoDB setup script...");
//
//// Verificar a conexão com o MongoDB
//db = db.getSiblingDB('admin');
//var result = db.runCommand({ ping: 1 });
//printjson(result);
//
//print("Finished MongoDB setup script.");

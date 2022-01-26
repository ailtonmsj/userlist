# Userlist Project

Projeto baseado em Quarkus para possibilitar a listagem de usuarios. 

## Executar a aplicação in dev mode

Executando com live coding:
```shell script
./mvnw compile quarkus:dev
```

# Build da Imagem Docker
```
mvn package
docker build -f src/main/docker/Dockerfile.jvm -t <SEU-DOCKERHUB-ID>/userlist-jvm:1.0.0 .
docker push <SEU-DOCKERHUB-ID>/userlist-jvm:1.0.0
```


# Testando a aplicação:
```shell script
curl --location --request GET 'http:\<DNS\>/usuario'
```

## Aplicar os manifestos presentes em "src/main/kubernetes" para o deploy ocorrer:
## Importante:
- Necessário uma Infra com o Service Mesh
- Alterar a imagem existente no arquivo 02.deployament.yaml
- Se ambiente de execução AWS deve ter acesso nas tabelas do DynamoDB
- Link do terraform para criação da infra: https://github.com/ailtonmsj/aws-new-stack
```
...
containers:
      - image: <SEU-DOCKERHUB-ID>/userlist-jvm:1.0.0
        name: userlist-jvm
...
```

### Executar:
```
kubectl apply -f src/main/kubernetes
```

# Testes locais

### Container para simular dynamodb:
```
docker run --publish 8000:8000 amazon/dynamodb-local:1.11.477 -jar DynamoDBLocal.jar -inMemory -sharedDb
```

### Acessar no browser
```
http://localhost:8000
```


### Schema de banco de dados:
```
var params = {
    TableName: 'AwsNewStackUsuario',
    KeySchema: [{ AttributeName: 'nome', KeyType: 'HASH' }],
    AttributeDefinitions: [{  AttributeName: 'nome', AttributeType: 'S', }],
    ProvisionedThroughput: { ReadCapacityUnits: 1, WriteCapacityUnits: 1, }
};

dynamodb.createTable(params, function(err, data) {
    if (err) ppJson(err);
    else ppJson(data);
});
```

## Alterar o applications.properties para esse valor de acordo com o ambiente do dynamodb:

### Esses valores são para testes locais
```
quarkus.dynamodb.endpoint-override=http://localhost:8000

quarkus.dynamodb.aws.region=eu-central-1
quarkus.dynamodb.aws.credentials.type=static
quarkus.dynamodb.aws.credentials.static-provider.access-key-id=test-key
quarkus.dynamodb.aws.credentials.static-provider.secret-access-key=test-secret
```


### Esses valores são para uso com o AWS DynamoDB
```
quarkus.dynamodb.aws.region=<USAR-A-LOCALIZACAO-DO-DYNAMODB>
quarkus.dynamodb.aws.credentials.type=default
```

### SmallRye Health

Monitor your application's health using SmallRye Health

[Related guide section...](https://quarkus.io/guides/smallrye-health)
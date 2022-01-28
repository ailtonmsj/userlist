# UserUpdate Project

Projeto baseado em Quarkus para possibilitar a atualização de usuarios. 

<br >

# Executar a aplicação localmente (in dev mode):

Executando com live coding:
```shell script
./mvnw compile quarkus:dev
```

### Container para simular dynamodb:
```
docker run --publish 8000:8000 amazon/dynamodb-local:1.11.477 -jar DynamoDBLocal.jar -inMemory -sharedDb
```

### Acessar no browser
```
http://localhost:8000/shell/
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

<br >

# URL para teste (cURL):
```shell script
curl --location --request GET 'http:\<DNS\>/usuario'
```

## Exemplo:
```
```shell script
curl --location --request GET 'http:localhost:8091/usuario'
```
<br >

### IMPORTANTE: Para o profile de dev (quarkus:dev) a porta utilizada para essa aplicação é 8091, porém o default para acesso é 8080 como serviço, e se aplicado no cluster o manifesto kubernetes e a porta 80.

<br >
<br >

# Para executar em Cluster com Service Mesh:

## Build da Imagem Docker
```
mvn package
docker build -f src/main/docker/Dockerfile.jvm -t <SEU-DOCKERHUB-ID>/userlist-jvm:1.0.0 .
docker push <SEU-DOCKERHUB-ID>/userlist-jvm:1.0.0
```

## Aplicar os manifestos presentes em "src/main/kubernetes" para o deploy ocorrer:
### Importante:
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

## Alterar o applications.properties para esse valor de acordo com o ambiente do dynamodb:

### Esses valores são para uso com o AWS DynamoDB
```
quarkus.dynamodb.aws.region=<USAR-REGION-DO-DYNAMODB>
quarkus.dynamodb.aws.credentials.type=default
```

<br >

# Códigos de Resposta

### 200 - OK - Usuario Adicionado com Sucesso
### 400 - Bad Request - Rever parametros de entrada
### 500 - Server Erro - Erro no Servidor de aplicação

<br />

# Aplicações Relacionadas:

### https://github.com/ailtonmsj/userupdate
### https://github.com/ailtonmsj/useradd

<br />

### SmallRye Health

Monitor your application's health using SmallRye Health

[Related guide section...](https://quarkus.io/guides/smallrye-health)
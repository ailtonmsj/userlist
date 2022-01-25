package br.com.ailtonmsj.service;

import java.util.HashMap;
import java.util.Map;

import br.com.ailtonmsj.dto.Usuario;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;

public abstract class AbstractUsuarioService {
	
	public static final String USER_TABLE = "AwsNewStackUsuario";

    //public static final String USUARIO_ID_COL = "id";
    public static final String USUARIO_NOME_COL = "nome";
    public static final String USUARIO_SOBRENOME_COL = "sobrenome";
    public static final String USUARIO_IDADE_COL = "idade";
    public static final String USUARIO_PAIS_COL = "pais";
    public static final String USUARIO_STATUS_COL = "status";
    

    public String getTableName() {
        return USER_TABLE;
    }

    protected ScanRequest scanRequest() {
        return ScanRequest.builder().tableName(getTableName())
                .attributesToGet(
      //          		USUARIO_ID_COL, 
                		USUARIO_NOME_COL, 
                		USUARIO_SOBRENOME_COL, 
                		USUARIO_IDADE_COL, 
                		USUARIO_PAIS_COL,
                		USUARIO_STATUS_COL).build();
    }

    protected PutItemRequest putRequest(Usuario usuario) {
        Map<String, AttributeValue> item = new HashMap<>();
        //item.put(USUARIO_ID_COL, AttributeValue.builder().n(usuario.getId().toString()).build());
        item.put(USUARIO_NOME_COL, AttributeValue.builder().s(usuario.getNome()).build());
        item.put(USUARIO_SOBRENOME_COL, AttributeValue.builder().s(usuario.getSobrenome()).build());
        item.put(USUARIO_IDADE_COL, AttributeValue.builder().n(usuario.getIdade().toString()).build());
        item.put(USUARIO_PAIS_COL, AttributeValue.builder().s(usuario.getPais()).build());
        if(usuario.getStatus() != null) {
        	item.put(USUARIO_PAIS_COL, AttributeValue.builder().bool(usuario.getStatus()).build());
        }

        return PutItemRequest.builder()
                .tableName(getTableName())
                .item(item)
                .build();
    }

//    protected GetItemRequest getRequest(Integer id) {
//        Map<String, AttributeValue> key = new HashMap<>();
//        key.put(USUARIO_ID_COL, AttributeValue.builder().n(id.toString()).build());
//
//        return GetItemRequest.builder()
//                .tableName(getTableName())
//                .key(key)
//                .attributesToGet(
//                		USUARIO_ID_COL, 
//                		USUARIO_NOME_COL, 
//                		USUARIO_SOBRENOME_COL, 
//                		USUARIO_IDADE_COL, 
//                		USUARIO_PAIS_COL,
//                		USUARIO_STATUS_COL)
//                .build();
//    }
    
    protected GetItemRequest getRequest(String name) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put(USUARIO_NOME_COL, AttributeValue.builder().s(name).build());

        return GetItemRequest.builder()
                .tableName(getTableName())
                .key(key)
                .attributesToGet( 
                		USUARIO_NOME_COL, 
                		USUARIO_SOBRENOME_COL, 
                		USUARIO_IDADE_COL, 
                		USUARIO_PAIS_COL,
                		USUARIO_STATUS_COL)
                .build();
    }    
    
}

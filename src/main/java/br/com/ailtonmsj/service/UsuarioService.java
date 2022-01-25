package br.com.ailtonmsj.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.ailtonmsj.dto.Usuario;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@ApplicationScoped
public class UsuarioService extends AbstractUsuarioService {
	
    @Inject
    DynamoDbClient dynamoDB;
    
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    public List<Usuario> findAll() {
        return dynamoDB.scanPaginator(scanRequest()).items().stream()
                .map(Usuario::from)
                .collect(Collectors.toList());
    }

    public List<Usuario> add(Usuario usuario) {
    	
    	log.info("Preparando para adicinar usuario - " + usuario);
    	
        dynamoDB.putItem(putRequest(usuario));
        
        //dynamoDB.putItem(putRequest(usuario)).
        
        return findAll();
    }

//    public Usuario get(Integer id) {
//        return Usuario.from(dynamoDB.getItem(getRequest(id)).item());
//    }
    
    public Usuario get(String nome) {
        return Usuario.from(dynamoDB.getItem(getRequest(nome)).item());
    }
}

package br.com.ailtonmsj.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.ailtonmsj.dto.Usuario;
import br.com.ailtonmsj.service.UsuarioService;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioList {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Inject
	private UsuarioService usuarioService;

    @GET
    public List<Usuario> getAll() {
    	log.info("Listando usuarios");
    	
    	List<Usuario> usuarios = usuarioService.findAll();
		
    	for(Usuario usuario : usuarios) {
    		log.info("listando usuarios " + usuario);
    	}
    	
		return usuarios;
    }
    
//    @GET
//    @Path("{id}")
//    public Usuario getSingle(@PathParam("id") Integer id) {
//    	
//    	log.info("Obtendo usuario id " + id);
//    	
//        return usuarioService.get(id);
//    }
    
    @GET
    @Path("{nome}")
    public Usuario getSingle(@PathParam("id") String nome) {
    	
    	log.info("Obtendo usuario por nome " + nome);
    	
        return usuarioService.get(nome);
    }

    @POST
    public List<Usuario> add(Usuario usuario) {
    	
    	log.info("adicionando usuário" + usuario);
    	
    	return usuarioService.add(usuario);
    }
}
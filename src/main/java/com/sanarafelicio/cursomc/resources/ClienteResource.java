package com.sanarafelicio.cursomc.resources;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sanarafelicio.cursomc.domain.Cliente;
import com.sanarafelicio.cursomc.dto.ClienteDTO;
import com.sanarafelicio.cursomc.dto.ClienteNewDTO;
import com.sanarafelicio.cursomc.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Cliente obj = service.find(id);		
		return ResponseEntity.ok().body(obj);			
		}
	
	//inserir um cliente
		@RequestMapping(method=RequestMethod.POST) //@RequestBidy faz o Json ser convertido para obj java
		public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto){
			Cliente obj = service.fromDTO(objDto);
			obj = service.insert(obj);
			//pegando o id da categoria q fio inserido adicionar a url e converter pra uri
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).build();		
		}
	
	//Atualizando um cliente
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto,@PathVariable Integer id){
		Cliente obj = service.fromDTO(objDto);
		obj.setId(id);
		obj= service.update(obj);
		return ResponseEntity.noContent().build();			
	}
	
	//Deletando uma Cliente
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();		
	}

	//listando todas os clientes
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {
			List<Cliente> list = service.findAll();
			//transformando a lista de cliente para uma clienteDto
			List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
			return ResponseEntity.ok().body(listDto);			
		}
	
	//listando todas os clientes com paginação
		@RequestMapping(value="/page",method=RequestMethod.GET)
		public ResponseEntity<Page<ClienteDTO>> findPage(
				@RequestParam(value="page", defaultValue="0") Integer page, 
				@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
				@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
				@RequestParam(value="direction", defaultValue="ASC") String direction) {
				
				Page<Cliente> list = service.findPage(page, linesPerPage, orderBy,direction);
				//transformando a Page de cliente para uma page de ClienteDTO
				Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
				return ResponseEntity.ok().body(listDto);			
			}

}

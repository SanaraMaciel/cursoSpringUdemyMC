package com.sanarafelicio.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanarafelicio.cursomc.domain.Cidade;
import com.sanarafelicio.cursomc.domain.Cliente;
import com.sanarafelicio.cursomc.domain.Endereco;
import com.sanarafelicio.cursomc.domain.enums.TipoCliente;
import com.sanarafelicio.cursomc.dto.ClienteDTO;
import com.sanarafelicio.cursomc.dto.ClienteNewDTO;
import com.sanarafelicio.cursomc.repositories.ClienteRepository;
import com.sanarafelicio.cursomc.repositories.EnderecoRepository;
import com.sanarafelicio.cursomc.services.exceptions.DataIntegrityException;
import com.sanarafelicio.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	//declarando uma dependência do objeto cliente repository para poder fazer os métodos
	//O @Autowired é uma dependência q faz com q seja carregado automaticamente pelo spring 
	@Autowired
	private ClienteRepository repo;
	
	@Autowired 
	private EnderecoRepository enderecoRepository;
		
	public Cliente find(Integer id) {
		Cliente obj = repo.findOne(id);
		if (obj == null) {
		throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
		+ ", Tipo: " + Cliente.class.getName());
		}
		return obj;
		
	}
	
	//insert
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.save(obj.getEnderecos());
		return obj;
	}
	
	//update
		public Cliente update(Cliente obj) {
			Cliente newObj = find(obj.getId());
			//método auxiliar Update Data p/ atualizar os dadso desse nojo obj com base no obj q veio como argumento
			updateData(newObj, obj);			
			return repo.save(newObj);			
		}
		
		//delete
		public void delete(Integer id) {
			find(id);
			try {
				repo.delete(id);
			}catch(DataIntegrityViolationException e) {
				throw new DataIntegrityException("Não é possível excluir um cliente que possui pedidos"); 
			}
		}
		
		//Listar todas os clientes
		public List<Cliente> findAll(){
			return repo.findAll();
		}
		
		//Adicionando Paginação 
		public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
			PageRequest pageRequest = new PageRequest(page, linesPerPage,Direction.valueOf(direction),orderBy);
			return repo.findAll(pageRequest);		
		}
		
		//método auxiliar para converter de ClienteDTO para Cliente instancia uma categoria a partir de um DTO
		public Cliente fromDTO(ClienteNewDTO objDto) {
			Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.ToEnum(objDto.getTipo()));
			Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
			Endereco end = new Endereco(null, objDto.getLongradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
			cli.getEnderecos().add(end);
			cli.getTelefones().add(objDto.getTelefone1());
			if (objDto.getTelefone2()!=null) {
				cli.getTelefones().add(objDto.getTelefone2());
			}
			if (objDto.getTelefone3()!=null) {
				cli.getTelefones().add(objDto.getTelefone3());
			}
			return cli;
		}
		
		public Cliente fromDTO(ClienteDTO objDto) {
			return new Cliente(objDto.getId(),objDto.getNome(),objDto.getEmail(),null,null);	
		}
		
		
		//método auxiliar Update data para salvar apenas os campos desejados em uma tabela relacionamento
		//atualizando os dados do newObj com os dados q vieram do obj
		private void updateData(Cliente newObj, Cliente obj ) {
			newObj.setNome(obj.getNome());
			newObj.setEmail(obj.getEmail());			
		}
		
}

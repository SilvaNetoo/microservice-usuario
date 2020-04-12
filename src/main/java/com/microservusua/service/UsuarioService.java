package com.microservusua.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservusua.domain.Usuario;
import com.microservusua.dto.ProdutoDTO;
import com.microservusua.exceptions.ObjectNotFoundException;
import com.microservusua.repositories.CarrinhoFeignClient;
import com.microservusua.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;

	@Autowired
	private CarrinhoFeignClient carrinhoFeignClient;

	public List<Usuario> buscarTodos() {
		return repo.findAll();
	}

	public Usuario buscar(Integer id) {
		Optional<Usuario> prod = repo.findById(id);
		return prod.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}

	public Usuario buscarEmailSenha(String email, String senha) {
		Usuario obj = repo.findUser(email, senha);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		return obj;
	}

	public Usuario inserirProduto(Integer idUser, ProdutoDTO prod) {
		Usuario obj = buscar(idUser);
		carrinhoFeignClient.inserirProduto(prod.getId(), obj.getId());
		return repo.save(obj);
	}

	public Usuario inserir(Usuario obj) {
		obj.setId(null);
		repo.save(obj);
		carrinhoFeignClient.inserirCarrinho(obj.getId());
		return obj;
	}

	public Usuario atualizar(Usuario prod) {
		buscar(prod.getId());
		return repo.save(prod);
	}

	public void deletar(Integer id) {
		buscar(id);
		repo.deleteById(id);
	}

}

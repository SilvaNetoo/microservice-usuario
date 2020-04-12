package com.microservusua.repositories;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.microservusua.dto.CarrinhoDTO;

@FeignClient(name = "carrinho-service")
public interface CarrinhoFeignClient {

	@GetMapping(value = "/carrinhos/{id}")
	public CarrinhoDTO buscarCarrinhoPorId(@PathVariable("id") Integer id);
	
	@PostMapping(value = "/carrinhos/{id}")
	public CarrinhoDTO inserirCarrinho(@PathVariable("id") Integer id);
	
	@PutMapping(value = "/carrinhos/{idProd}/produtos/{idCarrinho}")
	public ResponseEntity<Void> inserirProduto(@PathVariable("idProd") Integer idProd, @PathVariable("idCarrinho") Integer idCarrinho);
	
}

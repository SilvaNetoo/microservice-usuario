package com.microservusua.repositories;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservusua.dto.ProdutoDTO;


@FeignClient(value = "produto-service")
public interface ProdutoFeignClient {

	@GetMapping(value = "/{id}")
	public ProdutoDTO buscarProdutoPorId(@PathVariable("id") Integer id);
}

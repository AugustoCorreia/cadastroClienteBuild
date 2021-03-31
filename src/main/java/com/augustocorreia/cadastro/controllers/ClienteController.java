package com.augustocorreia.cadastro.controllers;

import com.augustocorreia.cadastro.models.Cliente;
import com.augustocorreia.cadastro.models.ClienteRequest;
import com.augustocorreia.cadastro.services.ClienteServiceImpl;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@CrossOrigin
@RestController("/usuarios")
public class ClienteController {

	private final ClienteServiceImpl service;

	@GetMapping
	public Page<Cliente> findAll(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size, @RequestParam(value = "search", required = false) String search){
		Pageable pageable = PageRequest.of(page, size);
		return ObjectUtils.isEmpty(search)?
				service.getAllUsers(pageable):
				service.getAllUsersBySearch(pageable,search);
	}

	//por algum motivo o @PathVariable está dando erro na conversão de String para Long (no swaggerUI), por isso usei da forma abaixo
	@GetMapping("/byId")
	public ResponseEntity<Cliente> findById(@RequestParam(value = "id")  Long id){
		return service.findById(id);
	}
//	***********************************EXEMPLO COM PATH VARIABLE
//	@GetMapping("/{id}")
//	public ResponseEntity<UsuarioResponse> findById(@PathVariable(value = "id")  Long id){
//		return service.findById(id);
//	}

	@PostMapping
	public ResponseEntity<Cliente> addCliente (@RequestBody ClienteRequest clienteRequest){
		return service.addUser(clienteRequest);
	}

	@PutMapping
	public ResponseEntity<Cliente> updateCliente (@RequestParam(value = "id") Long id , @RequestBody ClienteRequest clienteRequest){
		return service.updateUser(clienteRequest,id);
	}

}

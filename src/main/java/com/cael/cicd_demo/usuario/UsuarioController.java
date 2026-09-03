package com.cael.cicd_demo.usuario;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RequestMapping("/api/users")

@RestControllerAdvice
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {
	IUsuarioService service;

	public UsuarioController(UsuarioService service) {
		this.service = service;
	}

	@GetMapping
	public List<UsuarioDTO> getUsuarios(UsuarioFiltroDTO filtro) {

		return service.getUsuarios(filtro);
	}

	@GetMapping("/{id}")
	public UsuarioDTO getUsuario(@PathVariable Long id) {
		return service.getUsuario(id);
	}

	@PostMapping("")
	public List<UsuarioDTO> addUsuario(@Validated @RequestBody UsuarioDTO user) {
		try {
			return service.addUsuario(user);
		} catch (Exception e) {
			System.err.println("ERROR:" + e.getMessage());
			return  null;
		}
	}

	@PutMapping("/{id}")
	public List<UsuarioDTO> updateUsuario(@Validated @PathVariable Long id, @RequestBody UsuarioDTO user) {

	    return service.updateUsuario(id, user);
	}

	@DeleteMapping("/{id}")
	public List<UsuarioDTO> delUsuario(@PathVariable Long id) {
		return service.delUsuario(id);
	}
}

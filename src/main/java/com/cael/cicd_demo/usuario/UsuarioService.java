package com.cael.cicd_demo.usuario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService {

	List<UsuarioDTO> lista = new ArrayList<>();

	public UsuarioService() {
		lista.add(new UsuarioDTO(1L, "Leanne Graham", "Bret", "Sincere@april.biz"));
		lista.add(new UsuarioDTO(2L, "Ervin Howell", "Antonette", "Shanna@melissa.tv"));
		lista.add(new UsuarioDTO(3L, "Clementine Bauch", "Samantha", "Nathan@yesenia.net"));
	}

	public List<UsuarioDTO> getUsuarios(UsuarioFiltroDTO filtro) {
		return lista;
	}

	public UsuarioDTO getUsuario(Long id) {
		return lista.stream().filter(u -> u.getId().equals(id)).findFirst()
				.orElseThrow(() -> new UsuarioNotFoundException(id));
	}

	public List<UsuarioDTO> addUsuario(UsuarioDTO user) {
		user.setId(lista.size() > 0 ? lista.stream().mapToLong(UsuarioDTO::getId).max().orElse(0L) + 1L : 1L);
		lista.add(user);
		return lista;
	}

	public List<UsuarioDTO> delUsuario(Long id) {
		getUsuario(id);

		lista = lista.stream().filter(u -> !u.getId().equals(id)).toList();
		return lista;
	}

	public List<UsuarioDTO> updateUsuario(Long id, UsuarioDTO user) {

		UsuarioDTO usuario = getUsuario(id);

		usuario.setName(user.getName());
		usuario.setUsername(user.getUsername());
		usuario.setEmail(user.getEmail());

		return lista;
	}
}

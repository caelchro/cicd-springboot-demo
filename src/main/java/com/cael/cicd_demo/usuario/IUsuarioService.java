package com.cael.cicd_demo.usuario;

import java.util.List;

public interface IUsuarioService {

	public List<UsuarioDTO> getUsuarios(UsuarioFiltroDTO filtro);

	public UsuarioDTO getUsuario(Long id);

	public List<UsuarioDTO> addUsuario(UsuarioDTO user);

	public List<UsuarioDTO> delUsuario(Long id);

	public List<UsuarioDTO> updateUsuario(Long id, UsuarioDTO user);
}

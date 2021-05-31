package com.alolorsus.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.alolorsus.collector.entity.Usuario;
import com.alolorsus.collector.service.UsuarioService;

@Component
public class InfoAdicionalToken implements TokenEnhancer {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		Map<String, Object> information = new HashMap<>();
		information.put("username", usuario.getUsername());
		information.put("email", usuario.getEmail());
		information.put("password", usuario.getPassword());
		information.put("createAt", usuario.getCreateAt());
		information.put("nombre", usuario.getNombre());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(information);
		return accessToken;
	}

}

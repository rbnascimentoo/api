package br.com.rnascimento.api.security;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.rnascimento.api.constants.SecurityConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtManager {
	
	/**
	 * 
	 * @param login
	 * @param roles
	 * @return
	 */
	public String createToken(String login, List<String> roles) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, SecurityConstant.JWT_EXP_DAYS);
		
		String jwt = Jwts.builder()
				.setSubject(login)
				.setExpiration(calendar.getTime())
				.claim(SecurityConstant.JWT_ROLE_KEY, roles)
				.signWith(SignatureAlgorithm.HS512, SecurityConstant.API_KEY.getBytes())
				.compact();
		
		return jwt;
		
	}
	
	/**
	 * 
	 * 
	 * @param jwt
	 * @return
	 */
	public Claims parseToken(String jwt) {
		Claims claims = Jwts.parser().setSigningKey(SecurityConstant.API_KEY.getBytes())
				.parseClaimsJws(jwt)
				.getBody();
		return claims;
	}

}
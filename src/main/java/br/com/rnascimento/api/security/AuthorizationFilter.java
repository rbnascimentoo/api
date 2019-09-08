package br.com.rnascimento.api.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.rnascimento.api.constants.SecurityConstant;
import br.com.rnascimento.api.exceptions.ApiError;
import io.jsonwebtoken.Claims;

public class AuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (jwt == null || !jwt.startsWith(SecurityConstant.JWT_PROVIDER)) {
			jwtUnauthorized(response, SecurityConstant.JWT_INVALID_MSG);
			return;
		}

		jwt = jwt.replace(SecurityConstant.JWT_PROVIDER, "");
		try {

			Claims claims = new JwtManager().parseToken(jwt);

			String login = claims.getSubject();
			List<String> roles = (List<String>) claims.get(SecurityConstant.JWT_ROLE_KEY);
			
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			roles.forEach(r -> {
				authorities.add(new SimpleGrantedAuthority(r));
			});
			
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(login
					, null, authorities);
			
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			
		} catch (Exception e) {
			jwtUnauthorized(response, e.getMessage());
			return;
		}
		
		filterChain.doFilter(request, response);
	}

	private void jwtUnauthorized(HttpServletResponse response, String message) throws IOException, JsonProcessingException {
		ApiError error = new ApiError(HttpStatus.UNAUTHORIZED.value(), SecurityConstant.JWT_INVALID_MSG);
		PrintWriter printWriter = response.getWriter();
		ObjectMapper objectMapper = new ObjectMapper();
		String errorWriteString = objectMapper.writeValueAsString(error);
		printWriter.write(errorWriteString);

		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		response.setStatus(HttpStatus.UNAUTHORIZED.value());

		return;
	}

}

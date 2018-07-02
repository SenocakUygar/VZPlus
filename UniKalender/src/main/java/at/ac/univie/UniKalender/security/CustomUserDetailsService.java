package at.ac.univie.UniKalender.security;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import at.ac.univie.UniKalender.models.User;
import at.ac.univie.UniKalender.repositorys.UserRepository;
import at.ac.univie.UniKalender.repositorys.UserRolesRepository;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;
	private final UserRolesRepository userRolesRepository;

	@Autowired
	public CustomUserDetailsService(UserRepository userRepository, UserRolesRepository userRolesRepository) {
		this.userRepository = userRepository;
		this.userRolesRepository = userRolesRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (null == user) {
			throw new UsernameNotFoundException("No user present with username: " + email);
		} else {
			List<String> userRoles = userRolesRepository.findRoleByEmail(email);
			return new CustomUserDetails(user, userRoles);
		}
	}
}
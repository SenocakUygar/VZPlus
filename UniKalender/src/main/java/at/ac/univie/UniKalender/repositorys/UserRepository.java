package at.ac.univie.UniKalender.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import at.ac.univie.UniKalender.models.User;
import at.ac.univie.UniKalender.models.MyJAXBModels.FachGebietModule;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	List<User> findAll();
	User findByEmail(String email);
	User findByUsername(String username);
}

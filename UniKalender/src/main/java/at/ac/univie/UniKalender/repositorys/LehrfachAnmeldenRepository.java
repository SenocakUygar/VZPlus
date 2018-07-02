package at.ac.univie.UniKalender.repositorys;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import at.ac.univie.UniKalender.models.LehrfachAnmelden;

@Repository
public interface LehrfachAnmeldenRepository extends CrudRepository<LehrfachAnmelden, Long>{
	List<LehrfachAnmelden> findAll();
	LehrfachAnmelden findByGroupId(String groupId);
	List<LehrfachAnmelden> findByEmail(String email);
	
}

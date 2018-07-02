package at.ac.univie.UniKalender.repositorys;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import at.ac.univie.UniKalender.models.MyJAXBModels.Group;
import at.ac.univie.UniKalender.models.MyJAXBModels.Zeit;

public interface ZeitRepository extends CrudRepository<Zeit, String>{

	List<Zeit> findAll();
	List<Zeit> findByGroupId(String groupId);
}

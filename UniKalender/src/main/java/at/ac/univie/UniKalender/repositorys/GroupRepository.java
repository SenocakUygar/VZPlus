package at.ac.univie.UniKalender.repositorys;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import at.ac.univie.UniKalender.models.MyJAXBModels.Group;

public interface GroupRepository extends CrudRepository<Group, String>{

	List<Group> findAll();
	List<Group> findByCourseId(String courseId);
}

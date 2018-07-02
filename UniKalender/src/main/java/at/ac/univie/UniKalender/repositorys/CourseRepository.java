package at.ac.univie.UniKalender.repositorys;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import at.ac.univie.UniKalender.models.MyJAXBModels.Course;

public interface CourseRepository extends CrudRepository<Course, Long>{
	List<Course> findAll();
	List<Course> findByModulId(String modulId);
}

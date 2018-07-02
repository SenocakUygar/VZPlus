package at.ac.univie.UniKalender.repositorys;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import at.ac.univie.UniKalender.models.MyJAXBModels.StudienRichtungModule;

@Repository
public interface StudienRichtungRepository extends CrudRepository<StudienRichtungModule, String> {
	List<StudienRichtungModule> findAll();
}

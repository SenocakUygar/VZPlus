package at.ac.univie.UniKalender.repositorys;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import at.ac.univie.UniKalender.models.MyJAXBModels.FachGebietModule;

@Repository
public interface FachGebietRepository extends CrudRepository<FachGebietModule, String> {
	List<FachGebietModule> findAll();
	List<FachGebietModule> findByRichtungId(String richtungId);
}

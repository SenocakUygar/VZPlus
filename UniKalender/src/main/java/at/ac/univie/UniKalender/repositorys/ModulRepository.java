package at.ac.univie.UniKalender.repositorys;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import at.ac.univie.UniKalender.models.MyJAXBModels.Modul;

public interface ModulRepository extends CrudRepository<Modul, String>{
	List<Modul> findAll();
	List<Modul> findByFachGebietId(String fachGebietId);
}


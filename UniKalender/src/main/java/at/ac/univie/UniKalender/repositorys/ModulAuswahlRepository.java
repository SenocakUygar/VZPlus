package at.ac.univie.UniKalender.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import at.ac.univie.UniKalender.models.ModulAuswahl;

public interface ModulAuswahlRepository extends CrudRepository<ModulAuswahl, Long>{

	public List<ModulAuswahl> findAll();
	public List<ModulAuswahl> findByUserEmail(String userEmail);
	@Transactional
	@Modifying
	@Query("update ModulAuswahl ma set ma.status =:status where ma.modulId =:modulId")
	void updateStatus(@Param("modulId")String modulId, @Param("status")int status);

}

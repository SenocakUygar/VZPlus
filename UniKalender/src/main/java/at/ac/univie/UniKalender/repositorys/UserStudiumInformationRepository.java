package at.ac.univie.UniKalender.repositorys;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import at.ac.univie.UniKalender.models.UserStudiumInformation;

@Repository
public interface UserStudiumInformationRepository extends CrudRepository<UserStudiumInformation, Long>{
	List<UserStudiumInformation> findAll();
	List<UserStudiumInformation> findAllByUserId(Long userId);
}

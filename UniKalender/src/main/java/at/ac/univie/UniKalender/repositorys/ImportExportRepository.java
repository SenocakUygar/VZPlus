package at.ac.univie.UniKalender.repositorys;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import at.ac.univie.UniKalender.models.ImportExport;

public interface ImportExportRepository extends CrudRepository<ImportExport, Long> {
	
	List<ImportExport> findAll();
	ImportExport findByImportId(Long importId);
}

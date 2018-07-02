package at.ac.univie.UniKalender.repositorys;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import at.ac.univie.UniKalender.models.UserRole;
@Repository
public interface UserRolesRepository extends CrudRepository<UserRole, Long> {
    @Query("select a.role from UserRole a, User b where b.username=?1 and a.userid=b.id")
    public List<String> findRoleByUsername(String username);
    @Query("select a.role from UserRole a, User b where b.email=?1 and a.userid=b.id")
    public List<String> findRoleByEmail(String email);
}

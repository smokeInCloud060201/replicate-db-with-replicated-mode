package vn.com.smoke.slaveservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.smoke.slaveservice.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

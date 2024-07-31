package vn.com.smoke.masterservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.smoke.masterservice.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

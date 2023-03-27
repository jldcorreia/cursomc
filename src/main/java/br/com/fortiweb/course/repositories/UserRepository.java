package br.com.fortiweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fortiweb.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}

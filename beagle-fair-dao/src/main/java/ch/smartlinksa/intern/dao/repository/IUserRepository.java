package ch.smartlinksa.intern.dao.repository;

import ch.smartlinksa.intern.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.jws.soap.SOAPBinding;

public interface IUserRepository extends JpaRepository<User, String>{

}

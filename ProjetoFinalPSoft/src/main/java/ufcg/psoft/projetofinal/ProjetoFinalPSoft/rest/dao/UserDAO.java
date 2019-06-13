package ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.model.User;

import java.io.Serializable;

@Repository
public interface UserDAO<T, LOGIN extends Serializable> extends JpaRepository<User, String> {

    @Query(value="Select u from User u where u.login=:plogin")
    User findByLogin(@Param("plogin") String login);

    @SuppressWarnings("unchecked")
    User save(User user);
    
}

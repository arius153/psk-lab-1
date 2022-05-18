package lt.vu.persistence.interfaces;

import lt.vu.entities.Author;

import javax.persistence.EntityManager;
import java.util.List;

public interface AuthorDAO {

    List<Author> findAll();

    void setEm(EntityManager em);

    void persist(Author author);

    Author findOne(Long id);
}

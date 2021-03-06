package lt.vu.persistence;

import lt.vu.entities.Author;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class AuthorDAO implements lt.vu.persistence.interfaces.AuthorDAO {

    @Inject
    private EntityManager em;

    public List<Author> findAll() {
        return em.createNamedQuery("Author.findAll", Author.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Author author) {
        this.em.persist(author);
    }

    public Author findOne(Long id) {
        return em.find(Author.class, id);
    }
}

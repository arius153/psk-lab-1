package lt.vu.persistence;

import lt.vu.entities.Author;
import lt.vu.persistence.interfaces.AuthorDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Alternative
@ApplicationScoped
public class AlternativeAuthorDAO implements AuthorDAO {
    @Inject
    private EntityManager em;

    public List<Author> findAll() {
        System.out.println("\n\nFinding All authors \n\n");
        return em.createNamedQuery("Author.findAll", Author.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Author author) {
        System.out.println("\n\nSaving author with name" + author.getFullName() +"\n\n");
        this.em.persist(author);
    }

    public Author findOne(Long id) {
        System.out.println("\n\nGetting author with id: " + id + "\n\n");
        return em.find(Author.class, id);
    }
}

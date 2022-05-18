package lt.vu.persistence;

import lt.vu.entities.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import java.util.List;

@ApplicationScoped
public class BookDAO {

    @Inject
    private EntityManager em;

    public List<Book> findAll() {
        return em.createNamedQuery("Book.findAll", Book.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Book book) {
        this.em.persist(book);
    }

    public Book update(Book book) {
        return em.merge(book);
    }

    public void flush() {
        em.flush();
    }

    public Book findOne(Long id) {
        return em.find(Book.class, id);
    }

    public List<Book> findHmm() {
        return em.createNamedQuery("Book.findAllAvailableForUser", Book.class).getResultList();
    }
}

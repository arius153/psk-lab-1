package lt.vu.persistence;

import lt.vu.entities.Book;
import lt.vu.entities.LibraryUser;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class LibraryUserDAO {

    @Inject
    private EntityManager em;

    public List<LibraryUser> findAll() {
        return em.createNamedQuery("LibraryUser.findAll", LibraryUser.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(LibraryUser libraryUser) {
        this.em.persist(libraryUser);
    }

    public LibraryUser findOne(Long id) {
        return em.find(LibraryUser.class, id);
    }

    public List<Book> getAllAvailableBooks(Long libraryUserId) {
        return null;
    }
}

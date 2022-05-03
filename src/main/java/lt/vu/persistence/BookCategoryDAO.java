package lt.vu.persistence;

import lt.vu.entities.Book;
import lt.vu.entities.BookCategory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class BookCategoryDAO {

    @Inject
    private EntityManager em;

    public List<BookCategory> findAll() {
        return em.createNamedQuery("BookCategory.findAll", BookCategory.class).getResultList();
    }

    public void persist(BookCategory bookCategory) {
        this.em.persist(bookCategory);
    }

}

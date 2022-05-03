package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Book;
import lt.vu.entities.LibraryUser;
import lt.vu.persistence.BookDAO;
import lt.vu.persistence.LibraryUserDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Model
public class UserDetailedUseCase {

    @Inject
    LibraryUserDAO libraryUserDAO;

    @Inject
    BookDAO bookDAO;

    @Getter
    @Setter
    private LibraryUser user;
    
    @Getter
    @Setter
    private Book bookToBorrow;
    
    @Getter
    private List<Book> availableBooks;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long libraryUserId = Long.parseLong(requestParameters.get("libraryUserId"));
        user = libraryUserDAO.findOne(libraryUserId);
        availableBooks = bookDAO.findHmm();
    }

    @Transactional
    public void addBook() {
        if (user.getBooks() == null) {
            user.setBooks(new HashSet<>());
        }
        user.getBooks().add(bookToBorrow);
        libraryUserDAO.persist(user);
    }
}

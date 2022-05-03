package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Book;
import lt.vu.entities.Library;
import lt.vu.entities.LibraryUser;
import lt.vu.persistence.BookDAO;
import lt.vu.persistence.LibraryDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

@Model
public class LibraryDetailedUseCase {

    @Inject
    LibraryDAO libraryDAO;

    @Inject
    BookDAO bookDAO;

    @Getter
    @Setter
    private Library library;

    @Getter
    @Setter
    private Book book;

    @Getter
    @Setter
    private LibraryUser user;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long libraryId = Long.parseLong(requestParameters.get("libraryId"));
        this.library = libraryDAO.findOne(libraryId);
    }

    @Transactional
    public void addBook() {
        if (library.getBooks() == null) {
            library.setBooks(new HashSet<>());
        }
        library.getBooks().add(book);
        libraryDAO.persist(library);
    }

    @Transactional
    public void addUser() {
        if (library.getUsers() == null) {
            library.setUsers(new HashSet<>());
        }
        library.getUsers().add(user);
        libraryDAO.persist(library);
    }
}

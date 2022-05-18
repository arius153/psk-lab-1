package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Book;
import lt.vu.entities.BookCategory;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.AuthorDAO;
import lt.vu.persistence.BookCategoryDAO;
import lt.vu.persistence.BookDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Model
@LoggedInvocation
public class BooksUseCase {

    @Inject
    BookDAO bookDAO;

    @Inject
    BookCategoryDAO bookCategoryDAO;

    @Getter
    @Setter
    private Long AuthorId;

    @Getter
    @Setter
    private Book bookToAdd = new Book();

    @Getter
    @Setter
    private BookCategory bookCategoryToAdd = new BookCategory();

    @Getter
    private List<Book> allBooks;

    @Getter
    private List<BookCategory> allBookCategories;

    @Getter
    @Setter
    private BookCategory category;

    @PostConstruct
    public void init() {
        allBooks = bookDAO.findAll();
        allBookCategories = bookCategoryDAO.findAll();
    }

    @Transactional
    public void addBook() {
        this.bookDAO.persist(bookToAdd);
    }

    @Transactional
    public void addBookCategory() {
        this.bookCategoryDAO.persist(bookCategoryToAdd);
    }

}

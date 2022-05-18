package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Book;
import lt.vu.persistence.BookCategoryDAO;
import lt.vu.persistence.BookDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Setter
@Getter
@Named
public class BookDetailedUseCase implements Serializable {

    @Inject
    BookDAO bookDAO;

    @Inject
    BookCategoryDAO bookCategoryDAO;

    @Getter
    @Setter
    private Book book;

    @Getter
    @Setter
    private boolean editMode;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        editMode = Boolean.parseBoolean(requestParameters.get("editMode"));
        Long bookId = Long.parseLong(requestParameters.get("bookId"));
        book = bookDAO.findOne(bookId);
    }

    @Transactional
    public String editBook() {
        try {
            bookDAO.update(book);
        } catch (OptimisticLockException ole) {
            return "/bookDetailed?bookId=" + book.getId() +"&editMode=true&error=optimistic-lock-exception&faces-redirect=true";
        }
        return "/bookDetailed?bookId=" + book.getId() +"&editMode=false&faces-redirect=true";
    }
}

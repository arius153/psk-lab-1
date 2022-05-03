package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Author;
import lt.vu.entities.Book;
import lt.vu.persistence.BookCategoryDAO;
import lt.vu.persistence.BookDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Map;

@Model
public class BookDetailedUseCase {

    @Inject
    BookDAO bookDAO;

    @Inject
    BookCategoryDAO bookCategoryDAO;

    @Getter
    @Setter
    private Book book;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long bookId = Long.parseLong(requestParameters.get("bookId"));
        book = bookDAO.findOne(bookId);
    }

}

package lt.vu.usecases.mybatis;

import lombok.Getter;
import lt.vu.mybatis.dao.BookMapper;
import lt.vu.mybatis.model.Book;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Map;

@Model
public class BookDetailedMyBatis {

    @Inject
    private BookMapper bookMapper;

    @Getter
    private Book book;


    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long bookId = Long.parseLong(requestParameters.get("bookId"));
        book = bookMapper.getBookWithAuthor(bookId);
    }


}

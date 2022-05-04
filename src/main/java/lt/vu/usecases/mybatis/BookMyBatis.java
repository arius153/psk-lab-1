package lt.vu.usecases.mybatis;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.dao.BookCategoryMapper;
import lt.vu.mybatis.dao.BookMapper;
import lt.vu.mybatis.model.Book;
import lt.vu.mybatis.model.BookCategory;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class BookMyBatis {

    @Inject
    BookMapper bookMapper;

    @Inject
    BookCategoryMapper bookCategoryMapper;

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
        allBooks = bookMapper.findAll();
        allBookCategories = bookCategoryMapper.selectAll();
    }

    @Transactional
    public String addBook() {
        this.bookMapper.insert(bookToAdd);
        return "/mybatis/books?faces-redirect=true";

    }

    @Transactional
    public String addBookCategory() {
        this.bookCategoryMapper.insert(bookCategoryToAdd);
        return "/mybatis/books?faces-redirect=true";
    }

}

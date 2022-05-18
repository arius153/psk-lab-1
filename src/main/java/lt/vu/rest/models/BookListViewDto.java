package lt.vu.rest.models;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Book;

@Getter
@Setter
public class BookListViewDto {

    private Long id;
    private String name;
    private String authorName;
    private String category;

    public BookListViewDto(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.authorName = book.getAuthor().getFullName();
        this.category = book.getCategory().getCategory();
    }
}

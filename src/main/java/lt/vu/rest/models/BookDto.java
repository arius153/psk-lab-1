package lt.vu.rest.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.vu.entities.Book;
import lt.vu.entities.Library;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class BookDto {

    private Long id;
    private String name;
    private String authorName;
    private String description;
    private List<String> inLibraries;
    private Integer version;

    public BookDto(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.authorName = book.getAuthor().getFullName();
        this.description = book.getDescription();
        this.inLibraries = book.getLibraries().stream().map(Library::getName).collect(Collectors.toList());
        this.version = book.getVersion();
    }
}

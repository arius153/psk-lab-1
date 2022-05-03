package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "BOOK")
@NamedQueries({
        @NamedQuery(name = "Book.findAll", query = "select t from Book as t"),
        @NamedQuery(name = "Book.findAllAvailableForUser",
                query = "select distinct t from Book as t join t.libraries as library join library.users as user where user.id = 1")
})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private BookCategory category;

    private String description;

    private Boolean currentlyIssued;

    @ManyToMany(mappedBy = "books")
    private Set<Library> libraries;

    @ManyToMany(mappedBy = "books")
    private Set<LibraryUser> libraryUser;

    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    private Author author;

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id.equals(book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "LIBRARY")
@NamedQueries({
        @NamedQuery(name = "Library.findAll", query = "select t from Library as t")
})
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    @ManyToMany
    @JoinTable(
            name="LIBRARY_BOOK",
            joinColumns = @JoinColumn(name = "LIBRARY_ID"),
            inverseJoinColumns = @JoinColumn(name = "BOOK_ID")
    )
    private Set<Book> books;

    @ManyToMany
    @JoinTable(
            name = "LIBRARY_LIBRARY_USER",
            joinColumns = @JoinColumn(name = "LIBRARY_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID")
    )
    private Set<LibraryUser> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return id.equals(library.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

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
@Table(name = "LIBRARY_USER")
@NamedQueries({
        @NamedQuery(name = "LibraryUser.findAll", query = "select t from LibraryUser as t")
})
public class LibraryUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @ManyToMany
    @JoinTable(
            name = "LIBRARY_USER_BOOKS",
            joinColumns = @JoinColumn(name = "LIBRARY_USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "BOOK_ID")
    )
    private Set<Book> books;

    @ManyToMany(mappedBy = "users")
    private Set<Library> libraries;

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryUser that = (LibraryUser) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

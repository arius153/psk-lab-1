package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "Author")
@NamedQueries({
        @NamedQuery(name = "Author.findAll", query = "select t from Author as t")
})
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String description;

    @OneToMany(mappedBy = "author")
    Collection<Book> books;

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}

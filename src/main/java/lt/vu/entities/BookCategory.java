package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "BOOK_CATEGORY")
@NamedQueries({
        @NamedQuery(name = "BookCategory.findAll", query = "select t from BookCategory as t")
})
public class BookCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    @OneToMany(mappedBy = "category")
    Collection<Book> books;

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}

package lt.vu.mybatis.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Author {

    private Long id;

    private String description;

    private String fullname;

    private List<Book> books;
}

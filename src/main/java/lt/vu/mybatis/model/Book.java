package lt.vu.mybatis.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {

    private Long id;

    private Boolean currentlyissued;

    private String description;

    private String name;

//    private Long authorId;
//
//    private Long categoryId;

    private Author author;

    private BookCategory category;

}

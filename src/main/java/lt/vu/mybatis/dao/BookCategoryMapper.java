package lt.vu.mybatis.dao;

import lt.vu.mybatis.model.BookCategory;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface BookCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.BOOK_CATEGORY
     *
     * @mbg.generated Wed May 04 09:11:11 EEST 2022
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.BOOK_CATEGORY
     *
     * @mbg.generated Wed May 04 09:11:11 EEST 2022
     */
    int insert(BookCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.BOOK_CATEGORY
     *
     * @mbg.generated Wed May 04 09:11:11 EEST 2022
     */
    BookCategory selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.BOOK_CATEGORY
     *
     * @mbg.generated Wed May 04 09:11:11 EEST 2022
     */
    List<BookCategory> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.BOOK_CATEGORY
     *
     * @mbg.generated Wed May 04 09:11:11 EEST 2022
     */
    int updateByPrimaryKey(BookCategory record);
}
package spring.library.dto.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import spring.library.domain.Book;


@Getter
@Setter
@Builder
public class BookDto {
    private long id;
    private String title;
    private String author;
    private String publisher;
    private long publicationYear;
    private String classification;
    private String status;
    private long amount;


    public static BookDto from(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .publicationYear(book.getPublicationYear())
                .classification(book.getClassification())
                .status(book.getStatus())
                .amount(book.getAmount())
                .build();
    }
}

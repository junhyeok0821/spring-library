package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;
import spring.library.dto.request.BookRequest;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String author;
    private String publisher;
    private long publicationYear;
    private String classification;
    private String status;
    private long amount;



    @OneToMany(mappedBy="book", fetch=FetchType.LAZY,
            cascade=CascadeType.ALL,
            orphanRemoval=true)

    private List<Loan> list=new ArrayList<>();


    public void statusChange(){
        if(status.equals("대출가능")){
            status="대출중";
        }
        else status="대출가능";
    }



    public static Book from(BookRequest bookRequest){
        return Book.builder()
                .title(bookRequest.getTitle())
                .author(bookRequest.getAuthor())
                .publisher(bookRequest.getPublisher())
                .publicationYear(bookRequest.getPublicationYear())
                .classification(bookRequest.getClassification())
                .status(bookRequest.getStatus())
                .amount(bookRequest.getAmount())
                .build();
    }

    public void update(BookRequest bookRequest){
        setTitle(bookRequest.getTitle());
        setAuthor(bookRequest.getAuthor());
        setPublisher(bookRequest.getPublisher());
        setPublicationYear(bookRequest.getPublicationYear());
        setClassification(bookRequest.getClassification());
        setStatus(bookRequest.getStatus());
        setAmount(bookRequest.getAmount());
    }



}
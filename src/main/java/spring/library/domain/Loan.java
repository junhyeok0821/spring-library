package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String loanDate;
    private String dueDate;
    private long renewalCount;
    private boolean returned;



    @ManyToOne
    @JoinColumn(nullable=false)
    private Member member;

    @ManyToOne
    @JoinColumn(nullable=false)
    private Book book;

    public static Loan loan(Member member, Book book) {

        int plus = switch (member.getFeature()) {
            case "학생" -> 10;
            case "교직원" -> 30;
            default -> 110813;
        };

        return Loan.builder()
                .loanDate(LocalDate.now().toString())
                .dueDate(LocalDate.now().plusDays(plus).toString())
                .renewalCount(0)
                .returned(false)
                .member(member)
                .book(book)
                .build();
    }

    public void returnLoan() {
        returned = true;
    }

    public boolean renewable(){
        return renewalCount == 0;
    }

    public void renew() {
        dueDate = LocalDate.parse(dueDate, DateTimeFormatter.ISO_LOCAL_DATE).plusDays(5).toString();
        renewalCount++;
    }

}
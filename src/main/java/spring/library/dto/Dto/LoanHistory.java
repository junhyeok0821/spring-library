package spring.library.dto.Dto;

import lombok.*;
import spring.library.domain.Loan;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanHistory {

    private long bookId;
    private String title;
    private String author;
    private String loanDate;
    private String dueDate;
    private long renewalCount;
    private boolean returned;


    public static LoanHistory from(Loan loan) {
        return LoanHistory.builder()
                .bookId(loan.getBook().getId())
                .title(loan.getBook().getTitle())
                .author(loan.getBook().getAuthor())
                .loanDate(loan.getLoanDate())
                .dueDate(loan.getDueDate())
                .renewalCount(loan.getRenewalCount())
                .returned(loan.isReturned())
                .build();
    }
}
package spring.library.dto.Dto;

import lombok.*;
import spring.library.domain.Loan;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanDto {

    private long bookId;
    private String title;
    private String author;
    private String loanDate;
    private String dueDate;
    private long renewalCount;

    public static LoanDto from(Loan loan) {
        return LoanDto.builder()
                .bookId(loan.getBook().getId())
                .title(loan.getBook().getTitle())
                .author(loan.getBook().getAuthor())
                .loanDate(loan.getLoanDate())
                .dueDate(loan.getDueDate())
                .renewalCount(loan.getRenewalCount())
                .build();
    }
}
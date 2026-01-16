package spring.library.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.dto.Dto.LoanDto;
import spring.library.dto.Dto.LoanHistory;
import spring.library.dto.request.LoanRequest;
import spring.library.domain.Book;
import spring.library.domain.Loan;
import spring.library.domain.Member;
import spring.library.repository.BookRepository;
import spring.library.repository.LoanRepository;
import spring.library.repository.MemberRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class LoanService {


    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;


    @Transactional
    public String checkoutBook(long bookId, LoanRequest loanRequest) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book == null) return "오류";
        if(book.getStatus().equals("대출가능")){
            Member member = memberRepository.findById(loanRequest.getMemberId()).orElse(null);
            if(member == null) return "오류";
            int count=member.getBooks().stream().filter(loan-> loan.isReturned()==false).toList().size();
            switch(member.getFeature()){
                case "학생":
                    if(count==10) return "오류";
                    break;
                case "교직원":
                    if(count==20) return "오류";
                    break;
                default:
                    if(count==100) return "오류";
                    break;
            }
            loanRepository.save(Loan.loan(member, book));
            book.statusChange();
            return "대출 완료";
        }
        else return "오류";
    }


    public List<LoanDto> checkoutList(long memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        return member.getBooks().stream().filter(loan-> loan.isReturned()==false).map(LoanDto::from).toList();
    }


    public List<LoanHistory> checkoutHistory(long memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        return member.getBooks().stream().map(LoanHistory::from).toList();
    }


    @Transactional
    public String returnBook(long bookId, LoanRequest loanRequest) {
        Member member = memberRepository.findById(loanRequest.getMemberId()).orElse(null);
        Book book = bookRepository.findById(bookId).orElse(null);
        Loan loan = loanRepository.findByBookAndMemberAndReturned(book,member,false).orElse(null);
        if(loan!=null){
            book.statusChange();
            loan.returnLoan();
            return "반납 완료";
        }
        else return "오류";
    }


    @Transactional
    public String checkoutRenewal(long bookId, LoanRequest loanRequest) {
        Member member = memberRepository.findById(loanRequest.getMemberId()).orElse(null);
        Book book = bookRepository.findById(bookId).orElse(null);
        Loan loan = loanRepository.findByBookAndMemberAndReturned(book,member,false).orElse(null);
        if(loan!=null && loan.renewable()){ //&& LocalDate.now().toString().equals(loan.getDueDate())
            loan.renew();
            return "연장 완료";
        }
        else return "오류";
    }


}
package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.library.domain.Book;
import spring.library.domain.Loan;
import spring.library.domain.Member;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    Optional<Loan> findByBookAndMemberAndReturned(Book book, Member member, boolean returned);

}
package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.dto.Dto.LoanDto;
import spring.library.dto.Dto.LoanHistory;
import spring.library.dto.request.LoanRequest;
import spring.library.service.LoanService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class LoanController {


    private final LoanService loanService;


    @PostMapping("/{bookId}/checkout")
    public ResponseEntity<String> checkoutBook(@PathVariable long bookId, @RequestBody LoanRequest loanRequest) {
        return ResponseEntity.ok().body(loanService.checkoutBook(bookId,loanRequest));
    }



    @GetMapping("/checkout")
    public ResponseEntity<List<LoanDto>> checkoutList(@RequestParam("memberId") long memberId) {
        return ResponseEntity.ok().body(loanService.checkoutList(memberId));
    }



    @GetMapping("/history")
    public ResponseEntity<List<LoanHistory>> checkoutHistory(@RequestParam("memberId") long memberId) {
        return ResponseEntity.ok().body(loanService.checkoutHistory(memberId));
    }



    @PutMapping("/{bookId}/return")
    public ResponseEntity<String> returnBook(@PathVariable long bookId, @RequestBody LoanRequest loanRequest) {
        return ResponseEntity.ok().body(loanService.returnBook(bookId,loanRequest));
    }



    @PutMapping("/{bookId}/renewal")
    public ResponseEntity<String> checkoutRenewal(@PathVariable long bookId, @RequestBody LoanRequest loanRequest) {
        return ResponseEntity.ok().body(loanService.checkoutRenewal(bookId,loanRequest));
    }


}
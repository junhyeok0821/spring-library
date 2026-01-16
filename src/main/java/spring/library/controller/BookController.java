package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.dto.request.BookRequest;
import spring.library.dto.response.BookResponse;
import spring.library.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {


    private final BookService bookService;


    @PostMapping("/books")
    public ResponseEntity<String> addBook(@RequestBody BookRequest bookRequest) {
        return ResponseEntity.ok().body(bookService.addBook(bookRequest));
    }


    @GetMapping("/books")
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        return ResponseEntity.ok().body(bookService.getAllBooks());
    }


    @PutMapping("/books/{bookId}")
    public ResponseEntity<String> updateBook(@PathVariable Long bookId, @RequestBody BookRequest bookRequest) {
        return ResponseEntity.ok().body(bookService.updateBook(bookId,bookRequest));
    }


    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable Long bookId) {
        return ResponseEntity.ok().body(bookService.deleteBook(bookId));
    }


}
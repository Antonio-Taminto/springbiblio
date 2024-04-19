package com.springbiblio.controllers;

import com.springbiblio.entities.BookEntity;
import com.springbiblio.entities.UserEntity;
import com.springbiblio.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @PostMapping("/create")
    public ResponseEntity<BookEntity> createBook(@RequestBody BookEntity book){
        return ResponseEntity.ok().body(bookService.addBook(book));
    }
    @GetMapping("/getList")
    public ResponseEntity<List<BookEntity>> getAllBooks(){
        return ResponseEntity.ok().body(bookService.getAllBooks());
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookEntity> getBookById(@PathVariable Long id){
        Optional<BookEntity> bookEntityOptional = bookService.getBookById(id);
        if (bookEntityOptional.isPresent()){
            return ResponseEntity.ok(bookEntityOptional.get());
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("{id}")
    public ResponseEntity<BookEntity> updateBookById(@PathVariable Long id,@RequestBody BookEntity book){
        Optional<BookEntity> bookEntityOptional = bookService.updateBook(id,book);
        if (bookEntityOptional.isPresent()){
            return ResponseEntity.ok(bookEntityOptional.get());
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<BookEntity> deleteBookById(@PathVariable Long id){
        Optional<BookEntity> bookEntityOptional = bookService.deleteBookById(id);
        if (bookEntityOptional.isPresent()){
            return ResponseEntity.ok(bookEntityOptional.get());
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    @PatchMapping("/lend/{userId}/{bookId}")
    public ResponseEntity<BookEntity> lendBookById(@PathVariable Long userId,@PathVariable Long bookId){
        Optional<BookEntity> bookEntityOptional = bookService.lendBook(userId,bookId);
        if (bookEntityOptional.isPresent()){
            return ResponseEntity.ok(bookEntityOptional.get());
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    @PatchMapping("/return/{userId}/{bookId}")
    public ResponseEntity<BookEntity> returnBookById(@PathVariable Long userId,@PathVariable Long bookId){
        Optional<BookEntity> bookEntityOptional = bookService.returnBook(userId,bookId);
        if (bookEntityOptional.isPresent()){
            return ResponseEntity.ok(bookEntityOptional.get());
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
}

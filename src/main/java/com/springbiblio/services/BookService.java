package com.springbiblio.services;

import com.springbiblio.entities.BookEntity;
import com.springbiblio.entities.UserEntity;
import com.springbiblio.repositories.BookRepository;
import com.springbiblio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    public List<BookEntity> getAllBooks(){
        return bookRepository.findAll();
    }
    public Optional<BookEntity> getBookById(Long id){
        Optional<BookEntity> bookEntityOptional = bookRepository.findById(id);
        if(bookEntityOptional.isPresent()){
            return bookEntityOptional;
        }else {
            return Optional.empty();
        }
    }
    public BookEntity addBook(BookEntity book){
        return bookRepository.save(book);
    }

    public Optional<BookEntity> updateBook(Long id,BookEntity book){
        Optional<BookEntity> bookEntityOptional = bookRepository.findById(id);
        if(bookEntityOptional.isPresent()){
            bookEntityOptional.get().setTitolo(book.getTitolo());
            bookEntityOptional.get().setAutore(book.getAutore());
            bookEntityOptional.get().setAnnoPubblicazione(book.getAnnoPubblicazione());
            BookEntity updatedBook = bookRepository.save(bookEntityOptional.get());
            return Optional.of(updatedBook);
        }else {
            return Optional.empty();
        }
    }
    public Optional<BookEntity> deleteBookById(Long id){
        Optional<BookEntity> bookEntityOptional = bookRepository.findById(id);
        if(bookEntityOptional.isPresent()){
            bookRepository.deleteById(bookEntityOptional.get().getId());
            return bookEntityOptional;
        }else {
            return Optional.empty();
        }
    }
    public Optional<BookEntity> lendBook(Long userId,Long bookId){
        Optional<UserEntity> userEntityOptional = userRepository.findById(bookId);
        if(userEntityOptional.isPresent()){
            Optional<BookEntity> bookEntityOptional = bookRepository.findById(userId);
            if(bookEntityOptional.isPresent()){
                if(userEntityOptional.get().getTakenBooks().size() < 10
                    && bookEntityOptional.get().isDisponibile()){
                    bookEntityOptional.get().setDisponibile(false);
                    bookEntityOptional.get().setUser(userEntityOptional.get());
                    BookEntity lendBook = bookRepository.save(bookEntityOptional.get());
                    return Optional.of(lendBook);
                }
            }
        }
        return Optional.empty();
    }
    public Optional<BookEntity> returnBook(Long userId,Long bookId){
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if(userEntityOptional.isPresent()){
            List<BookEntity> lendtBookEntityList = userEntityOptional.get().getTakenBooks();
            for (BookEntity book : lendtBookEntityList){
                if (book.getId().equals(bookId)){
                    Optional<BookEntity> bookEntityOptional = bookRepository.findById(userId);
                    bookEntityOptional.get().setDisponibile(true);
                    BookEntity returnedBook = bookRepository.save(bookEntityOptional.get());
                    return Optional.of(returnedBook);
                }
            }
        }
        return Optional.empty();
    }
}

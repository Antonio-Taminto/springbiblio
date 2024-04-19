package com.springbiblio.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<BookEntity> takenBooks;

    public UserEntity(Long id, String name, List<BookEntity> takenBooks) {
        this.id = id;
        this.name = name;
        this.takenBooks = takenBooks;
    }

    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookEntity> getTakenBooks() {
        return takenBooks;
    }

    public void setTakenBooks(List<BookEntity> takenBooks) {
        this.takenBooks = takenBooks;
    }
}

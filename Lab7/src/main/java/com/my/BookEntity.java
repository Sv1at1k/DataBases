package com.my;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "book", schema = "db_jdbc", catalog = "")
public class BookEntity {
    private int idBook;
    private String bookName;
    private String author;
    private int amount;
    private List<PersonEntity> persons;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDBook", nullable = false)
    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    @Basic
    @Column(name = "BookName", nullable = false, length = 45)
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Basic
    @Column(name = "Author", nullable = false, length = 45)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "Amount", nullable = false)
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntity that = (BookEntity) o;

        if (idBook != that.idBook) return false;
        if (amount != that.amount) return false;
        if (bookName != null ? !bookName.equals(that.bookName) : that.bookName != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idBook;
        result = 31 * result + (bookName != null ? bookName.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + amount;
        return result;
    }

    @ManyToMany
    @JoinTable(name = "personbook", catalog = "", schema = "db_jdbc", joinColumns = @JoinColumn(name = "IDBook", referencedColumnName = "IDBook", nullable = false), inverseJoinColumns = @JoinColumn(name = "IDPerson", referencedColumnName = "IDPerson", nullable = false))
    public List<PersonEntity> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonEntity> persons) {
        this.persons = persons;
    }
}

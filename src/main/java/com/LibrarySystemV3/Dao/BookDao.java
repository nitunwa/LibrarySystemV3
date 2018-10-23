package com.LibrarySystemV3.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.LibrarySystemV3.models.Book;

@Repository
@Transactional
public class BookDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Book> getAllBook(String category){
	List<Book> bookList =  entityManager.createQuery("from Book where category= :Category" , Book.class)
			.setParameter("Category", category).getResultList();
	return bookList;
	}
	
	public Book getSingleBook(String bookName) {
		Book book= entityManager.createQuery("from Book where bookName= :BookName", Book.class)
				.setParameter("BookName", bookName).getSingleResult();
		return book;
		
	}
}

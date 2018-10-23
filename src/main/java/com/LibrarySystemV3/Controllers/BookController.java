package com.LibrarySystemV3.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LibrarySystemV3.Dao.BookDao;
import com.LibrarySystemV3.Dao.UserV3Dao;
import com.LibrarySystemV3.models.AuthenticationDto;
import com.LibrarySystemV3.models.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/book-rest")
public class BookController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	BookDao bookDao;
	Book book = new Book();

	@GetMapping(value = "/getBookByCatergory/{category}", produces = "application/xml")
	public ResponseEntity<?> getBookByCatergory(@PathVariable(value = "category") String category
			) {
		List<Book> bookList = new ArrayList<>();
		try {
			bookList = bookDao.getAllBook(category);
			
			return new ResponseEntity(bookList, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("fail to load", ex);
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping(value = "/getSingleBook/{bookName}", produces = "application/xml")
	public ResponseEntity<?> getSingleBook(@PathVariable(value = "bookName") String bookName){
		
		try {
			Book book =bookDao.getSingleBook(bookName) ;
			return new ResponseEntity(book, HttpStatus.OK);
		}
		catch (Exception ex) {
			logger.error("fail to load", ex);
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
	}

}

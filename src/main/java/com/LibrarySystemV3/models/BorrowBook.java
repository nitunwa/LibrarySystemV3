package com.LibrarySystemV3.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "borrowBook")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class BorrowBook {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "borrrowUserId") 
	private User borrrowUser;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) 
	@JoinColumn(name = "borrrowBookId")
	private Book borrowBook;

	private Date checkOut;
	private Date checkIn;
	private Date returnDate;

	// private String reportBook;

	// public String getReportBook() {
	// return reportBook;
	// }

	// public void setReportBook(String reportBook) {
	// this.reportBook = reportBook;
	// }

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getBorrrowUser() {
		return borrrowUser;
	}

	public void setBorrrowUser(User borrrowUser) {
		this.borrrowUser = borrrowUser;
	}

	public Book getBorrowBook() {
		return borrowBook;
	}

	public void setBorrowBook(Book borrowBook) {
		this.borrowBook = borrowBook;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public BorrowBook(long id, User borrrowUser, Book borrowBook, Date checkOut, Date checkIn, Date returnDate) {
		super();
		this.id = id;
		this.borrrowUser = borrrowUser;
		this.borrowBook = borrowBook;
		this.checkOut = checkOut;
		this.checkIn = checkIn;
		this.returnDate = returnDate;
	}

	// public BorrowBook(long id, User borrrowUserName, Book borrowBookName,
	// Date checkOut) {
	// super();
	// this.id = id;
	// this.borrrowUserName = borrrowUserName;
	// this.borrowBookName = borrowBookName;
	// this.checkOut = checkOut;

	// }

	public BorrowBook() {

	}

	@Override
	public String toString() {
		return "BorrowBook [id=" + id + ", borrrowUser=" + borrrowUser + ", borrowBook=" + borrowBook + ", checkOut="
				+ checkOut + ", checkIn=" + checkIn + ",returnDate=" + returnDate + "]";
	}

	// @Override
	// public String toString() {
	// return "BorrowBook [id=" + id + ", borrrowUserName=" + borrrowUserName +
	// ", borrowBookName=" + borrowBookName + ", checkOut="
	// + checkOut + "]";
	// }

}

package com.yazilimokulu.mvc.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "BOOK_QUESTION_CHAPTER")
public class BookChapter extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 9024812200347805777L;

	private Long id;
	
	private String name;

	private String urlUniqueName;

	private List<BookQuestion> questionList;
	
	private Book book;

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "URL_NAME")
	public String getUrlUniqueName() {
		return name.toLowerCase().replace(" ", "-");
	}

	public void setUrlUniqueName(String urlUniqueName) {
		this.urlUniqueName = urlUniqueName;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bookChapter")
	public List<BookQuestion> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<BookQuestion> questionList) {
		this.questionList = questionList;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BOOK_CHAPTER_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="BOOK_ID")
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}



}

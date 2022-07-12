package sampleBatch4;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="book")
@Data
public class Book {
	@Id
	@Column(name="isbn")
	String isbn;
	
	@Column(name="title")
	String title;
	@Column(name="price")
	Integer price;
	
	@Column(name="publish_date")
	LocalDate publishDate;
}

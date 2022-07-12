package sampleBatch4;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.support.AbstractItemStreamItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

public class BookJpaItemWriter extends AbstractItemStreamItemWriter<Book>{
	Logger logger=LoggerFactory.getLogger(BookJpaItemWriter.class);
	
	@Autowired
	BookRepository bookRepository;

	@Override
	public void write(List<? extends Book> items) throws Exception {
		logger.info("[writer] write items: size = {}",items.size());
		items.forEach(book->{
			logger.info("[writer] write item: isbn = {}, title = {}",book.getIsbn(),book.getTitle());
			bookRepository.save(book);
		});
	}
	
	

}

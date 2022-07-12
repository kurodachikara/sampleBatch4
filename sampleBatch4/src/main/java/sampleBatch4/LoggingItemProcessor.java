package sampleBatch4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class LoggingItemProcessor implements ItemProcessor<Book,Book>{
	Logger logger=LoggerFactory.getLogger(LoggingItemProcessor.class);

	@Override
	public Book process(Book item) throws Exception {
		logger.info("[processor] process book: isbn = {}, title = {}",item.getIsbn(),item.getTitle());
		return item;
	}
	
}

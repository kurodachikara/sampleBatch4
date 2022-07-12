package sampleBatch4;

import java.io.BufferedReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.DefaultBufferedReaderFactory;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;
import org.springframework.core.io.Resource;

public class CsvFileItemReader extends AbstractItemCountingItemStreamItemReader<Book> implements ResourceAwareItemReaderItemStream<Book> {
	Logger logger=LoggerFactory.getLogger(CsvFileItemReader.class);
	
	Resource resource;
	BufferedReader reader;
	DateTimeFormatter publishDateFormatter;
	@Override
	public void setResource(Resource resource) {
		this.resource=resource;
		
	}
	@Override
	protected Book doRead() throws Exception {
		String line=reader.readLine();
		if(line!=null) {
			String[] tokens=line.split(",");
			Book book=new Book();
			book.setIsbn(tokens[0]);
			book.setTitle(tokens[1]);
			book.setPrice(Integer.parseInt(tokens[2]));
			book.setPublishDate(LocalDate.parse(tokens[3],publishDateFormatter));
			logger.info("[reader] read book:  isbn = {}, title = {}",book.getIsbn(),book.getTitle());
			return book;
		}
		logger.info("[reader] readed books");
		return null;
	}
	@Override
	protected void doOpen() throws Exception {
		DefaultBufferedReaderFactory bufferedReaderFactory=new DefaultBufferedReaderFactory();
		reader=bufferedReaderFactory.create(resource, "UTF-8");
		publishDateFormatter=DateTimeFormatter.ofPattern("uuuu-MM-dd");
		
	}
	@Override
	protected void doClose() throws Exception {
		reader.close();
	}
	
}

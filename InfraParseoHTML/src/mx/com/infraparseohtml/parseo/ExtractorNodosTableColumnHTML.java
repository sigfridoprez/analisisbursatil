package mx.com.infraparseohtml.parseo;

import mx.com.infraestructura.exceptions.BusinessException;

import org.apache.log4j.Logger;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class ExtractorNodosTableColumnHTML {
	private Logger logger = Logger.getLogger(this.getClass());
	
	public NodeList getListaNodosTableColumn(String strUrl)throws BusinessException{
		logger.debug("En getListaNodosTexto strUrl::" + strUrl);
		
		NodeFilter filter = new NodeClassFilter (TableColumn.class);
		Parser parser;
		
		try {
			parser = new Parser (strUrl);
			return parser.extractAllNodesThatMatch (filter);
		} catch (ParserException e) {
			e.printStackTrace();
			logger.error(e);
			throw new BusinessException("Error al tratar de extraer la lista de nodos, la comunicacion se perdio");
		}
	}
}

package mx.com.infraparseohtml.parseo;
import mx.com.infraestructura.exceptions.BusinessException;

import org.apache.log4j.Logger;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;


public class ParserHTML extends Parser {
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass());
	private static final String ID = "id";
	
	public TableTag getTabla(String strUrl,String strIdTabla)throws BusinessException{
		logger.debug("En getTabla  strUrl:::" + strUrl + "  strIdTabla" + strIdTabla);
		NodeFilter filter;
		ParserHTML parser;
		NodeList nodeList;
		SimpleNodeIterator iterNodeList;
		TableTag tableTag;
		Object object;
		String strID;
		
		try {
			parser = new ParserHTML();
			parser.setFeedback(ParserHTML.STDOUT);
			parser.setResource(strUrl);
			filter = new TagNameFilter ("TABLE");
			
			logger.debug("Obtenemos todas las tablas de la pagina");
			nodeList = parser.parse (filter);
			if(nodeList !=null){
				logger.debug("Buscamos la tabla que nos interesa");
				iterNodeList =	nodeList.elements();
				while(iterNodeList.hasMoreNodes()){
					object = iterNodeList.nextNode();
					if(object instanceof TableTag){
						tableTag = (TableTag)object;						
						if(tableTag != null){
							if(tableTag.getAttribute(ID)!=null || tableTag.getAttribute(ID.toUpperCase())!=null){
								logger.debug("La tabla tiene ID buscamos nuestra tabla");
								strID = tableTag.getAttribute(ID)!=null?tableTag.getAttribute(ID):tableTag.getAttribute(ID.toUpperCase());
								if(strIdTabla.equalsIgnoreCase(strID)){
									logger.debug("La encontramos");
									return tableTag;
								}
							}
						}
					}
				}
			}			 
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Error problemas al parcear la pagina",e);
			throw new BusinessException("Error problemas al parcear la pagina ERROR::" + e.getMessage());
		}
		return null;
	}
}

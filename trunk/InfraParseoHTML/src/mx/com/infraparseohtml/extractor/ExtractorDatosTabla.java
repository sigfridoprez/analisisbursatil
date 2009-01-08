package mx.com.infraparseohtml.extractor;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import org.htmlparser.tags.TableColumn;

import mx.com.analisispreciosmercado.conf.IpConfPropiedadesTabla;
import mx.com.analisispreciosmercado.conf.IpConfTablasHtmlParsear;
import mx.com.infracomunes.vo.ObjetoTablaVO;
import mx.com.infraestructura.exceptions.BusinessException;
import mx.com.infraparseohtml.conftablas.exception.PaginaSinDatosException;
import mx.com.infraparseohtml.util.Constantes;

import org.apache.log4j.Logger;
import org.apache.commons.beanutils.BeanUtils;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.Span;
import org.htmlparser.tags.TableHeader;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;

public class ExtractorDatosTabla {
	private Logger logger = Logger.getLogger(this.getClass());
	private static final String TBODY = "TBODY";
	private static final String THEAD = "THEAD";
	private boolean blnPuntos = true;

	/**
	 * Metodos para la extracion de datos de una tabla con presentacion de GRID
	 * 
	 * @param tableTag
	 * @param ipConfTablasHtmlParsear
	 * @return
	 * @throws BusinessException
	 * @throws PaginaSinDatosException
	 */
	@SuppressWarnings("unchecked")
	public synchronized List<ObjetoTablaVO> getDatosTabla(TableTag tableTag,
			IpConfTablasHtmlParsear ipConfTablasHtmlParsear)
			throws BusinessException, PaginaSinDatosException {
		logger.debug("En getDatosTabla ");
		List<ObjetoTablaVO> lstReturn = null;
		Class<ObjetoTablaVO> claseGenerica;
		ObjetoTablaVO objeto;
		List<HashMap<String, String>> lstValores = null;
		String strValor;

		logger.debug("Verificamos las entradas");
		if (tableTag == null) {
			logger.debug("TableTag es nulo");
			throw new BusinessException("Debe de pasar la tabla a cargar");
		}
		if (ipConfTablasHtmlParsear == null) {
			logger.debug("IpConfTablasHtmlParsear es nulo");
			throw new BusinessException("No existe configuracion");
		}
		logger.debug("Extraemos los datos de la tabla");
		lstValores = getValoresTabla(tableTag);
		if (lstValores != null && !lstValores.isEmpty()) {
			logger.debug("Copiamos los datos de la tabla a la lista de VO");
			logger.debug("Creamos el tipo de objeto a regresar::" + ipConfTablasHtmlParsear.getObjetoRegresar());
			try {
				if (ipConfTablasHtmlParsear.getIpConfPropiedadesTablas() != null) {
					logger.debug("Cargamos la clase");
					claseGenerica = (Class<ObjetoTablaVO>) Class.forName(ipConfTablasHtmlParsear.getObjetoRegresar());
					lstReturn = new ArrayList<ObjetoTablaVO>();

					for (HashMap<String, String> valores : lstValores) {
						logger.debug("Creamos una nueva instancia de la clase");
						objeto = claseGenerica.newInstance();
						logger.debug("Copiamos los datos al objeto para cada propiedad configurada");

						for (IpConfPropiedadesTabla propiedadTabla : ipConfTablasHtmlParsear.getIpConfPropiedadesTablas()) {
							if (propiedadTabla != null) {
								if(valores.containsKey(propiedadTabla.getNombrePropiedad().toUpperCase())){
									strValor = valores.get(propiedadTabla.getNombrePropiedad().toUpperCase());
									logger.debug("Propiedad:::" + propiedadTabla.getNombrePropiedad().toUpperCase() + " Valor::" + strValor);
									if (propiedadTabla.getTipoDato() == Constantes.TD_TIME) {
										strValor = revisaFormatoTime(strValor);
									}
									BeanUtils.setProperty(objeto, propiedadTabla.getNombrePropiedadObjeto(), strValor);									
								}else{
									throw new BusinessException("Propiedad buscada no encontrada en la lista de valores.");
								}
							}
						}
						lstReturn.add(objeto);
					}
				} else {
					logger.debug("La tabla no tiene propiedades configuradas");
					throw new BusinessException(
							"La tabla no tiene propiedades configuradas");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				logger.error("No se encontro la clase", e);
				throw new BusinessException("No se encontro la clase ERROR::"
						+ e.getMessage());
			} catch (InstantiationException e) {
				e.printStackTrace();
				logger.error("Problemas al crear objeto de la clase::"
						+ ipConfTablasHtmlParsear.getObjetoRegresar(), e);
				throw new BusinessException(
						"Problemas al crear objeto de la clase::"
								+ ipConfTablasHtmlParsear.getObjetoRegresar()
								+ "  ERROR::" + e.getMessage());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				logger.error("Acceso ilegal a la clase"
						+ ipConfTablasHtmlParsear.getObjetoRegresar(), e);
				throw new BusinessException("Acceso ilegal a la clase::"
						+ ipConfTablasHtmlParsear.getObjetoRegresar()
						+ "  ERROR::" + e.getMessage());
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				logger.error("No se logro invocar al metodo SET", e);
				throw new BusinessException(
						"No se logro invocar al metodo SET ERROR::"
								+ e.getMessage());
			}
		} else {
			logger.debug("No se logro hacer la carga de la informaci—n, la pagina no contiene datos");
			throw new PaginaSinDatosException("No se logro hacer la carga de la informaci—n, la pagina no contiene datos");
		}

		return lstReturn;
	}

	/**
	 * Verifica si el formato de hora esta bien formado, en caso contrario lo
	 * pasa a un formato valido.
	 * 
	 * @param strValor
	 * @return
	 */
	private String revisaFormatoTime(String strValor) {
		StringTokenizer stzFormato = new StringTokenizer(strValor, ":");

		if (stzFormato.countTokens() == 1) {
			return new StringBuffer(strValor + ":00:00").toString();
		} else if (stzFormato.countTokens() == 2) {
			return new StringBuffer(strValor + ":00").toString();
		} else if (stzFormato.countTokens() == 3) {
			return strValor;
		}

		return "00:00:00";
	}

	/**
	 * Obtiene los valores de la tabla en una lista de HashMap.
	 * 
	 * @param tableTag
	 * @return
	 * @throws BusinessException
	 */
	private List<HashMap<String, String>> getValoresTabla(TableTag tableTag)
			throws BusinessException {
		// logger.debug("en getValoresTabla");
		List<HashMap<String, String>> lstReturn = null;
		SimpleNodeIterator iterNode;
		iterNode = tableTag.elements();
		Object obj;
		TagNode tagNode;
		boolean blnCargaDatos = false;
		boolean blnCargaDatosHeader = false;
		List<String> lstHeaders = null;

		// logger.debug("Iteramos hasta encontrar el <TBODY>");
		while (iterNode.hasMoreNodes()) {
			obj = iterNode.nextNode();
			if (obj != null) {
				if (obj instanceof TableRow && blnCargaDatos) {
					// logger.debug("Ya encontramos los datos, los cargamos");
					lstReturn.add(getRenglon((TableRow) obj, lstHeaders));
				} else if (obj instanceof TableRow && blnCargaDatosHeader) {
					// logger.debug("Cargamos la lista de cabeceros");
					lstHeaders = getHeaderTabla((TableRow) obj);
					blnCargaDatosHeader = false;
				}
				if (obj instanceof TagNode) {
					tagNode = (TagNode) obj;
					if (TBODY.equalsIgnoreCase(tagNode.getTagName())
							&& !blnCargaDatos) {
						blnCargaDatos = true;
						lstReturn = new ArrayList<HashMap<String, String>>();
						// logger.debug("En la siguiente iteracion cargamos los
						// datos");
					} else if (THEAD.equalsIgnoreCase(tagNode.getTagName())) {
						blnCargaDatosHeader = true;
						// logger.debug("Cargamos los cabeceros en la proxima
						// iteracion");
					}
				}
			}
		}

		logger.debug("lstReturn.size::" + lstReturn.size());
		return lstReturn;
	}

	/**
	 * Obtiene los cabeceros del GRID
	 * 
	 * @param tableRow
	 * @return
	 */
	private List<String> getHeaderTabla(TableRow tableRow) {
		// logger.debug("En getHeaderTabla");
		return getValoresRenglon(tableRow);
	}

	/**
	 * Obtiene un renglon del GRID
	 * 
	 * @param tableRow
	 * @param lstHeaders
	 * @return
	 * @throws BusinessException
	 */
	private HashMap<String, String> getRenglon(TableRow tableRow,
			List<String> lstHeaders) throws BusinessException {
		// logger.debug("En getValoresRenglon");
		HashMap<String, String> hsmReturn = null;
		List<String> lstValores;
		int intContador = 0;
		int intDuplicado = 1;

		if (lstHeaders != null && !lstHeaders.isEmpty()) {
			lstValores = getValoresRenglon(tableRow);
			if (lstValores != null && !lstValores.isEmpty()) {
				if (lstHeaders.size() != lstValores.size()) {
					throw new BusinessException("Error, la lista de Cabeceros es menor a la lista de valores leidos.");
				}
				hsmReturn = new HashMap<String, String>();
				for (String strHeader : lstHeaders) {
					if(hsmReturn.containsKey(strHeader)){
						hsmReturn.put(strHeader+intDuplicado, lstValores.get(intContador));
						intDuplicado++;
					}else{
						hsmReturn.put(strHeader, lstValores.get(intContador));	
					}
					intContador++;
				}
			} else {
				throw new BusinessException(
						"No se puede leer correctamente el renglon");
			}
		} else {
			throw new BusinessException(
					"Error la lista de encabezados no se cargo correctamente");
		}

		logger.debug("hsmReturn.size::"
				+ (hsmReturn == null ? 0 : hsmReturn.size()));
		return hsmReturn;
	}

	/**
	 * Obtiene los valores del renglon del GRID
	 * 
	 * @param tableRow
	 * @return
	 */
	private List<String> getValoresRenglon(TableRow tableRow) {
		// logger.debug("En getValoresRenglon");
		List<String> lstReturn = null;
		SimpleNodeIterator iterHijos;
		Object objHijo;
		TableColumn columna;
		TableHeader tableHeader;
		String strValor="";

		if (tableRow != null) {
			lstReturn = new ArrayList<String>();
			// logger.debug("Extraemos los hijos de este renglon");
			iterHijos = tableRow.elements();
			while (iterHijos.hasMoreNodes()) {
				objHijo = iterHijos.nextNode();
				if (objHijo instanceof TableColumn) {
					columna = (TableColumn) objHijo;
					if (columna != null) {
						// logger.debug("Iteramos hasta encontrar el texto de
						// esta columna");
						lstReturn.add(getValor(columna));
					}
				} else if (objHijo instanceof TableHeader) {
					tableHeader = (TableHeader) objHijo;
					if (tableHeader != null) {
						// logger.debug("Iteramos hasta encontrar el texto de
						// este cabecero");
						strValor = getValor(tableHeader);
						if(strValor!=null && strValor.equalsIgnoreCase("")){
							strValor="DESCONOCIDO";
						}
						lstReturn.add(strValor.toUpperCase());
					}
				}
			}
		}

		return lstReturn;
	}

	/**
	 * Obtiene el valor de una celda
	 * 
	 * @param obj
	 * @return
	 */

	/**
	 * Elimina caracteres especiales del valor obtenido en la celda
	 * 
	 * @param strCadena
	 * @return
	 */
	private String eliminaCaracEsp(String strCadena) {
		StringBuffer sbCadenaRegresar = new StringBuffer(strCadena
				.replaceAll("&Aacute;", "A")
				.replaceAll("&Eacute;", "E")
				.replaceAll("&Iacute;", "I")
				.replaceAll("&Oacute;", "O")
				.replaceAll("&Uacute;", "U")
				.replaceAll("&aacute;", "a")
				.replaceAll("&eacute;", "e")
				.replaceAll("&iacute;", "i")
				.replaceAll("&oacute;", "o")
				.replaceAll("&uacute;", "u")
				.replaceAll("&AACUTE;", "A")
				.replaceAll("&EACUTE;", "E")
				.replaceAll("&IACUTE;", "I")
				.replaceAll("&OACUTE;", "O")
				.replaceAll("&UACUTE;", "U")
				.replaceAll("&aACUTE;", "a")
				.replaceAll("&eACUTE;", "e")
				.replaceAll("&iACUTE;", "i")
				.replaceAll("&oACUTE;", "o")
				.replaceAll("&uACUTE;", "u")
				.replaceAll("ç", "A")
				.replaceAll("ƒ", "E")
				.replaceAll("ê", "I")
				.replaceAll("î", "O")
				.replaceAll("ò", "U")
				.replaceAll("‡", "a")
				.replaceAll("Ž", "e")
				.replaceAll("’", "i")
				.replaceAll("—", "o")
				.replaceAll("œ", "u")
				.replaceAll(",", "")
				.replace("&AMP;", "&"));
		return sbCadenaRegresar.toString().trim();
	}

	/**
	 * Metodo para la extraccion de datos de una tabla con presentacion de
	 * contenedor, por ejemplo:
	 * 
	 * ____________________________________ |CAMPO1|VALOR1 | CAMPO2 | VALOR2 |
	 * ------------------------------------ |CAMPO3|CALOR3 | CAMPO4 | VALOR4 |
	 * -------------------------------------
	 * 
	 * @param tableTag
	 * @param ipConfTablasHtmlParsear
	 * @return
	 * @throws BusinessException
	 * @throws PaginaSinDatosException
	 */
	@SuppressWarnings("unchecked")
	public ObjetoTablaVO getDatosTablaVO(NodeList lstNodosTableColumn,
			IpConfTablasHtmlParsear ipConfTablasHtmlParsear)
			throws BusinessException, PaginaSinDatosException {
		logger.debug("En getDatosTablaVO");
		SimpleNodeIterator iterNodos;
		TableColumn nodo;
		List<TableColumn> lstNodosFiltrados;
		boolean blnSiguiente = false;
		HashMap<String, String> hsmValores;
		HashMap<String, String> hsmPropiedadCalss;
		Class<ObjetoTablaVO> claseGenerica;
		ObjetoTablaVO objeto = null;
		String strValor;

		logger.debug("Verificamos las entradas");
		if (lstNodosTableColumn == null) {
			logger.debug("lstNodosTableColumn es nulo");
			throw new BusinessException("Debe de pasar la tabla a cargar");
		}
		if (lstNodosTableColumn.size() <= 0) {
			logger.debug("La lista de nodos esta vacia");
			throw new BusinessException("La lista de nodos esta vacia");
		}
		if (ipConfTablasHtmlParsear == null) {
			logger.debug("IpConfTablasHtmlParsear es nulo");
			throw new BusinessException("No existe configuracion");
		}

		logger.debug("Extraemos los datos de la tabla");
		blnPuntos = false;
		if (ipConfTablasHtmlParsear.getIpConfPropiedadesTablas() != null) {
			try {
				logger.debug("Si la lista de nodos contiene algun dato, cargamos en un hassmap las clases de los nodos buscados");
				if (lstNodosTableColumn.size() > 0) {
					hsmPropiedadCalss = new HashMap<String, String>();

					logger.debug("Cargamos la lista de propiedades");
					for (IpConfPropiedadesTabla vo : ipConfTablasHtmlParsear.getIpConfPropiedadesTablas()) {
						hsmPropiedadCalss.put(vo.getClaseHtml().toUpperCase(),vo.getNombrePropiedad());
					}
					iterNodos = lstNodosTableColumn.elements();
					logger.debug("Reducimos el universo de busqueda");
					lstNodosFiltrados = new ArrayList<TableColumn>();
					while (iterNodos.hasMoreNodes()) {
						nodo = (TableColumn) iterNodos.nextNode();
						if (blnSiguiente) {
							lstNodosFiltrados.add(nodo);
							blnSiguiente = false;
						} else if (nodo.getAttribute(Constantes.CLASE_HTML) != null
								&& hsmPropiedadCalss.containsKey(nodo
										.getAttribute(Constantes.CLASE_HTML)
										.toUpperCase())) {
							logger.debug("Nodo encontrado, el siguiente es el valor tambien se guarda");
							lstNodosFiltrados.add(nodo);
							blnSiguiente = true;
						}
					}
					nodo = null;
					iterNodos = null;
					hsmPropiedadCalss = null;
					logger.debug("Ya tenemos la lista filtrada lstNodosFiltrados.size::"
									+ lstNodosFiltrados.size());
					logger.debug("Creamos un HashMap con los valores de la lista filtrada");
					hsmValores = getValores(lstNodosFiltrados);
					logger
							.debug("Ya tenemos el HashMap con los valores, por cada propiedad configurada llenamos el Objeto a regresar");
					logger.debug("Creamos el tipo de objeto a regresar::"
							+ ipConfTablasHtmlParsear.getObjetoRegresar());
					logger.debug("Cargamos la clase");
					claseGenerica = (Class<ObjetoTablaVO>) Class
							.forName(ipConfTablasHtmlParsear
									.getObjetoRegresar());
					objeto = claseGenerica.newInstance();

					logger
							.debug("Copiamos los datos al objeto para cada propiedad configurada");
					for (IpConfPropiedadesTabla propiedadTabla : ipConfTablasHtmlParsear
							.getIpConfPropiedadesTablas()) {
						if (propiedadTabla != null) {
							strValor = hsmValores.get(propiedadTabla
									.getNombrePropiedad().toUpperCase());
							logger.debug("Obtenemos el valor::" + strValor);
							if (propiedadTabla.getTipoDato() == Constantes.TD_TIME) {
								strValor = revisaFormatoTime(strValor);
							}
							BeanUtils.setProperty(objeto, propiedadTabla
									.getNombrePropiedadObjeto(),
									validaValoXTipoDato(strValor,
											propiedadTabla.getTipoDato()));
						}
					}
				} else {
					logger.debug("La lista de nodos esta vacia");
					throw new PaginaSinDatosException(
							"La pagina no contiene informacion");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				logger.error("No se encontro la clase", e);
				throw new BusinessException("No se encontro la clase ERROR::"
						+ e.getMessage());
			} catch (InstantiationException e) {
				e.printStackTrace();
				logger.error("Problemas al crear objeto de la clase::"
						+ ipConfTablasHtmlParsear.getObjetoRegresar(), e);
				throw new BusinessException(
						"Problemas al crear objeto de la clase::"
								+ ipConfTablasHtmlParsear.getObjetoRegresar()
								+ "  ERROR::" + e.getMessage());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				logger.error("Acceso ilegal a la clase"
						+ ipConfTablasHtmlParsear.getObjetoRegresar(), e);
				throw new BusinessException("Acceso ilegal a la clase::"
						+ ipConfTablasHtmlParsear.getObjetoRegresar()
						+ "  ERROR::" + e.getMessage());
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				logger.error("No se logro invocar al metodo SET", e);
				throw new BusinessException(
						"No se logro invocar al metodo SET ERROR::"
								+ e.getMessage());
			}

		} else {
			logger.debug("No hay propiedades configuradas en la tabla");
			throw new BusinessException(
					"No hay propiedades configuradas en la tabla");
		}

		return objeto;
	}

	private String validaValoXTipoDato(String strValor, char tipoDato) {

		if (tipoDato == Constantes.TD_DOUBLE || tipoDato == Constantes.TD_FLOAT
				|| tipoDato == Constantes.TD_INTEGER) {
			return eliminaCaracteres(strValor);
		}

		return null;
	}

	private String eliminaCaracteres(String strValor) {
		char[] arrayChar;
		StringBuffer sbRegresa;
		boolean blnRegresa = false;

		if (strValor != null) {
			sbRegresa = new StringBuffer();

			arrayChar = strValor.toCharArray();
			for (char car : arrayChar) {
				if (car == '0' || car == '1' || car == '2' || car == '3'
						|| car == '4' || car == '5' || car == '6' || car == '7'
						|| car == '8' || car == '9' || car == '.') {
					sbRegresa.append(car);
					blnRegresa = true;
				}
			}
			if (blnRegresa) {
				return sbRegresa.toString();
			}
		}

		return null;
	}

	private HashMap<String, String> getValores(List<TableColumn> lstNodos) {
		HashMap<String, String> hsmReturn = new HashMap<String, String>();
		int intContador = 0;
		logger.debug("Para cada nodo extraemos su valor");
		String strKey = "";
		String strValor = "";

		for (TableColumn columna : lstNodos) {
			if (columna != null) {
				if ((intContador % 2) == 0) {
					strKey = getValor(columna).toUpperCase();
				} else {
					strValor = getValor(columna);
					logger.debug("El primero es el Key:" + strKey + "  Valor::"
							+ strValor);
					hsmReturn.put(strKey, strValor);
				}
			}
			intContador++;
		}
		return hsmReturn;
	}

	private String getValor(Object nodo) {
		StringBuffer sbRegresa = new StringBuffer();
		SimpleNodeIterator iterator = null;

		if (nodo instanceof TextNode) {
			return eliminaCaracEsp(((TextNode) nodo).getText());
		} else if (nodo instanceof LinkTag) {
			iterator = ((LinkTag) nodo).elements();
		} else if (nodo instanceof TableColumn) {
			iterator = ((TableColumn) nodo).elements();
		} else if (nodo instanceof TableHeader) {
			iterator = ((TableHeader) nodo).elements();
		} else if (nodo instanceof Span) {
			iterator = ((Span) nodo).elements();
		}
		if (iterator != null) {
			while (iterator.hasMoreNodes()) {
				sbRegresa.append(getValor(iterator.nextNode()));
			}
		}
		
		if(blnPuntos){
			return sbRegresa.toString().trim();
		}
		return sbRegresa.toString().replaceAll(" :", ":").replaceAll(":", "").trim();
	}

}

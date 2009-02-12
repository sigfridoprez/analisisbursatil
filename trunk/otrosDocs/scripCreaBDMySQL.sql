-- phpMyAdmin SQL Dump
-- version 2.11.0
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generaci—n: 12-02-2009 a las 00:55:46
-- Versi—n del servidor: 5.0.22
-- Versi—n de PHP: 5.2.0

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Base de datos: `kennerta_com_-_ABADM`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `CI_CONF_LINKS`
--

DROP TABLE IF EXISTS `CI_CONF_LINKS`;
CREATE TABLE `CI_CONF_LINKS` (
  `ID_CONF` int(11) NOT NULL,
  `LINK` varchar(200) NOT NULL,
  `ID_TABLA` int(11) NOT NULL,
  PRIMARY KEY  (`ID_CONF`),
  KEY `FK_CI_CONF_LINKS_1` (`ID_TABLA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `CI_CONF_LINKS`
--

INSERT INTO `CI_CONF_LINKS` (`ID_CONF`, `LINK`, `ID_TABLA`) VALUES
(1, 'http://www.bmv.com.mx/img-bmv/series.html', 1),
(2, 'http://mx.finance.yahoo.com/q?s=', 2),
(3, 'http://www.bmv.com.mx/img-bmv/resumen.html', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `CONFIGURACION_SPLIT`
--

DROP TABLE IF EXISTS `CONFIGURACION_SPLIT`;
CREATE TABLE `CONFIGURACION_SPLIT` (
  `FECHA_ALTA` date NOT NULL,
  `EMISORA` varchar(25) NOT NULL,
  `SERIE` varchar(10) NOT NULL,
  `FECHA_APLICACION` date default NULL,
  `SPLIT` int(11) NOT NULL,
  PRIMARY KEY  (`EMISORA`,`SERIE`,`FECHA_ALTA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `CONFIGURACION_SPLIT`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `DIAS_FESTIVOS`
--

DROP TABLE IF EXISTS `DIAS_FESTIVOS`;
CREATE TABLE `DIAS_FESTIVOS` (
  `FECHA` date NOT NULL,
  `DESCRIPCION` varchar(200) default NULL,
  PRIMARY KEY  (`FECHA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `DIAS_FESTIVOS`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `INDICES`
--

DROP TABLE IF EXISTS `INDICES`;
CREATE TABLE `INDICES` (
  `FECHA` date NOT NULL,
  `INDICE` varchar(25) NOT NULL,
  `ULTIMO` decimal(16,2) NOT NULL,
  `HORA` time default NULL,
  `ANTERIOR` decimal(16,2) NOT NULL,
  `PORCENTAJE` decimal(4,2) default NULL,
  `PUNTOS` decimal(8,2) default NULL,
  `MAXIMO` decimal(16,2) NOT NULL,
  `HORA_MAXIMO` time default NULL,
  `MINIMO` decimal(16,2) NOT NULL,
  `HORA_MINIMO` time default NULL,
  `DIA_HABIL` char(1) NOT NULL,
  `VAR_PCT_ULTIMO` decimal(6,4) default NULL,
  `VAR_PCT_MAXIMO` decimal(6,4) NOT NULL,
  `VAR_PCT_MINIMO` decimal(6,4) NOT NULL,
  `VAR_PCT` decimal(6,4) NOT NULL,
  `RENDIMIENTO` decimal(4,3) default NULL,
  PRIMARY KEY  (`FECHA`,`INDICE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `INDICES`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `INDICES_INTRA_DIA`
--

DROP TABLE IF EXISTS `INDICES_INTRA_DIA`;
CREATE TABLE `INDICES_INTRA_DIA` (
  `FECHA` date NOT NULL,
  `ID_CARGA` int(11) NOT NULL,
  `INDICE` varchar(25) NOT NULL,
  `ULTIMO` decimal(16,2) NOT NULL,
  `HORA` time default NULL,
  `ANTERIOR` decimal(16,2) NOT NULL,
  `PORCENTAJE` decimal(4,2) default NULL,
  `PUNTOS` decimal(8,2) default NULL,
  `MAXIMO` decimal(16,2) NOT NULL,
  `HORA_MAXIMO` time default NULL,
  `MINIMO` decimal(16,2) NOT NULL,
  `HORA_MINIMO` time default NULL,
  `VAR_PCT_ULTIMO` decimal(6,4) default NULL,
  `VAR_PCT_MAXIMO` decimal(6,4) default NULL,
  `VAR_PCT_MINIMO` decimal(6,4) default NULL,
  `VAR_PCT` decimal(6,4) default NULL,
  `RENDIMIENTO` decimal(6,4) default NULL,
  PRIMARY KEY  (`FECHA`,`ID_CARGA`,`INDICE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `INDICES_INTRA_DIA`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `IP_CONF_PROPIEDADES_TABLA`
--

DROP TABLE IF EXISTS `IP_CONF_PROPIEDADES_TABLA`;
CREATE TABLE `IP_CONF_PROPIEDADES_TABLA` (
  `ID_TABLA` int(11) NOT NULL,
  `ID_PROPIEDAD_TABLA` int(11) NOT NULL,
  `NOMBRE_PROPIEDAD` varchar(100) NOT NULL,
  `CLASE_HTML` varchar(100) default NULL,
  `NOMBRE_PROPIEDAD_OBJETO` varchar(100) NOT NULL,
  `TIPO_DATO` char(1) default NULL,
  PRIMARY KEY  (`ID_TABLA`,`ID_PROPIEDAD_TABLA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `IP_CONF_PROPIEDADES_TABLA`
--

INSERT INTO `IP_CONF_PROPIEDADES_TABLA` (`ID_TABLA`, `ID_PROPIEDAD_TABLA`, `NOMBRE_PROPIEDAD`, `CLASE_HTML`, `NOMBRE_PROPIEDAD_OBJETO`, `TIPO_DATO`) VALUES
(1, 1, 'Emisora', '', 'strEmisora', 'S'),
(1, 2, 'Serie', '', 'strSerie', 'S'),
(1, 3, 'Hora', '', 'timeHora', 'T'),
(1, 4, 'Ultimo', '', 'dblUltimo', 'D'),
(1, 5, 'PPP', '', 'dblPPP', 'D'),
(1, 6, 'Anterior', '', 'dblAnterior', 'D'),
(1, 7, 'Maximo', '', 'dblMaximo', 'D'),
(1, 8, 'Minimo', '', 'dblMinimo', 'D'),
(1, 9, 'Volumen', '', 'dblVolumen', 'D'),
(1, 10, 'Importe', '', 'dblImporte', 'D'),
(1, 11, 'Ops.', '', 'dblOps', 'D'),
(1, 12, 'Puntos', '', 'dblPuntos', 'D'),
(1, 13, '%', '', 'dblPct', 'D'),
(2, 1, 'Ultima transaccion', 'yfnc_tablehead1', 'dblUltimaTransac', 'D'),
(2, 2, 'Cierre Previo', 'yfnc_tablehead1', 'dblCierrePrevio', 'D'),
(2, 3, 'Por Pagar', 'yfnc_tablehead1', 'dblPorPagar', 'D'),
(2, 4, 'Oferta', 'yfnc_tablehead1', 'dblOferta', 'D'),
(2, 5, 'Venta', 'yfnc_tablehead1', 'dblVenta', 'D'),
(3, 1, 'DESCONOCIDO', '', 'strIndice', 'S'),
(3, 2, 'ULTIMO', '', 'dblUltimo', 'D'),
(3, 3, 'HORA', '', 'timeHora', 'T'),
(3, 4, 'ANTERIOR', NULL, 'dblAnterior', 'D'),
(3, 5, 'VARIACION %', NULL, 'dblPct', 'D'),
(3, 6, 'VARIACION PTOS.', NULL, 'dblPuntos', 'D'),
(3, 7, 'MAXIMO', NULL, 'dblMaximo', 'D'),
(3, 8, 'Hora', NULL, 'timeHoraMaximo', 'T'),
(3, 9, 'MINIMO', NULL, 'dblMinimo', 'D'),
(3, 10, 'Hora', NULL, 'timeHoraMinimo', 'T');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `IP_CONF_TABLAS_HTML_PARSEAR`
--

DROP TABLE IF EXISTS `IP_CONF_TABLAS_HTML_PARSEAR`;
CREATE TABLE `IP_CONF_TABLAS_HTML_PARSEAR` (
  `ID_TABLA` int(11) NOT NULL,
  `NOMBRE_TABLA` varchar(100) NOT NULL,
  `FORMATO_TABLA` char(1) NOT NULL,
  `OBJETO_REGRESAR` varchar(500) NOT NULL,
  PRIMARY KEY  (`ID_TABLA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `IP_CONF_TABLAS_HTML_PARSEAR`
--

INSERT INTO `IP_CONF_TABLAS_HTML_PARSEAR` (`ID_TABLA`, `NOMBRE_TABLA`, `FORMATO_TABLA`, `OBJETO_REGRESAR`) VALUES
(1, 'formaSeriesOperadas:table', 'G', 'mx.com.infracomunes.impl.vo.SeriesOperadasMercadoCapitalesVO'),
(2, 'yfncsumtab', 'D', 'mx.com.infracomunes.impl.vo.ResumenEmisoraVO'),
(3, 'formaPrincipalesIndices:table1', 'G', 'mx.com.infracomunes.impl.vo.IndicesMercadoCapitalesVO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `SERIES_OPERADAS`
--

DROP TABLE IF EXISTS `SERIES_OPERADAS`;
CREATE TABLE `SERIES_OPERADAS` (
  `FECHA` date NOT NULL,
  `EMISORA` varchar(25) NOT NULL,
  `SERIE` varchar(10) NOT NULL,
  `HORA` time default NULL,
  `ULTIMO` decimal(16,2) NOT NULL,
  `PPP` decimal(16,2) default NULL,
  `ANTERIOR` decimal(16,2) NOT NULL,
  `MAXIMO` decimal(16,2) NOT NULL,
  `MINIMO` decimal(16,2) NOT NULL,
  `VOLUMEN` decimal(12,0) default NULL,
  `IMPORTE` decimal(16,2) default NULL,
  `OPERACIONES` decimal(6,0) default NULL,
  `PUNTOS` decimal(8,2) default NULL,
  `PORCENTAJE` decimal(4,2) default NULL,
  `DIA_HABIL` char(1) NOT NULL,
  `SPLIT` int(11) NOT NULL,
  `VAR_PCT_PRECIO` decimal(6,4) default NULL,
  `VAR_PCT_MAXIMO` decimal(6,4) NOT NULL,
  `VAR_PCT_MINIMO` decimal(6,4) NOT NULL,
  `VAR_PCT` decimal(6,4) NOT NULL,
  `RENDIMIENTO` decimal(4,3) default NULL,
  `IND_BURSATILIDAD` decimal(9,6) default NULL,
  `TIPO_IND_BURSATILIDAD` char(1) default NULL,
  PRIMARY KEY  (`FECHA`,`EMISORA`,`SERIE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `SERIES_OPERADAS`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `SERIES_OPERADAS_INTRA_DIA`
--

DROP TABLE IF EXISTS `SERIES_OPERADAS_INTRA_DIA`;
CREATE TABLE `SERIES_OPERADAS_INTRA_DIA` (
  `FECHA` date NOT NULL,
  `ID_CARGA` int(11) NOT NULL,
  `EMISORA` varchar(25) NOT NULL,
  `SERIE` varchar(10) NOT NULL,
  `HORA` time NOT NULL,
  `ULTIMO` decimal(16,2) NOT NULL,
  `PPP` decimal(16,2) NOT NULL,
  `ANTERIOR` decimal(16,2) NOT NULL,
  `MAXIMO` decimal(16,2) NOT NULL,
  `HORA_MAXIMO` time NOT NULL,
  `MINIMO` decimal(16,2) NOT NULL,
  `HORA_MINIMO` time NOT NULL,
  `VOLUMEN` decimal(12,0) NOT NULL,
  `IMPORTE` decimal(16,2) NOT NULL,
  `OPERACIONES` decimal(6,0) NOT NULL,
  `PUNTOS` decimal(8,2) NOT NULL,
  `PORCENTAJE` decimal(4,2) NOT NULL,
  `SPLIT` int(11) NOT NULL,
  `VAR_PCT_PRECIO` decimal(6,4) default NULL,
  `VAR_PCT_MAXIMO` decimal(6,4) default NULL,
  `VAR_PCT_MINIMO` decimal(6,4) default NULL,
  `VAR_PCT` decimal(6,4) default NULL,
  `RENDIMIENTO` decimal(6,4) default NULL,
  PRIMARY KEY  (`FECHA`,`ID_CARGA`,`EMISORA`,`SERIE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `SERIES_OPERADAS_INTRA_DIA`
--


--
-- Filtros para las tablas descargadas (dump)
--

--
-- Filtros para la tabla `CI_CONF_LINKS`
--
ALTER TABLE `CI_CONF_LINKS`
  ADD CONSTRAINT `CI_CONF_LINKS_ibfk_1` FOREIGN KEY (`ID_TABLA`) REFERENCES `IP_CONF_TABLAS_HTML_PARSEAR` (`ID_TABLA`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `IP_CONF_PROPIEDADES_TABLA`
--
ALTER TABLE `IP_CONF_PROPIEDADES_TABLA`
  ADD CONSTRAINT `IP_CONF_PROPIEDADES_TABLA_ibfk_1` FOREIGN KEY (`ID_TABLA`) REFERENCES `IP_CONF_TABLAS_HTML_PARSEAR` (`ID_TABLA`) ON DELETE CASCADE ON UPDATE CASCADE;

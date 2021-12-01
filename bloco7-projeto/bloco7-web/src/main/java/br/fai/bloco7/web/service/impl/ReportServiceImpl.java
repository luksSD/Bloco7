package br.fai.bloco7.web.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import br.fai.bloco7.model.Anuncio;
import br.fai.bloco7.web.service.AnuncioService;
import br.fai.bloco7.web.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportServiceImpl implements ReportService {

	private final String BASE_PATH = "c:/FAI" + File.separator + "reports" + File.separator;

	private static final String JRXML_FILE = "classpath:reports/anuncios/bloco7-anuncios-report.jrxml";

	@Autowired
	private AnuncioService anuncioService;

	@Override
	public String generateAndGetPdfFilePath() {

		final List<Anuncio> anuncios = anuncioService.readAll();

		if (anuncios == null || anuncios.size() == 0) {
			return "";
		}

		File file;
		try {
			file = ResourceUtils.getFile(JRXML_FILE);

			final JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

			final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(anuncios);

			final Map<String, Object> parameters = new HashMap<String, Object>();

			final JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

			final String filePath = generateFilePath();

			JasperExportManager.exportReportToPdfFile(jasperPrint, filePath);

			return filePath;

		} catch (final FileNotFoundException | JRException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());

			return "";
		}

	}

	private String generateFilePath() {

		final SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy-HH_mm_ss");
		final Date date = new Date(System.currentTimeMillis());

		final String fileName = formatter.format(date) + "-report.pdf";

		return BASE_PATH + fileName;

	}

}

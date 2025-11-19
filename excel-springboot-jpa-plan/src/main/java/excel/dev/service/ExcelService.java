package excel.dev.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import excel.dev.entity.Produto;
import excel.dev.repository.ProdutoRepository;

@Service
public class ExcelService {

	@Autowired
	private ProdutoRepository repository;

	public void lerEImportarExcel(MultipartFile file) throws Exception {

			Workbook workbook = WorkbookFactory.create(file.getInputStream());
			Sheet sheet = workbook.getSheetAt(0); // Primeira aba

			for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Ignora o cabeçalho
				Row row = sheet.getRow(i);

				if (row == null)
					continue;

				Produto p = new Produto();
				p.setNome(row.getCell(0).getStringCellValue());
				p.setPreco(row.getCell(1).getNumericCellValue());
				p.setQuantidade((int) row.getCell(2).getNumericCellValue());

				repository.saveAndFlush(p);
			}

			workbook.close();
		
	}
}

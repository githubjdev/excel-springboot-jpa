import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import excel.dev.app.SpringBootApp;
import excel.dev.repository.ProdutoRepository;
import excel.dev.service.ExcelService;

@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = { SpringBootApp.class })
public class ExcelServiceTest {
	
	@Mock
	private MockMvc mockMvc;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ExcelService excelService;


	@Test
	void deveLerExcelDoDiretorioResources() throws Exception {

		produtoRepository.deleteAll();
		// ==== Lê o arquivo físico ====
		InputStream excelStream = getClass().getResourceAsStream("/excel/produtos.xlsx");

		MockMultipartFile file = new MockMultipartFile("file", 
				"produtos.xlsx",
				"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", 
				excelStream);

		// ==== Executa o método ====
		excelService.lerEImportarExcel(file);
		
		assertEquals(2, produtoRepository.findAll().size());
		
		produtoRepository.deleteAll();

	}

}

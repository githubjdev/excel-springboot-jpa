package excel.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import excel.dev.service.ExcelService;

@RestController
@RequestMapping("/excel")
public class ExcelController {

	@Autowired
	private ExcelService excelService;

	@PostMapping(value="/upload")
	public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) throws Exception {

		if (file.isEmpty()) {
			return ResponseEntity.badRequest().body("O arquivo está vazio.");
		}

		excelService.lerEImportarExcel(file);

		return ResponseEntity.ok("Arquivo importado com sucesso!");
	}
}

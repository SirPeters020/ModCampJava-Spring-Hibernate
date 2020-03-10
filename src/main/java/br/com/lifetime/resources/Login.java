package br.com.lifetime.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * Classe para direcionar URI's para os arquivos HTML
 *
 */
@Controller
public class Login {
	
	
	@GetMapping("/login")
    public String login() {
        return "login";
    }
	
	
	@GetMapping("/tela-datatable")
    public String dataTable() {
        return "tela-datatable";
    }
	
	@GetMapping("/cadastrocampanha")
    public String cadastroCamp() {
        return "cadastrocampanha";
    }

	@GetMapping("/cadastroestrategia")
    public String cadastroEstrategia() {
        return "cadastroestrategia";
    }
	
	@GetMapping("/editcontrolecampanha")
    public String editContCamp() {
        return "editcontrolecampanha";
    }
	
}

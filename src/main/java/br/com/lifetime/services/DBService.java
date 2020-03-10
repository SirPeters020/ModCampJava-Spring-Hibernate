package br.com.lifetime.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lifetime.domain.Aai;
import br.com.lifetime.domain.Campanha;
import br.com.lifetime.domain.Cliente;
import br.com.lifetime.domain.ControleCampanha;
import br.com.lifetime.domain.Equipe;
import br.com.lifetime.domain.Estrategia;
import br.com.lifetime.domain.SubCampanha;
import br.com.lifetime.repositories.AaiRepository;
import br.com.lifetime.repositories.ClienteRepository;
import br.com.lifetime.repositories.ControleCampanhaRepository;
import br.com.lifetime.repositories.EquipeRepository;
import br.com.lifetime.repositories.EstrategiaRepository;
import br.com.lifetime.repositories.SubCampanhaRepository;
import br.com.lifetime.resources.SugarConnection;

/**
 * 
 * Classe para instanciar classes tabelas, salvar no BD e demais regras de negocio
 * envolvendo banco de dados
 *
 */
@Service
public class DBService {

	@Autowired
	private EquipeRepository equipeRepository;
	@Autowired
	private AaiRepository aaiRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ControleCampanhaRepository controleCampanhaRepository;
	@Autowired
	private SubCampanhaRepository subCampanhaRepository;
	@Autowired
	private EstrategiaRepository estrategiaRepository;
	
	/**
	 *  metodo para instanciar classes e salva-las na BD
	 */
	@Transactional
	public void instantiateDatabase() {

		Equipe e1 = new Equipe(null, "equipe1", "Pedro", 2222);
		Equipe e2 = new Equipe(null, "pedro", "pedroso", 645465);
		Equipe e3 = new Equipe(null, "pedro extra", "pedroso extra", 56565);
		Equipe e4 = new Equipe(null, "pedro teste", "pedroso teste", 666);

		Aai a1 = new Aai(null, "pedro2", "pedro.augusto@hotmail.com", true, "02", 948393312, false, 0, e1);
		Aai a2 = new Aai(null, "pedro3", "pedro.augusto@hotmail.com", false, "02",986777122, true, 0, e2);
		Aai a3 = new Aai(null, "pedro4", "pedro.augusto@hotmail.com", true, "02", 956239481, false, 0, e1);
		Aai a4 = new Aai(null, "pedro4", "pedro.augusto@hotmail.com", true, "02", 956239481, false, 0, null);

		Cliente cli1 = new Cliente(null, 1111, "Clieton", 3333, "05", "pedro.augu@hotmail.com", "agressivo",
				"lftmNb1", true, 0, a1);
		Cliente cli2 = new Cliente(null, 2222, "Pedro", 44444, "05", "augu.pewdro@hotmail.com", "passivo",
				"lftmNb2", false, 0,  a3);
		Cliente cli3 = new Cliente(null, 3333, "Kleber", 5555, "05", "pedroson@hotmail.com", "agressivo",
				"lftmNb3", true, 0 ,a2);

		Estrategia est1 = new Estrategia(null, "nmEstrategia", "descricao");
		Estrategia est2 = new Estrategia(null, "nmEstrategia", "descricao");
		Estrategia est3 = new Estrategia(null, "nmEstrategia", "descricao");
		Estrategia est4 = new Estrategia(null, "nmEstrategia", "descricao");

		Campanha ca1 = new Campanha(null, "genericao");
		Campanha ca2 = new Campanha(null, "genericasso");
		Campanha ca3 = new Campanha(null, "pedro");

		SubCampanha sub1 = new SubCampanha(null, "nmCampanha", "riscoSugerido", "riscoEfetivo", true, 154.56,
				false, "pitchVendas", est1, 0, 0, ca1);
		SubCampanha sub2 = new SubCampanha(null, "nmCampanha", "riscoSugerido", "riscoEfetivo", true, 154.56,
				true, "pitchVendas", est3, 0, 0, ca3);
		SubCampanha sub3 = new SubCampanha(null, "nmCampanha", "riscoSugerido", "riscoEfetivo", true, 154.56,
				false, "pitchVendas", est2, 0, 0, ca2);

		ControleCampanha cca1 = new ControleCampanha(null, 23.500, "04 12 19", "06 09 16", 03333, "fomento01", "status", true, false,
				true, false, true, false, true, 0, 0, cli2, sub3);
		ControleCampanha cca2 = new ControleCampanha(null, 265.456, "04 11 18", "06 10 17", 040404, "fomento02", "status", true, false,
				true, false, true, false, true, 0, 0, cli3, sub1);
		ControleCampanha cca3 = new ControleCampanha(null, 19.566, "04 10 17", "06 11 18", 070707, "fomento03", "status", true, false,
				true, false, true, false, true, 0, 0, cli1, sub2);

		e1.getAai().addAll(Arrays.asList(a1, a3));
		e2.getAai().addAll(Arrays.asList(a2));

		a1.getCliente().addAll(Arrays.asList(cli1));
		a2.getCliente().addAll(Arrays.asList(cli3));
		a3.getCliente().addAll(Arrays.asList(cli2));

		cli1.getControleCampanha().addAll(Arrays.asList(cca1));
		cli2.getControleCampanha().addAll(Arrays.asList(cca3));
		cli3.getControleCampanha().addAll(Arrays.asList(cca2));

		sub1.getControleCamp().addAll(Arrays.asList(cca1));
		sub2.getControleCamp().addAll(Arrays.asList(cca3));
		sub3.getControleCamp().addAll(Arrays.asList(cca2));

		est1.getCampanha().addAll(Arrays.asList(sub1));
		est2.getCampanha().addAll(Arrays.asList(sub3));
		est3.getCampanha().addAll(Arrays.asList(sub2));

		equipeRepository.saveAll(Arrays.asList(e1, e1, e3, e4));
		aaiRepository.saveAll(Arrays.asList(a1, a2, a3, a4));
		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3));
		controleCampanhaRepository.saveAll(Arrays.asList(cca1, cca2, cca3));
		subCampanhaRepository.saveAll(Arrays.asList(sub1, sub2, sub3));
		estrategiaRepository.saveAll(Arrays.asList(est1, est2, est3, est4));

		
//		boolean login = SugarConnection.SugarLogin("samuel.conceicao", "SC@lftm2019");
//		System.out.println(login);
	}
}

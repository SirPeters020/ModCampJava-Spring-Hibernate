$(document).ready(function() {

  var dataSet = [
    {id:201461, nome:"Adenilso",equipe: "null", perfilxp:"Agressivo", elegivel:"Sim", fomento:"AAI", fomRealizado:"Não", opEnv: "null", perRec: "null", ordExe:"null", valor:"1.300"},
    {id:315164, nome:"Cristiane",equipe: "null", perfilxp:"Agressivo", elegivel:"Sim", fomento:"AAI", fomRealizado:"Não", opEnv: "null", perRec: "null", ordExe:"null", valor:"2.600"},
    {id:416364, nome:"Edson",equipe: "null", perfilxp:"Moderado", elegivel:"Sim", fomento:"AAI", fomRealizado:"Sim", opEnv: "null", perRec: "null", ordExe:"null", valor:"4.800"},
    {id:194356, nome:"Educelia",equipe: "null", perfilxp:"Agressivo", elegivel:"Não", fomento:"AAI", fomRealizado:"Não", opEnv: "null", perRec: "null", ordExe:"null", valor:"2.450"},
    {id:459134, nome:"Roger",equipe: "null", perfilxp:"Conservador", elegivel:"Sim", fomento:"AAI", fomRealizado:"Não", opEnv: "null", perRec: "null", ordExe:"null",  valor:"6.400"},
    {id:759421, nome:"Wheaton",equipe: "null", perfilxp:"Agressivo", elegivel:"Sim", fomento:"AAI", fomRealizado:"Sim", opEnv: "null", perRec: "null", ordExe:"null",  valor:"3.600"},
    {id:469134, nome:"Pedro",equipe: "null", perfilxp:"Agressivo", elegivel:"Sim", fomento:"AAI", fomRealizado:"Não", opEnv: "null", perRec: "null", ordExe:"null", valor:"4.700"},
    {id:794182, nome:"Alan",equipe: "null", perfilxp:"Agressivo", elegivel:"Sim", fomento:"AAI", fomRealizado:"Não", opEnv: "null", perRec: "null", ordExe:"null", valor:"1.700"},
    {id:741963, nome:"Malu",equipe: "null", perfilxp:"Moderado", elegivel:"Sim", fomento:"AAI", fomRealizado:"Sim", opEnv: "null", perRec: "null", ordExe:"null", valor:"7.400"},
    {id:369741, nome:"Lucas",equipe: "null", perfilxp:"Moderado", elegivel:"Sim", fomento:"AAI", fomRealizado:"Não", opEnv: "null", perRec: "null", ordExe:"null", valor:"1.300"},
    {id:963741, nome:"Adenilso",equipe: "null", perfilxp:"Agressivo", elegivel:"Sim", fomento:"AAI", fomRealizado:"Não", opEnv: "null", perRec: "null", ordExe:"null", valor:"4.400"},
    {id:963741, nome:"Adenilso",equipe: "null", perfilxp:"Agressivo", elegivel:"Sim", fomento:"AAI", fomRealizado:"Não", opEnv: "null", perRec: "null", ordExe:"null", valor:"4.400"},
    ];
    
  var columnDefs = [{
    data: "id",
    title: "Cod. Cliente",
    type: "readonly"
  },
  {
    data: "nome",
    title: "Nome Cliente",
    type: "readonly"
  },
  {
     data: "equipe",
     title: "Equipe",
     type: "readonly"
   },
 {
    data: "perfilxp",
    title: "Perfil XP",
    type: "readonly"
  },
 {
    data: "elegivel",
    title: "Elegivel"
  },
 {
    data: "fomento",
    title: "Fomento",
    type: "readonly"
  },
  {
     data: "fomRealizado",
     title: "Fomento Realizado"
   },
 {
    data: "opEnv",
    title: "Operação enviada",
    type: "readonly"
  },
  {
     data: "perRec",
     title: "Permissão Recebida",
     type: "readonly"
   },
   {
      data: "ordExe",
      title: "Ordem Executada",
      type: "readonly"
    },
 {
    data: "valor",
    title: "Quantidade/Valor"
  } ];

  var myTable;

  myTable = $('#example').DataTable({
    "sPaginationType": "full_numbers",
    data: dataSet,
    columns: columnDefs,
	  dom: 'Bfrtip',        // Needs button container
    select: 'single',
    responsive: true,
    processing: true,
    serverSide: false,
    altEditor: true,     // Enable altEditor
    buttons: [{
            text: 'Add',
            name: 'add'        // do not change name
          },

          {
            extend: 'selected', // Bind to Selected row
            text: 'Editar',
            name: 'edit'        // do not change name
          },

          {
            extend: 'selected', // Bind to Selected row
            text: 'Deletar',
            name: 'delete'      // do not change name
         }]
  });

  document.getElementById("example_info").style.display = "none"; // ocultando exibição de quantidades de itens exibisdos
  
  $("label").attr("id","search"); 

  var trocaTxt = document.getElementById("search").childNodes;
  trocaTxt[0].textContent = "Procurar";

  var trocaTxt = document.getElementsByClassName("dt-button buttons-selected disabled")[1];
  trocaTxt.id = "deletar-btn"

  var trocaTxt = document.getElementsByClassName("dt-button")[0];
  trocaTxt.id = "adicionar-btn";

  // let edit = document.getElementById("example_first");
  // edit.textContent = "Inicio";

  var trocaTxt = document.getElementsByClassName ("dataTables_empty").childNodes;
  trocaTxt[0].textContent = "Não Encontrado"

});


package br.com.lifetime.resources;

//import static view.sub.ReportDes.TODOS;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.lifetime.domain.Cliente;

//import model.Produto;

/**
 * Classe de conexão com o Sugar.
 */
@RestController
@RequestMapping(value = "/login")
public class SugarConnection {

	private static URL url, url2;
	private static final String urls = "";
	private static HttpURLConnection con, con2;
	public static String username = "";
	private static String password = "";
	public static String accessToken;
	private static String team_id;
	private static String user_id;
	private static String userName;
	private static String funcaoAtual;
	private static String assessorId;
	private static String reportTo;
	public static boolean is_admin = false;
	public static boolean is_asset = false;
	public static boolean is_officer = false;
	private static Cliente cliente;
	public static int status, status2;

	/**
	 * Construtor da classe.
	 */
	public SugarConnection() {
	}

	/**
	 * M�todo para validar login no Sugar.
	 * 
	 * @param usuario usuario
	 * @param senha   senha
	 * @return boolean login realizado
	 * @throws IOException
	 */
	@PostMapping
	public static Object SugarLogin(@RequestParam(value = "usuario") String usuario,
			@RequestParam(value = "senha") String senha) throws IOException {
		String inputLine;
		// setting up connection url

		username = usuario;
		password = senha;

		try {
			url = new URL(urls + "/oauth2/token");
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setConnectTimeout(20000);
			con.setReadTimeout(20000);
		} catch (java.net.MalformedURLException error) {
			System.out.println("Error on URL");
		} catch (java.io.IOException error) {
			System.out.println("Error on connection setup");
		}
		// setting parameters
		Map<String, String> parameters = new HashMap<>();
		parameters.put("grant_type", "password");
		parameters.put("client_id", "sugar");
		parameters.put("username", username);
		parameters.put("password", password);
		parameters.put("platform", "alocapro");
		// set parameters to the connection
		try {
			con.setDoOutput(true);
			OutputStream os = con.getOutputStream(); // here
			DataOutputStream out = new DataOutputStream(os);
			out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
			out.flush();
			out.close();
		} catch (java.net.ProtocolException error) {
			System.out.println("Error on Login");
			return false;
		} catch (java.io.IOException error) {
			System.out.println("Error on connection parameters");
			return false;
		}
		// executing and getting response
		try {
			status = con.getResponseCode();
			InputStream is = con.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			try {
				JSONObject Response = new JSONObject(content.toString());
				accessToken = Response.getString("access_token");
				username = usuario;
				password = senha;

			} catch (org.json.JSONException error) {
				System.out.println("Error on Json Object");
			}
			in.close();
//            if (status == 200)  
//                return true;
//            else
//                return false;

		} catch (java.io.IOException error) {
			System.out.println("Error on connection output");
			return false;
		}
		// Pegando id do time

		try {
			url = new URL(urls + "/Users?filter[0][user_name]=" + username + "");
//			url = new URL(urls + "/Employees?filter[0][id]=" + username + "");
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(20000);
			con.setReadTimeout(20000);
			con.setRequestProperty("OAuth-Token", accessToken);
			status = con.getResponseCode();

			InputStream is = con.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			StringBuffer content = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}

			JSONObject Response = new JSONObject(content.toString());
			JSONArray Records = Response.getJSONArray("records");
			JSONObject one = Records.getJSONObject(0);
			in.close();
			cliente = new Cliente();
			if ("".equals(one.getString("id")) || one.getString("id") == null)
				return false;
			else {
				JSONArray team = one.getJSONArray("team_name");
				for (int i = 0; i < team.length(); i++) {
					JSONObject t = team.getJSONObject(i);
					if (t.getString("id").equals("62e7bffc-99ab-11e9-8ff4-069e4f790264")
							|| t.getString("name").equals("OFFICERS")) {
						is_officer = true;
					}
				}


				is_admin = one.getBoolean("is_admin");
				user_id = one.getString("id");
				team_id = one.getString("default_team");

				userName = one.getString("user_name");
				funcaoAtual = one.getString("lftm_funcao_atual_c");
				assessorId = one.getString("lftm_codigo_assessor_c");

				Map<String, String> response = new HashMap<>();
                response.put("userId", user_id);
                response.put("TeamId", team_id);
				response.put("User Name", userName);
				response.put("Funcao assessor", funcaoAtual);
				response.put("login", "true");
				response.put("assessor ID", assessorId);
				response.put("reporta pra", reportTo);
				
				if (team_id.equals("801fe6be-bd9b-11e7-93d5-06e5351e95d9")) {
					is_asset = true;
				}

				return response;

			}
		} catch (org.json.JSONException error) {
			System.out.println("Error on Json Object Cliente");
		} catch (java.net.MalformedURLException error) {
			System.out.println("Error on URL");
		} catch (java.io.IOException error) {
			System.out.println("Error on connection setup");
		}
		return true;
	}

	/**
	 * Método para verificar se o login expirou.
	 * 
	 * @return boolean expirou
	 */
	public static boolean isExpired() {
		try {
			url2 = new URL(urls + "/Contacts");
			con2 = (HttpURLConnection) url2.openConnection();
			con2.setRequestMethod("GET");
			con2.setConnectTimeout(20000);
			con2.setReadTimeout(20000);
			con2.setRequestProperty("OAuth-Token", accessToken);
			status2 = con2.getResponseCode();

			if (status2 != 200)
				return true;
		} catch (IOException | IllegalStateException e) {
		}
		return false;
	}

	public Object sugarResponse() {
		try {
			String inputLine;
			url = new URL(urls + "/Users?filter[0][user_name]=" + username + "");
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(20000);
			con.setReadTimeout(20000);
			con.setRequestProperty("OAuth-Token", accessToken);
			status = con.getResponseCode();

			InputStream is = con.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			StringBuffer content = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}

			JSONObject Response = new JSONObject(content.toString());
			JSONArray Records = Response.getJSONArray("records");
			JSONObject one = Records.getJSONObject(0);
			in.close();
			cliente = new Cliente();
			if ("".equals(one.getString("id")) || one.getString("id") == null)
				return false;
			else {
				JSONArray team = one.getJSONArray("team_name");
				for (int i = 0; i < team.length(); i++) {
					JSONObject t = team.getJSONObject(i);
					if (t.getString("id").equals("62e7bffc-99ab-11e9-8ff4-069e4f790264")
							|| t.getString("name").equals("OFFICERS")) {
						is_officer = true;
					}
				}

				is_admin = one.getBoolean("is_admin");
				user_id = one.getString("id");
				team_id = one.getString("default_team");

				userName = one.getString("user_name");
				funcaoAtual = one.getString("lftm_funcao_atual_c");
				assessorId = one.getString("lftm_codigo_assessor_c");

				Map<String, String> response = new HashMap<>();
                response.put("userId", user_id);
                response.put("TeamId", team_id);
				response.put("User Name", userName);
				response.put("Funcao assessor", funcaoAtual);
				response.put("login", "true");
				response.put("assessor ID", assessorId);
				response.put("reporta pra", reportTo);
				
				
				if (team_id.equals("801fe6be-bd9b-11e7-93d5-06e5351e95d9")) {
					is_asset = true;
				}

				return response;

			}
		} catch (org.json.JSONException error) {
			System.out.println("Error on Json Object Cliente");
		} catch (java.net.MalformedURLException error) {
			System.out.println("Error on URL");
		} catch (java.io.IOException error) {
			System.out.println("Error on connection setup");
		}
		return true;
	}

}
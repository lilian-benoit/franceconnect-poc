package com.imcfr.poc.franceconnect;
import eu.ooffee.fcconnect.FcConnection;
import eu.ooffee.fcconnect.FcParamConfig;


public class EasyFranceConnect {
	private static String BASE_URI = "http://localhost:8080/franceconnect-poc";
	
	public static String SCOPE_PAR_DEFAUT = "openid";
	public static String SCOPE_AVEC_DATE_DE_NAISSANCE = "openid profile birth";
	public static String SCOPE_AVEC_REVENU_FISCAL = "openid profile birth dgfip_revenu_fiscal_de_reference";

	private static String tokenUri = "https://fcp.integ01.dev-franceconnect.fr/api/v1/token";
	private static String authorizationUri = "https://fcp.integ01.dev-franceconnect.fr/api/v1/authorize";
	private static String userInfoUri = "https://fcp.integ01.dev-franceconnect.fr/api/v1/userinfo";
	private static String logoutUri = "https://fcp.integ01.dev-franceconnect.fr/api/v1/logout";
	private static String redirectUri = "/oidc_callback";
	private static String clientId = "...";
	private static String clientSecret = "...";
	private static String verifParameterId = "nonce";
	
	public static FcConnection getConnection(String scope, String state, String verifParameterValue) {
		if (scope == null) {
			// scope par défaut
			scope = SCOPE_PAR_DEFAUT;
		}
		FcParamConfig fpc = new FcParamConfig(tokenUri, authorizationUri, BASE_URI+redirectUri, userInfoUri, clientId, clientSecret, scope, state, verifParameterId, verifParameterValue);
		FcConnection fcc = new FcConnection(fpc);
		return fcc;
	}

	public static String getLogoutRequest(FcConnection fcc, String idToken) {
		StringBuffer buf = new StringBuffer();
		buf.append(logoutUri);
		buf.append("?id_token_hint="+idToken);
		buf.append("&state=state");
		buf.append("&post_logout_redirect_uri=" + BASE_URI + "/");
		
		return buf.toString();
	}
}

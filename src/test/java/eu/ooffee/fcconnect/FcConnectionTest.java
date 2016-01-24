package eu.ooffee.fcconnect;

import org.junit.Test;

public class FcConnectionTest {
	
	@Test
	public void testConnection() throws FcConnectException{
		
		String tokenUri = "https://fcp.integ01.dev-franceconnect.fr/api/v1/token";
		String authorizationUri = "https://fcp.integ01.dev-franceconnect.fr/api/v1/authorize";
		String userInfoUri = "https://fcp.integ01.dev-franceconnect.fr/api/v1/userinfo";
		String redirectUri = "https://localhost:9043/oidc_callback";
		String clientId = "...";
		String clientSecret = "...";
		String scope = "openid profile";
		String state = "test";
		String verifParameterId = "nonce";
		String verifParameterValue = "toto";

		FcParamConfig fpc = new FcParamConfig(tokenUri, authorizationUri, redirectUri, userInfoUri, clientId, clientSecret, scope, state, verifParameterId, verifParameterValue);

		FcConnection fcc = new FcConnection(fpc);
		
		System.out.println("URI : " + fcc.getRedirectUri());

		
	}

}

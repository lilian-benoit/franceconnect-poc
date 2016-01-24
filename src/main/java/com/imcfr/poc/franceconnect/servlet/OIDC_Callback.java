package com.imcfr.poc.franceconnect.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imcfr.poc.franceconnect.EasyFranceConnect;

import eu.ooffee.fcconnect.FcConnectException;
import eu.ooffee.fcconnect.FcConnection;

/**
 * Servlet implementation class OIDC_Callback
 */
public class OIDC_Callback extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Logger LOGGER = LoggerFactory.getLogger(OIDC_Callback.class);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OIDC_Callback() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String state = request.getParameter("state");
		HttpSession session = request.getSession();
		String stateInSession = (String)session.getAttribute("state");
		if ((state != null) && (state.equals(stateInSession))) {

			FcConnection fcc = EasyFranceConnect.getConnection(EasyFranceConnect.SCOPE_PAR_DEFAUT, "state", "toto");

			String accesToken;
			String idToken;
			try {
				
				OAuthAccessTokenResponse accesTokenResponse = fcc.getAccessToken(request);
				accesToken = accesTokenResponse.getAccessToken();
				LOGGER.info("LOG : ACCES_TOKEN => " + accesToken);
				
				idToken = accesTokenResponse.getParam("id_token");
				LOGGER.info("LOG : ID_TOKEN => " + idToken);
				session.setAttribute("id_token", idToken);
				
				String userInfo = fcc.getUserInfo(accesToken);
				LOGGER.info("LOG : USERINFO => " + userInfo);
				
				session.setAttribute("individu", userInfo);
				
				response.sendRedirect(request.getContextPath() +"/secure/");
			} catch (FcConnectException e) {
				LOGGER.error("Erreur lors l'analyse de la réponse. ", e);
			}
		} else {
			LOGGER.info("LOG : attribut state différent (request / session ) : '" + state + "' / '" + stateInSession + "'");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

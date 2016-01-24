package com.imcfr.poc.franceconnect.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imcfr.poc.franceconnect.EasyFranceConnect;

import eu.ooffee.fcconnect.FcConnectException;
import eu.ooffee.fcconnect.FcConnection;

/**
 * Servlet implementation class RecupDataByFranceConnect
 */
public class RecupDataByFranceConnect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger LOGGER = LoggerFactory.getLogger(RecupDataByFranceConnect.class);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecupDataByFranceConnect() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FcConnection connection = EasyFranceConnect.getConnection(null, "state", "toto");
		try {
			OAuthAccessTokenResponse responseToken = connection.getDataByAccessToken(request);
			String accesToken = responseToken.getAccessToken();
			
			
		} catch (FcConnectException e) {
			LOGGER.error("Lors de la récupération des données", e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package com.imcfr.poc.franceconnect.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imcfr.poc.franceconnect.EasyFranceConnect;

import eu.ooffee.fcconnect.FcConnectException;
import eu.ooffee.fcconnect.FcConnection;

public class AuthentificationFranceConnectFilter implements Filter {

	private Logger LOGGER = LoggerFactory.getLogger(AuthentificationFranceConnectFilter.class);
	

	public void destroy() {
	}

	private static String PARAM_SESSION_INDIVIDU = "individu";

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpSession session = null;

		if (request instanceof HttpServletRequest) {
			HttpServletRequest httprequest = (HttpServletRequest) request;
			HttpServletResponse httpresponse = (HttpServletResponse) response;
			session = httprequest.getSession(true);

			String individu = (String) session.getAttribute(PARAM_SESSION_INDIVIDU);
			if (individu == null) {
				// Pas authentifié
				String donnees = httprequest.getParameter("revenu");
				String scope = EasyFranceConnect.SCOPE_PAR_DEFAUT;
				if (donnees != null) {
					scope = EasyFranceConnect.SCOPE_AVEC_REVENU_FISCAL;
				}
				FcConnection fcc = EasyFranceConnect.getConnection(scope, "state", "toto");
				session.setAttribute("state", "state");
				try {
					httpresponse.sendRedirect(fcc.getRedirectUri().toString());
				} catch (FcConnectException e) {
					LOGGER.error("Lors de la redirection vers France Connect", e);
					httpresponse.sendRedirect(httprequest.getContextPath() + "/error.jsp");
				}
				return;
			} else {
				// je authentifié
				filterChain.doFilter(request, response);
			}
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}

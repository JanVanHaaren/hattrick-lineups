package api;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;
import org.scribe.model.Verb;

//FIXME: credit to original author!

/**
 * @author	Jan Van Haaren
 * @date	27 June 2013
 */
public class HattrickAPI extends DefaultApi10a {

	private static final String AUTHORIZATION_URL = "https://chpp.hattrick.org/oauth/authorize.aspx";

	private static final String ACCESS_TOKEN_URL = "https://chpp.hattrick.org/oauth/access_token.ashx";

	private static final String REQUEST_TOKEN_URL = "https://chpp.hattrick.org/oauth/request_token.ashx";

	@Override
	public String getAccessTokenEndpoint() {
		return ACCESS_TOKEN_URL;
	}

	@Override
	public String getRequestTokenEndpoint() {
		return REQUEST_TOKEN_URL;
	}

	@Override
	public Verb getAccessTokenVerb() {
		return Verb.GET;
	}

	@Override
	public Verb getRequestTokenVerb() {
		return Verb.GET;
	}

	@Override
	public String getAuthorizationUrl(Token requestToken) {
		return String.format(AUTHORIZATION_URL + "?oauth_token=%s", requestToken.getToken());
	}

}

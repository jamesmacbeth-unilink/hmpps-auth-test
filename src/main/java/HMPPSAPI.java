import com.github.scribejava.core.builder.api.DefaultApi20;

/**
 * Custom API class for use with ScribeJava's ServiceBuilder.
 * Using OAuth 2.0.
 * <br>
 * Assumes hmpps-auth project running locally on port 8080.
 */
public class HMPPSAPI extends DefaultApi20 {

    protected HMPPSAPI() {}

    private static class InstanceHolder {
        private static final HMPPSAPI INSTANCE = new HMPPSAPI();
    }

    public static HMPPSAPI getInstance()
    {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "http://localhost:8080/auth/oauth/token";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return "http://localhost:8080/auth/oauth/authorize";
    }
}

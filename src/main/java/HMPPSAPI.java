import com.github.scribejava.core.builder.api.DefaultApi20;

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

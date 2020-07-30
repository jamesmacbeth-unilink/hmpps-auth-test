import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        // Example clientId and secret.
        String clientId = "elite2apiclient";
        String clientSecret = "clientsecret";

        OAuth20Service service = new ServiceBuilder(clientId)
                .apiSecret(clientSecret)
                .defaultScope("read")
                .callback("http://localhost:8081/login")
                .build(HMPPSAPI.getInstance()); // Using custom API class.

        // User should navigate to this URL and login.
        String authUrl = service.createAuthorizationUrlBuilder().build();
        System.out.println(authUrl);
        System.out.println();

        // Will be redirected with the auth code in the url params
        // enter it when prompted:
        System.out.print("code: ");
        Scanner in = new Scanner(System.in, "UTF-8");
        String code = in.nextLine();

        // Use the API to convert auth code into access token
        OAuth2AccessToken accessToken = service.getAccessToken(code);
        System.out.println("raw access token: " + accessToken.getRawResponse());
        System.out.println();

        OAuthRequest request = new OAuthRequest(Verb.GET, "http://localhost:8080/auth/api/user/me");
        service.signRequest(accessToken, request);


        try (Response response = service.execute(request))
        {
            System.out.println(response.getBody());
        }
        System.out.println();
    }
}

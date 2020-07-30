import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        String clientId = "elite2apiclient";
        String clientSecret = "clientsecret";

        OAuth20Service service = new ServiceBuilder(clientId)
                .apiSecret(clientSecret)
                .defaultScope("read")
                .callback("http://localhost:8081/login")
                .build(HMPPSAPI.getInstance());

        String authUrl = service.createAuthorizationUrlBuilder().build();
        System.out.println(authUrl);

        System.out.print("code: ");
        Scanner in = new Scanner(System.in, "UTF-8");
        String code = in.nextLine();

        OAuth2AccessToken accessToken = service.getAccessToken(code);
        System.out.println("raw access token: " + accessToken.getRawResponse());
    }
}

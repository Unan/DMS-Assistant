import {AuthServiceConfig, GoogleLoginProvider} from "angularx-social-login";
import {environment} from "../environments/environment";

let config = new AuthServiceConfig([
  {
    id: GoogleLoginProvider.PROVIDER_ID,
    provider: new GoogleLoginProvider(environment.googleProviderID)
  }
]);

export function provideConfig() {
  return config;
}

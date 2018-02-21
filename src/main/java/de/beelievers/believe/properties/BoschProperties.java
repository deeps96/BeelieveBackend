package de.beelievers.believe.properties;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties("bosch")
@Getter @Setter
public class BoschProperties {

    @NotBlank
    private String consumerID, secret, offeringID, marketplaceURI;

}
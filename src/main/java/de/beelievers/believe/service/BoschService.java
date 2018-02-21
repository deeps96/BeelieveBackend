package de.beelievers.believe.service;

import com.fasterxml.jackson.databind.JsonNode;
import de.beelievers.believe.properties.BoschProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.bigiot.lib.Consumer;
import org.eclipse.bigiot.lib.offering.AccessParameters;
import org.eclipse.bigiot.lib.offering.AccessResponse;
import org.eclipse.bigiot.lib.offering.Offering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
public class BoschService {

    private BoschProperties properties;
    private Offering offering;

    @Autowired
    public BoschService(BoschProperties properties) throws IOException, ExecutionException, InterruptedException {
        setProperties(properties);
        initOffering();
    }

    private void initOffering() throws IOException, ExecutionException, InterruptedException {
        Consumer consumer = new Consumer(getProperties().getConsumerID(), getProperties().getMarketplaceURI())
                .authenticate(getProperties().getSecret());
        setOffering(consumer.subscribeByOfferingId(getProperties().getOfferingID()).get());
    }

    public String requestAirQualityForLast24H(long longitude, long latitude, int radius) throws ExecutionException,
            InterruptedException {
        AccessParameters accessParameters = AccessParameters.create();
        accessParameters
                .addRdfTypeValue("schema:latitude", latitude)
                .addRdfTypeValue("schema:longitude", longitude)
                .addRdfTypeValue("schema:geoRadius", radius);
        return getOffering().accessOneTime(accessParameters).get().getPrettyPrint();
    }

}

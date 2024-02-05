package guru.springframework.spring6resttemplate.client;

import guru.springframework.spring6resttemplate.model.BeerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClientImpl beerClient;

    @Test
    void listBeerById() {

        Page<BeerDTO> beerDTOS = beerClient.listBeers();
        BeerDTO dto = beerDTOS.getContent().get(0);

        BeerDTO byId = beerClient.getBeerById(dto.getId());

        assertThat(byId).isNotNull();
    }

    @Test
    void listBeersNoName() {

        beerClient.listBeers(null, null, null,null ,null);
    }

    @Test
    void listBeers() {

        beerClient.listBeers("ALE", null, null,null ,null);
    }

}
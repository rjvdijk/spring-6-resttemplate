package guru.springframework.spring6resttemplate.client;

import guru.springframework.spring6resttemplate.model.BeerDTO;
import guru.springframework.spring6resttemplate.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClientImpl beerClient;

    @Test
    void testUpdateBeer() {
        BeerDTO newDto = BeerDTO.builder()
                .beerName("Mango Bobs 2")
                .beerStyle(BeerStyle.IPA)
                .quantityOnHand(500)
                .upc("123456")
                .price(new BigDecimal( "10.99"))
                .build();
        BeerDTO beerDto = beerClient.createBeer(newDto);
        final String newName = "Mango Bobs 3";
        beerDto.setBeerName(newName);

        BeerDTO updatedBeer = beerClient.updateBeer(beerDto);

        assertThat(newName).isEqualTo(updatedBeer.getBeerName());
    }

    @Test
    void testCreateBeer() {
        BeerDTO newDto = BeerDTO.builder()
                .beerName("Mango Bobs")
                .beerStyle(BeerStyle.IPA)
                .quantityOnHand(500)
                .upc("123456")
                .price(new BigDecimal("10.99"))
                .build();

        BeerDTO savedDto = beerClient.createBeer(newDto);

        assertThat(savedDto).isNotNull();
    }

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
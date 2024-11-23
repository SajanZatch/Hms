package com.hms.services;

import com.hms.Service.CountryService;
import com.hms.entity.Country;
import com.hms.repository.CountryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class countryServiceTest {


    @Mock
    private CountryRepository countryRepo;

    @InjectMocks
    private CountryService countryService;

    @Test
    void testSaveCountry() {
        // Arrange
        Country country = new Country();
        country.setId(1L);
        country.setName("India");

        // Mocking the repository behavior
        when(countryRepo.save(country)).thenReturn(country);

        // Act
        Country savedCountry = countryRepo.save(country);

        // Assert
        assertNotNull(savedCountry);
        assertEquals("India", savedCountry.getName());
        assertEquals(1L, savedCountry.getId());

        // Verify that the save method was called exactly once
        verify(countryRepo, times(1)).save(country);
    }
}

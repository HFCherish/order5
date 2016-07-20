package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

import static com.thoughtworks.ketsu.support.TestHelper.productJsonForTest;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(ApiTestRunner.class)
public class ProductRepositoryTest {
    @Inject
    ProductRepository productRepository;

    @Test
    public void should_create_and_get_product() {
        Map productInfo = productJsonForTest();

        productRepository.save(productInfo);
        Long id = Long.valueOf(productInfo.get("id").toString());
        Optional<Product> fetched = productRepository.findById(id);

        assertThat(fetched.isPresent(), is(true));
        assertThat(fetched.get().getId(), is(id));

    }
}
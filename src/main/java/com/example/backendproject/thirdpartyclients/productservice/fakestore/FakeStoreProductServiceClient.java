package com.example.backendproject.thirdpartyclients.productservice.fakestore;

import com.example.backendproject.dtos.GenericProductDto;
import com.example.backendproject.exceptions.NotFoundException;
import com.example.backendproject.models.Product;
import com.example.backendproject.thirdpartyclients.productservice.ThirdPartyProductServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreProductServiceClient implements ThirdPartyProductServiceClient {
    private String fakeStoreProductsRequestUrl;
    private String fakeStoreSpecificProductRequestUrl;
    private String getAllCategoriesUrl = "https://fakestoreapi.com/products/categories";
    private String getProductsinCategoryUrl = "https://fakestoreapi.com/products/category/{category}";

    public FakeStoreProductServiceClient(@Value("${fakestore.api.url}") String fakeStoreApiUrl,
                                         @Value("${fakestore.api.paths.product}") String fakeStoreProductsApiPath){
        this.fakeStoreProductsRequestUrl = fakeStoreApiUrl + fakeStoreProductsApiPath;
        this.fakeStoreSpecificProductRequestUrl = fakeStoreApiUrl + fakeStoreProductsApiPath + "/{id}";
    }

    @Override
    public FakeStoreProductDto getProductById(Integer id) throws NotFoundException {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.getForEntity(fakeStoreSpecificProductRequestUrl, FakeStoreProductDto.class, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        if(fakeStoreProductDto == null){
            throw new NotFoundException("Product with id: " + id + " doesn't exist.");
        }
        return fakeStoreProductDto;
    }

    @Override
    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<FakeStoreProductDto[]> response =
                restTemplate.getForEntity(fakeStoreProductsRequestUrl, FakeStoreProductDto[].class);

        List<FakeStoreProductDto> fakeStoreProductDtos = Arrays.asList(response.getBody());
        return fakeStoreProductDtos;
    }

    @Override
    public FakeStoreProductDto addNewProduct(Product product) {
        RestTemplate restTemplate = new RestTemplate();

//        ResponseEntity<FakeStoreProductDto> response =
//                restTemplate.postForEntity(fakeStoreProductsRequestUrl, product, FakeStoreProductDto.class);

        HttpEntity<Product> request = new HttpEntity<>(product);
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);

        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.execute(fakeStoreProductsRequestUrl, HttpMethod.POST,
                        requestCallback, responseExtractor);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }

    @Override
    public FakeStoreProductDto updateProduct(int id, GenericProductDto product) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<GenericProductDto> request = new HttpEntity<>(product);
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);

        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.execute(fakeStoreSpecificProductRequestUrl, HttpMethod.PUT,
                        requestCallback, responseExtractor, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }

    @Override
    public FakeStoreProductDto deleteProduct(Integer id) {
        RestTemplate restTemplate = new RestTemplate();

//        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);

        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.execute(fakeStoreSpecificProductRequestUrl, HttpMethod.DELETE,
                        null, responseExtractor, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }

    @Override
    public ResponseEntity<?> getAllCategories() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String[]> responseEntity =
                restTemplate.getForEntity(getAllCategoriesUrl, String[].class);

        return responseEntity;
    }

    @Override
    public FakeStoreProductDto[] getProductsinCategory(String category) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<FakeStoreProductDto[]> response =
                restTemplate.getForEntity(getProductsinCategoryUrl, FakeStoreProductDto[].class, category);

        FakeStoreProductDto[] fakeStoreProductDtos = response.getBody();

        return fakeStoreProductDtos;
    }
}

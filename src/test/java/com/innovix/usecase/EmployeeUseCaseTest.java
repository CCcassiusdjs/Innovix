package com.innovix.usecase;

import com.innovix.dto.PersonDTO;
import com.innovix.entity.*;
import com.innovix.service.*;
import com.innovix.usecase.EmployeeUseCase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class EmployeeUseCaseTest {

    @Mock
    private PersonService personService;

    @Mock
    private AddressService addressService;

    @Mock
    private OrderService orderService;

    @Mock
    private ProductService productService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private StoreService storeService;

    @Mock
    private PromotionService promotionService;

    @InjectMocks
    private EmployeeUseCase employeeUseCase;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @org.junit.jupiter.api.Test
    void testListAllEmployees() {
        // Simulate data
        PersonDTO employee1 = new PersonDTO();
        employee1.setId(1L);
        PersonDTO employee2 = new PersonDTO();
        employee2.setId(2L);
        List<PersonDTO> employees = Arrays.asList(employee1, employee2);

        // Mock behavior
        when(personService.listAllByType(PersonType.EMPLOYEE)).thenReturn(employees);

        // Test
        List<PersonDTO> result = employeeUseCase.listAllEmployees();
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        // Verify interactions
        verify(personService, times(1)).listAllByType(PersonType.EMPLOYEE);
        verifyNoMoreInteractions(personService);
    }

    @org.junit.jupiter.api.Test
    void testGetEmployeeById() {
        // Simulate data
        Long id = 1L;
        PersonDTO employee = new PersonDTO();
        employee.setId(id);

        // Mock behavior
        when(personService.findById(id)).thenReturn(employee);

        // Test
        PersonDTO result = employeeUseCase.getEmployeeById(id);
        assertEquals(id, result.getId());

        // Verify interactions
        verify(personService, times(1)).findById(id);
        verifyNoMoreInteractions(personService);
    }

    @org.junit.jupiter.api.Test
    void testRegisterEmployee() {
        // Simulate data
        PersonDTO personDTO = new PersonDTO();
        personDTO.setEmail("test@example.com");

        // Test
        employeeUseCase.registerEmployee(personDTO);

        // Verify interactions
        verify(personService, times(1)).registerEmployee(personDTO);
        verifyNoMoreInteractions(personService);
    }

    @org.junit.jupiter.api.Test
    void testListAllProducts() {
        // Simulate data
        Product product1 = new Product();
        product1.setId(1L);
        Product product2 = new Product();
        product2.setId(2L);
        List<Product> products = Arrays.asList(product1, product2);

        // Mock behavior
        when(productService.listAll()).thenReturn(products);

        // Test
        List<Product> result = employeeUseCase.listAllProducts();
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        // Verify interactions
        verify(productService, times(1)).listAll();
        verifyNoMoreInteractions(productService);
    }

    @org.junit.jupiter.api.Test
    void testCreateProduct() {
        // Simulate data
        Product product = new Product();
        product.setName("Test Product");

        // Mock behavior
        when(productService.save(any(Product.class))).thenReturn(product);

        // Test
        Product result = employeeUseCase.createProduct(product);
        assertEquals("Test Product", result.getName());

        // Verify interactions
        verify(productService, times(1)).save(product);
        verifyNoMoreInteractions(productService);
    }

    @org.junit.jupiter.api.Test
    void testGetProductById() {
        // Simulate data
        Long id = 1L;
        Product product = new Product();
        product.setId(id);

        // Mock behavior
        when(productService.findById(id)).thenReturn(product);

        // Test
        Product result = employeeUseCase.getProductById(id);
        assertEquals(id, result.getId());

        // Verify interactions
        verify(productService, times(1)).findById(id);
        verifyNoMoreInteractions(productService);
    }

    @org.junit.jupiter.api.Test
    void testListProductsByNameContaining() {
        // Simulate data
        String name = "Test";
        Product product1 = new Product();
        product1.setId(1L);
        Product product2 = new Product();
        product2.setId(2L);
        List<Product> products = Arrays.asList(product1, product2);

        // Mock behavior
        when(productService.findByNameContaining(name)).thenReturn(products);

        // Test
        List<Product> result = employeeUseCase.listProductsByNameContaining(name);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        // Verify interactions
        verify(productService, times(1)).findByNameContaining(name);
        verifyNoMoreInteractions(productService);
    }

    @org.junit.jupiter.api.Test
    void testListProductsByCategoryId() {
        // Simulate data
        Long categoryId = 1L;
        Product product1 = new Product();
        product1.setId(1L);
        Product product2 = new Product();
        product2.setId(2L);
        List<Product> products = Arrays.asList(product1, product2);

        // Mock behavior
        when(productService.findByCategoryId(categoryId)).thenReturn(products);

        // Test
        List<Product> result = employeeUseCase.listProductsByCategoryId(categoryId);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        // Verify interactions
        verify(productService, times(1)).findByCategoryId(categoryId);
        verifyNoMoreInteractions(productService);
    }

    @org.junit.jupiter.api.Test
    void testListProductsByPriceBetween() {
        // Simulate data
        double minPrice = 10.0;
        double maxPrice = 100.0;
        Product product1 = new Product();
        product1.setId(1L);
        Product product2 = new Product();
        product2.setId(2L);
        List<Product> products = Arrays.asList(product1, product2);

        // Mock behavior
        when(productService.findByPriceBetween(minPrice, maxPrice)).thenReturn(products);

        // Test
        List<Product> result = employeeUseCase.listProductsByPriceBetween(minPrice, maxPrice);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        // Verify interactions
        verify(productService, times(1)).findByPriceBetween(minPrice, maxPrice);
        verifyNoMoreInteractions(productService);
    }

    @org.junit.jupiter.api.Test
    void testListProductsBySize() {
        // Simulate data
        String size = "M";
        Product product1 = new Product();
        product1.setId(1L);
        Product product2 = new Product();
        product2.setId(2L);
        List<Product> products = Arrays.asList(product1, product2);

        // Mock behavior
        when(productService.findBySize(size)).thenReturn(products);

        // Test
        List<Product> result = employeeUseCase.listProductsBySize(size);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        // Verify interactions
        verify(productService, times(1)).findBySize(size);
        verifyNoMoreInteractions(productService);
    }

    @org.junit.jupiter.api.Test
    void testListProductsByMaterial() {
        // Simulate data
        String material = "Cotton";
        Product product1 = new Product();
        product1.setId(1L);
        Product product2 = new Product();
        product2.setId(2L);
        List<Product> products = Arrays.asList(product1, product2);

        // Mock behavior
        when(productService.findByMaterial(material)).thenReturn(products);

        // Test
        List<Product> result = employeeUseCase.listProductsByMaterial(material);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        // Verify interactions
        verify(productService, times(1)).findByMaterial(material);
        verifyNoMoreInteractions(productService);
    }

    @org.junit.jupiter.api.Test
    void testListProductsByPromotionId() {
        // Simulate data
        Long promotionId = 1L;
        Product product1 = new Product();
        product1.setId(1L);
        Product product2 = new Product();
        product2.setId(2L);
        List<Product> products = Arrays.asList(product1, product2);

        // Mock behavior
        when(productService.findByPromotionId(promotionId)).thenReturn(products);

        // Test
        List<Product> result = employeeUseCase.listProductsByPromotionId(promotionId);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        // Verify interactions
        verify(productService, times(1)).findByPromotionId(promotionId);
        verifyNoMoreInteractions(productService);
    }

    @org.junit.jupiter.api.Test
    void testDeleteProduct() {
        // Mock behavior
        doNothing().when(productService).delete(anyLong());

        // Test
        employeeUseCase.deleteProduct(1L);

        // Verify interactions
        verify(productService, times(1)).delete(1L);
        verifyNoMoreInteractions(productService);
    }

    @org.junit.jupiter.api.Test
    void testListAllCategories() {
        // Simulate data
        Category category1 = new Category();
        category1.setId(1L);
        Category category2 = new Category();
        category2.setId(2L);
        List<Category> categories = Arrays.asList(category1, category2);

        // Mock behavior
        when(categoryService.listAll()).thenReturn(categories);

        // Test
        List<Category> result = employeeUseCase.listAllCategories();
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        // Verify interactions
        verify(categoryService, times(1)).listAll();
        verifyNoMoreInteractions(categoryService);
    }

    @org.junit.jupiter.api.Test
    void testCreateCategory() {
        // Simulate data
        Category category = new Category();
        category.setName("Test Category");

        // Mock behavior
        when(categoryService.save(any(Category.class))).thenReturn(category);

        // Test
        Category result = employeeUseCase.createCategory(category);
        assertEquals("Test Category", result.getName());

        // Verify interactions
        verify(categoryService, times(1)).save(category);
        verifyNoMoreInteractions(categoryService);
    }

    @org.junit.jupiter.api.Test
    void testGetCategoryById() {
        // Simulate data
        Long id = 1L;
        Category category = new Category();
        category.setId(id);

        // Mock behavior
        when(categoryService.findById(id)).thenReturn(category);

        // Test
        Category result = employeeUseCase.getCategoryById(id);
        assertEquals(id, result.getId());

        // Verify interactions
        verify(categoryService, times(1)).findById(id);
        verifyNoMoreInteractions(categoryService);
    }

    @org.junit.jupiter.api.Test
    void testGetCategoryByName() {
        // Simulate data
        String name = "Test Category";
        Category category = new Category();
        category.setName(name);

        // Mock behavior
        when(categoryService.findByName(name)).thenReturn(category);

        // Test
        Category result = employeeUseCase.getCategoryByName(name);
        assertEquals(name, result.getName());

        // Verify interactions
        verify(categoryService, times(1)).findByName(name);
        verifyNoMoreInteractions(categoryService);
    }

    @org.junit.jupiter.api.Test
    void testDeleteCategory() {
        // Mock behavior
        doNothing().when(categoryService).delete(anyLong());

        // Test
        employeeUseCase.deleteCategory(1L);

        // Verify interactions
        verify(categoryService, times(1)).delete(1L);
        verifyNoMoreInteractions(categoryService);
    }

    @org.junit.jupiter.api.Test
    void testListAllStores() {
        // Simulate data
        Store store1 = new Store();
        store1.setId(1L);
        Store store2 = new Store();
        store2.setId(2L);
        List<Store> stores = Arrays.asList(store1, store2);

        // Mock behavior
        when(storeService.listAll()).thenReturn(stores);

        // Test
        List<Store> result = employeeUseCase.listAllStores();
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        // Verify interactions
        verify(storeService, times(1)).listAll();
        verifyNoMoreInteractions(storeService);
    }

    @org.junit.jupiter.api.Test
    void testCreateStore() {
        // Simulate data
        Store store = new Store();
        store.setStoreName("Test Store");

        // Mock behavior
        when(storeService.save(any(Store.class))).thenReturn(store);

        // Test
        Store result = employeeUseCase.createStore(store);
        assertEquals("Test Store", result.getStoreName());

        // Verify interactions
        verify(storeService, times(1)).save(store);
        verifyNoMoreInteractions(storeService);
    }

    @org.junit.jupiter.api.Test
    void testGetStoreById() {
        // Simulate data
        Long id = 1L;
        Store store = new Store();
        store.setId(id);

        // Mock behavior
        when(storeService.findById(id)).thenReturn(store);

        // Test
        Store result = employeeUseCase.getStoreById(id);
        assertEquals(id, result.getId());

        // Verify interactions
        verify(storeService, times(1)).findById(id);
        verifyNoMoreInteractions(storeService);
    }

    @org.junit.jupiter.api.Test
    void testGetStoreByName() {
        // Simulate data
        String name = "Test Store";
        Store store = new Store();
        store.setStoreName(name);

        // Mock behavior
        when(storeService.findByName(name)).thenReturn(store);

        // Test
        Store result = employeeUseCase.getStoreByName(name);
        assertEquals(name, result.getStoreName());

        // Verify interactions
        verify(storeService, times(1)).findByName(name);
        verifyNoMoreInteractions(storeService);
    }

    @org.junit.jupiter.api.Test
    void testGetStoreByCnpj() {
        // Simulate data
        String cnpj = "12345678901234";
        Store store = new Store();
        store.setStoreCnpj(cnpj);

        // Mock behavior
        when(storeService.findByCnpj(cnpj)).thenReturn(store);

        // Test
        Store result = employeeUseCase.getStoreByCnpj(cnpj);
        assertEquals(cnpj, result.getStoreCnpj());

        // Verify interactions
        verify(storeService, times(1)).findByCnpj(cnpj);
        verifyNoMoreInteractions(storeService);
    }

    @org.junit.jupiter.api.Test
    void testListStoresByCity() {
        // Simulate data
        String city = "Test City";
        Store store1 = new Store();
        store1.setId(1L);
        Store store2 = new Store();
        store2.setId(2L);
        List<Store> stores = Arrays.asList(store1, store2);

        // Mock behavior
        when(storeService.findByCity(city)).thenReturn(stores);

        // Test
        List<Store> result = employeeUseCase.listStoresByCity(city);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        // Verify interactions
        verify(storeService, times(1)).findByCity(city);
        verifyNoMoreInteractions(storeService);
    }

    @org.junit.jupiter.api.Test
    void testListStoresByState() {
        // Simulate data
        String state = "Test State";
        Store store1 = new Store();
        store1.setId(1L);
        Store store2 = new Store();
        store2.setId(2L);
        List<Store> stores = Arrays.asList(store1, store2);

        // Mock behavior
        when(storeService.findByState(state)).thenReturn(stores);

        // Test
        List<Store> result = employeeUseCase.listStoresByState(state);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        // Verify interactions
        verify(storeService, times(1)).findByState(state);
        verifyNoMoreInteractions(storeService);
    }

    @org.junit.jupiter.api.Test
    void testListStoresByCountry() {
        // Simulate data
        String country = "Test Country";
        Store store1 = new Store();
        store1.setId(1L);
        Store store2 = new Store();
        store2.setId(2L);
        List<Store> stores = Arrays.asList(store1, store2);

        // Mock behavior
        when(storeService.findByCountry(country)).thenReturn(stores);

        // Test
        List<Store> result = employeeUseCase.listStoresByCountry(country);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        // Verify interactions
        verify(storeService, times(1)).findByCountry(country);
        verifyNoMoreInteractions(storeService);
    }

    @org.junit.jupiter.api.Test
    void testListStoresByEmployeeId() {
        // Simulate data
        Long employeeId = 1L;
        Store store1 = new Store();
        store1.setId(1L);
        Store store2 = new Store();
        store2.setId(2L);
        List<Store> stores = Arrays.asList(store1, store2);

        // Mock behavior
        when(storeService.findByEmployeeId(employeeId)).thenReturn(stores);

        // Test
        List<Store> result = employeeUseCase.listStoresByEmployeeId(employeeId);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        // Verify interactions
        verify(storeService, times(1)).findByEmployeeId(employeeId);
        verifyNoMoreInteractions(storeService);
    }

    @org.junit.jupiter.api.Test
    void testDeleteStore() {
        // Mock behavior
        doNothing().when(storeService).delete(anyLong());

        // Test
        employeeUseCase.deleteStore(1L);

        // Verify interactions
        verify(storeService, times(1)).delete(1L);
        verifyNoMoreInteractions(storeService);
    }

    @org.junit.jupiter.api.Test
    void testListAllPromotions() {
        // Simulate data
        Promotion promotion1 = new Promotion();
        promotion1.setId(1L);
        Promotion promotion2 = new Promotion();
        promotion2.setId(2L);
        List<Promotion> promotions = Arrays.asList(promotion1, promotion2);

        // Mock behavior
        when(promotionService.listAll()).thenReturn(promotions);

        // Test
        List<Promotion> result = employeeUseCase.listAllPromotions();
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        // Verify interactions
        verify(promotionService, times(1)).listAll();
        verifyNoMoreInteractions(promotionService);
    }

    @org.junit.jupiter.api.Test
    void testCreatePromotion() {
        // Simulate data
        Promotion promotion = new Promotion();
        promotion.setDescription("Test Promotion");

        // Mock behavior
        when(promotionService.save(any(Promotion.class))).thenReturn(promotion);

        // Test
        Promotion result = employeeUseCase.createPromotion(promotion);
        assertEquals("Test Promotion", result.getDescription());

        // Verify interactions
        verify(promotionService, times(1)).save(promotion);
        verifyNoMoreInteractions(promotionService);
    }

    @org.junit.jupiter.api.Test
    void testGetPromotionById() {
        // Simulate data
        Long id = 1L;
        Promotion promotion = new Promotion();
        promotion.setId(id);

        // Mock behavior
        when(promotionService.findById(id)).thenReturn(promotion);

        // Test
        Promotion result = employeeUseCase.getPromotionById(id);
        assertEquals(id, result.getId());

        // Verify interactions
        verify(promotionService, times(1)).findById(id);
        verifyNoMoreInteractions(promotionService);
    }

    @org.junit.jupiter.api.Test
    void testListPromotionsBySeason() {
        // Simulate data
        String season = "Summer";
        Promotion promotion1 = new Promotion();
        promotion1.setId(1L);
        Promotion promotion2 = new Promotion();
        promotion2.setId(2L);
        List<Promotion> promotions = Arrays.asList(promotion1, promotion2);

        // Mock behavior
        when(promotionService.findBySeason(season)).thenReturn(promotions);

        // Test
        List<Promotion> result = employeeUseCase.listPromotionsBySeason(season);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        // Verify interactions
        verify(promotionService, times(1)).findBySeason(season);
        verifyNoMoreInteractions(promotionService);
    }

    @org.junit.jupiter.api.Test
    void testListPromotionsByInitLocalDateBefore() {
        // Simulate data
        LocalDate date = LocalDate.now();
        Promotion promotion1 = new Promotion();
        promotion1.setId(1L);
        Promotion promotion2 = new Promotion();
        promotion2.setId(2L);
        List<Promotion> promotions = Arrays.asList(promotion1, promotion2);

        // Mock behavior
        when(promotionService.findByInitLocalDateBefore(date)).thenReturn(promotions);

        // Test
        List<Promotion> result = employeeUseCase.listPromotionsByInitLocalDateBefore(date);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        // Verify interactions
        verify(promotionService, times(1)).findByInitLocalDateBefore(date);
        verifyNoMoreInteractions(promotionService);
    }

    @org.junit.jupiter.api.Test
    void testListPromotionsByEndLocalDateAfter() {
        // Simulate data
        LocalDate date = LocalDate.now();
        Promotion promotion1 = new Promotion();
        promotion1.setId(1L);
        Promotion promotion2 = new Promotion();
        promotion2.setId(2L);
        List<Promotion> promotions = Arrays.asList(promotion1, promotion2);

        // Mock behavior
        when(promotionService.findByEndLocalDateAfter(date)).thenReturn(promotions);

        // Test
        List<Promotion> result = employeeUseCase.listPromotionsByEndLocalDateAfter(date);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        // Verify interactions
        verify(promotionService, times(1)).findByEndLocalDateAfter(date);
        verifyNoMoreInteractions(promotionService);
    }

    @org.junit.jupiter.api.Test
    void testListPromotionsByEmployeeId() {
        // Simulate data
        Long employeeId = 1L;
        Promotion promotion1 = new Promotion();
        promotion1.setId(1L);
        Promotion promotion2 = new Promotion();
        promotion2.setId(2L);
        List<Promotion> promotions = Arrays.asList(promotion1, promotion2);

        // Mock behavior
        when(promotionService.findByEmployeeId(employeeId)).thenReturn(promotions);

        // Test
        List<Promotion> result = employeeUseCase.listPromotionsByEmployeeId(employeeId);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        // Verify interactions
        verify(promotionService, times(1)).findByEmployeeId(employeeId);
        verifyNoMoreInteractions(promotionService);
    }

    @org.junit.jupiter.api.Test
    void testDeletePromotion() {
        // Mock behavior
        doNothing().when(promotionService).delete(anyLong());

        // Test
        employeeUseCase.deletePromotion(1L);

        // Verify interactions
        verify(promotionService, times(1)).delete(1L);
        verifyNoMoreInteractions(promotionService);
    }

    @org.junit.jupiter.api.Test
    void testListAllOrders() {
        // Simulate data
        PurchaseOrder order1 = new PurchaseOrder();
        order1.setId(1L);
        PurchaseOrder order2 = new PurchaseOrder();
        order2.setId(2L);
        List<PurchaseOrder> orders = Arrays.asList(order1, order2);

        // Mock behavior
        when(orderService.listAll()).thenReturn(orders);

        // Test
        List<PurchaseOrder> result = employeeUseCase.listAllOrders();
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        // Verify interactions
        verify(orderService, times(1)).listAll();
        verifyNoMoreInteractions(orderService);
    }

    @org.junit.jupiter.api.Test
    void testGetOrderById() {
        // Simulate data
        Long id = 1L;
        PurchaseOrder order = new PurchaseOrder();
        order.setId(id);

        // Mock behavior
        when(orderService.findById(id)).thenReturn(order);

        // Test
        PurchaseOrder result = employeeUseCase.getOrderById(id);
        assertEquals(id, result.getId());

        // Verify interactions
        verify(orderService, times(1)).findById(id);
        verifyNoMoreInteractions(orderService);
    }

    @org.junit.jupiter.api.Test
    void testUserExists() {
        // Mock behavior
        when(personService.userExists(anyString())).thenReturn(true);

        // Test
        boolean result = employeeUseCase.userExists("test@example.com");
        assertTrue(result);

        // Verify interactions
        verify(personService, times(1)).userExists("test@example.com");
        verifyNoMoreInteractions(personService);
    }

    @org.junit.jupiter.api.Test
    void testListOrdersByStatus() {
        // Simulate data
        String status = "Pending";
        PurchaseOrder order1 = new PurchaseOrder();
        order1.setId(1L);
        PurchaseOrder order2 = new PurchaseOrder();
        order2.setId(2L);
        List<PurchaseOrder> orders = Arrays.asList(order1, order2);

        // Mock behavior
        when(orderService.findByStatus(status)).thenReturn(orders);

        // Test
        List<PurchaseOrder> result = employeeUseCase.listOrdersByStatus(status);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        // Verify interactions
        verify(orderService, times(1)).findByStatus(status);
        verifyNoMoreInteractions(orderService);
    }

    @org.junit.jupiter.api.Test
    void testListOrdersByCustomer() {
        // Simulate data
        Person customer = new Person();
        customer.setId(1L);
        PurchaseOrder order1 = new PurchaseOrder();
        order1.setId(1L);
        PurchaseOrder order2 = new PurchaseOrder();
        order2.setId(2L);
        List<PurchaseOrder> orders = Arrays.asList(order1, order2);

        // Mock behavior
        when(orderService.findByCustomer(customer)).thenReturn(orders);

        // Test
        List<PurchaseOrder> result = employeeUseCase.listOrdersByCustomer(customer);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        // Verify interactions
        verify(orderService, times(1)).findByCustomer(customer);
        verifyNoMoreInteractions(orderService);
    }

    @org.junit.jupiter.api.Test
    void testDeleteEmployee() {
        // Mock behavior
        doNothing().when(personService).delete(anyLong());

        // Test
        employeeUseCase.deleteEmployee(1L);

        // Verify interactions
        verify(personService, times(1)).delete(1L);
        verifyNoMoreInteractions(personService);
    }

    @org.junit.jupiter.api.Test
    void testGetEmployeeByEmail() {
        // Simulate data
        String email = "test@example.com";
        PersonDTO personDTO = new PersonDTO();
        personDTO.setEmail(email);

        // Mock behavior
        when(personService.findByEmail(email)).thenReturn(personDTO);

        // Test
        PersonDTO result = employeeUseCase.getEmployeeByEmail(email);
        assertEquals(email, result.getEmail());

        // Verify interactions
        verify(personService, times(1)).findByEmail(email);
        verifyNoMoreInteractions(personService);
    }

    @org.junit.jupiter.api.Test
    void testCreateEmployee() {
        // Simulate data
        String email = "test@example.com";
        PersonDTO personDTO = new PersonDTO();
        personDTO.setEmail(email);

        // Mock behavior
        when(personService.save(any(PersonDTO.class))).thenReturn(personDTO);

        // Test
        employeeUseCase.registerEmployee(personDTO);
        PersonDTO result = employeeUseCase.getEmployeeByEmail(email);
        assertEquals(email, result.getEmail());

        // Verify interactions
        verify(personService, times(1)).save(personDTO);
        verifyNoMoreInteractions(personService);
    }
}

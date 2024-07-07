package com.innovix.usecase;

import com.innovix.dto.*;
import com.innovix.entity.*;
import com.innovix.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class EmployeeUseCase {

    private final PersonService personService;
    private final AddressService addressService;
    private final OrderService orderService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final StoreService storeService;
    private final PromotionService promotionService;

    @Autowired
    public EmployeeUseCase(PersonService personService, AddressService addressService, PaymentMethodService paymentMethodService,
                           ShoppingCartService shoppingCartService, OrderService orderService, ProductService productService,
                           CategoryService categoryService, StoreService storeService, PromotionService promotionService) {
        this.personService = personService;
        this.addressService = addressService;
        this.orderService = orderService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.storeService = storeService;
        this.promotionService = promotionService;
    }

    public List<PersonDTO> listAllEmployees() {
        return personService.listAllByType(PersonType.EMPLOYEE);
    }

    public PersonDTO getEmployeeById(Long id) {
        return personService.findById(id);
    }

    public void registerEmployee(PersonDTO personDTO) {
        personService.registerEmployee(personDTO);
    }

    public List<Product> listAllProducts() {
        return productService.listAll();
    }

    public Product createProduct(Product product) {
        return productService.save(product);
    }

    public Product getProductById(Long id) {
        return productService.findById(id);
    }

    public List<Product> listProductsByNameContaining(String name) {
        return productService.findByNameContaining(name);
    }

    public List<Product> listProductsByCategoryId(Long categoryId) {
        return productService.findByCategoryId(categoryId);
    }

    public List<Product> listProductsByPriceBetween(double minPrice, double maxPrice) {
        return productService.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> listProductsBySize(String size) {
        return productService.findBySize(size);
    }

    public List<Product> listProductsByMaterial(String material) {
        return productService.findByMaterial(material);
    }

    public List<Product> listProductsByPromotionId(Long promotionId) {
        return productService.findByPromotionId(promotionId);
    }

    public void deleteProduct(Long id) {
        productService.delete(id);
    }

    public List<Category> listAllCategories() {
        return categoryService.listAll();
    }

    public Category createCategory(Category category) {
        return categoryService.save(category);
    }

    public Category getCategoryById(Long id) {
        return categoryService.findById(id);
    }

    public Category getCategoryByName(String name) {
        return categoryService.findByName(name);
    }

    public void deleteCategory(Long id) {
        categoryService.delete(id);
    }

    public List<Store> listAllStores() {
        return storeService.listAll();
    }

    public Store createStore(Store store) {
        return storeService.save(store);
    }

    public Store getStoreById(Long id) {
        return storeService.findById(id);
    }

    public Store getStoreByName(String name) {
        return storeService.findByName(name);
    }

    public Store getStoreByCnpj(String cnpj) {
        return storeService.findByCnpj(cnpj);
    }

    public List<Store> listStoresByCity(String city) {
        return storeService.findByCity(city);
    }

    public List<Store> listStoresByState(String state) {
        return storeService.findByState(state);
    }

    public List<Store> listStoresByCountry(String country) {
        return storeService.findByCountry(country);
    }

    public List<Store> listStoresByEmployeeId(Long employeeId) {
        return storeService.findByEmployeeId(employeeId);
    }

    public void deleteStore(Long id) {
        storeService.delete(id);
    }

    public List<Promotion> listAllPromotions() {
        return promotionService.listAll();
    }

    public Promotion createPromotion(Promotion promotion) {
        return promotionService.save(promotion);
    }

    public Promotion getPromotionById(Long id) {
        return promotionService.findById(id);
    }

    public List<Promotion> listPromotionsBySeason(String season) {
        return promotionService.findBySeason(season);
    }

    public List<Promotion> listPromotionsByInitLocalDateBefore(LocalDate date) {
        return promotionService.findByInitLocalDateBefore(date);
    }

    public List<Promotion> listPromotionsByEndLocalDateAfter(LocalDate date) {
        return promotionService.findByEndLocalDateAfter(date);
    }

    public List<Promotion> listPromotionsByEmployeeId(Long employeeId) {
        return promotionService.findByEmployeeId(employeeId);
    }

    public void deletePromotion(Long id) {
        promotionService.delete(id);
    }

    public List<PurchaseOrder> listAllOrders() {
        return orderService.listAll();
    }


    public PurchaseOrder getOrderById(Long id) {
        return orderService.findById(id);
    }

    public boolean userExists(String email) {
        return personService.userExists(email);
    }

    public List<PurchaseOrder> listOrdersByStatus(String status) {
        return orderService.findByStatus(status);
    }


    public List<PurchaseOrder> listOrdersByCustomer(Person customer) {
        return orderService.findByCustomer(customer);
    }

    public void deleteEmployee(Long id) {
        personService.delete(id);
    }

    public PersonDTO getEmployeeByEmail(String email) {
        return personService.findByEmail(email);
    }

    public List<PurchaseOrder> listOrdersByLocalDateRange(LocalDate startLocalDate, LocalDate endLocalDate) {
        return orderService.findByLocalDateRange(startLocalDate, endLocalDate);
    }

    public List<PurchaseOrder> listOrdersByProductId(Long productId) {
        return orderService.findByProductId(productId);
    }

    public void deleteOrder(Long id) {
        orderService.delete(id);
    }

    public List<Address> listAllAddresses() {
        return addressService.listAll();
    }

    public Address createAddress(Address address) {
        return addressService.save(address);
    }

    public Address getAddressById(Long id) {
        return addressService.findById(id);
    }

    public List<Address> listAddressesByCity(String city) {
        return addressService.findByCity(city);
    }

    public List<Address> listAddressesByState(String state) {
        return addressService.findByState(state);
    }

    public List<Address> listAddressesByCountry(String country) {
        return addressService.findByCountry(country);
    }

    public List<Address> listAddressesByPersonId(Long personId) {
        return addressService.findByPersonId(personId);
    }

    public void deleteAddress(Long id) {
        addressService.delete(id);
    }
}

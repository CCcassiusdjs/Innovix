package com.innovix.usecase;

import com.innovix.dto.PersonDTO;
import com.innovix.entity.*;
import com.innovix.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * Use case class for managing employee operations.
 * <p>
 * This class provides methods to handle employee-related business logic.
 * </p>
 */
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
    public EmployeeUseCase(PersonService personService, AddressService addressService, OrderService orderService,
                           ProductService productService, CategoryService categoryService, StoreService storeService,
                           PromotionService promotionService) {
        this.personService = personService;
        this.addressService = addressService;
        this.orderService = orderService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.storeService = storeService;
        this.promotionService = promotionService;
    }

    /**
     * Lists all employees.
     *
     * @return A list of employee DTOs.
     */
    public List<PersonDTO> listAllEmployees() {
        return personService.listAllByType(PersonType.EMPLOYEE);
    }

    /**
     * Gets an employee by ID.
     *
     * @param id The ID of the employee.
     * @return The employee DTO.
     */
    public PersonDTO getEmployeeById(Long id) {
        return personService.findById(id);
    }

    /**
     * Registers a new employee.
     *
     * @param personDTO The employee data transfer object.
     */
    public void registerEmployee(PersonDTO personDTO) {
        personService.registerEmployee(personDTO);
    }

    /**
     * Lists all products.
     *
     * @return A list of all products.
     */
    public List<Product> listAllProducts() {
        return productService.listAll();
    }

    /**
     * Creates a new product.
     *
     * @param product The product to create.
     * @return The created product.
     */
    public Product createProduct(Product product) {
        return productService.save(product);
    }

    /**
     * Gets a product by ID.
     *
     * @param id The ID of the product.
     * @return The product.
     */
    public Product getProductById(Long id) {
        return productService.findById(id);
    }

    /**
     * Lists products by name containing a specified string.
     *
     * @param name The name to search for.
     * @return A list of products.
     */
    public List<Product> listProductsByNameContaining(String name) {
        return productService.findByNameContaining(name);
    }

    /**
     * Lists products by category ID.
     *
     * @param categoryId The ID of the category.
     * @return A list of products.
     */
    public List<Product> listProductsByCategoryId(Long categoryId) {
        return productService.findByCategoryId(categoryId);
    }

    /**
     * Lists products by price range.
     *
     * @param minPrice The minimum price.
     * @param maxPrice The maximum price.
     * @return A list of products.
     */
    public List<Product> listProductsByPriceBetween(double minPrice, double maxPrice) {
        return productService.findByPriceBetween(minPrice, maxPrice);
    }

    /**
     * Lists products by size.
     *
     * @param size The size of the product.
     * @return A list of products.
     */
    public List<Product> listProductsBySize(String size) {
        return productService.findBySize(size);
    }

    /**
     * Lists products by material.
     *
     * @param material The material of the product.
     * @return A list of products.
     */
    public List<Product> listProductsByMaterial(String material) {
        return productService.findByMaterial(material);
    }

    /**
     * Lists products by promotion ID.
     *
     * @param promotionId The ID of the promotion.
     * @return A list of products.
     */
    public List<Product> listProductsByPromotionId(Long promotionId) {
        return productService.findByPromotionId(promotionId);
    }

    /**
     * Deletes a product by ID.
     *
     * @param id The ID of the product to delete.
     */
    public void deleteProduct(Long id) {
        productService.delete(id);
    }

    /**
     * Lists all categories.
     *
     * @return A list of all categories.
     */
    public List<Category> listAllCategories() {
        return categoryService.listAll();
    }

    /**
     * Creates a new category.
     *
     * @param category The category to create.
     * @return The created category.
     */
    public Category createCategory(Category category) {
        return categoryService.save(category);
    }

    /**
     * Gets a category by ID.
     *
     * @param id The ID of the category.
     * @return The category.
     */
    public Category getCategoryById(Long id) {
        return categoryService.findById(id);
    }

    /**
     * Gets a category by name.
     *
     * @param name The name of the category.
     * @return The category.
     */
    public Category getCategoryByName(String name) {
        return categoryService.findByName(name);
    }

    /**
     * Deletes a category by ID.
     *
     * @param id The ID of the category to delete.
     */
    public void deleteCategory(Long id) {
        categoryService.delete(id);
    }

    /**
     * Lists all stores.
     *
     * @return A list of all stores.
     */
    public List<Store> listAllStores() {
        return storeService.listAll();
    }

    /**
     * Creates a new store.
     *
     * @param store The store to create.
     * @return The created store.
     */
    public Store createStore(Store store) {
        return storeService.save(store);
    }

    /**
     * Gets a store by ID.
     *
     * @param id The ID of the store.
     * @return The store.
     */
    public Store getStoreById(Long id) {
        return storeService.findById(id);
    }

    /**
     * Gets a store by name.
     *
     * @param name The name of the store.
     * @return The store.
     */
    public Store getStoreByName(String name) {
        return storeService.findByName(name);
    }

    /**
     * Gets a store by CNPJ.
     *
     * @param cnpj The CNPJ of the store.
     * @return The store.
     */
    public Store getStoreByCnpj(String cnpj) {
        return storeService.findByCnpj(cnpj);
    }

    /**
     * Lists stores by city.
     *
     * @param city The city to search for.
     * @return A list of stores.
     */
    public List<Store> listStoresByCity(String city) {
        return storeService.findByCity(city);
    }

    /**
     * Lists stores by state.
     *
     * @param state The state to search for.
     * @return A list of stores.
     */
    public List<Store> listStoresByState(String state) {
        return storeService.findByState(state);
    }

    /**
     * Lists stores by country.
     *
     * @param country The country to search for.
     * @return A list of stores.
     */
    public List<Store> listStoresByCountry(String country) {
        return storeService.findByCountry(country);
    }

    /**
     * Lists stores by employee ID.
     *
     * @param employeeId The ID of the employee.
     * @return A list of stores.
     */
    public List<Store> listStoresByEmployeeId(Long employeeId) {
        return storeService.findByEmployeeId(employeeId);
    }

    /**
     * Deletes a store by ID.
     *
     * @param id The ID of the store to delete.
     */
    public void deleteStore(Long id) {
        storeService.delete(id);
    }

    /**
     * Lists all promotions.
     *
     * @return A list of all promotions.
     */
    public List<Promotion> listAllPromotions() {
        return promotionService.listAll();
    }

    /**
     * Creates a new promotion.
     *
     * @param promotion The promotion to create.
     * @return The created promotion.
     */
    public Promotion createPromotion(Promotion promotion) {
        return promotionService.save(promotion);
    }

    /**
     * Gets a promotion by ID.
     *
     * @param id The ID of the promotion.
     * @return The promotion.
     */
    public Promotion getPromotionById(Long id) {
        return promotionService.findById(id);
    }

    /**
     * Lists promotions by season.
     *
     * @param season The season to search for.
     * @return A list of promotions.
     */
    public List<Promotion> listPromotionsBySeason(String season) {
        return promotionService.findBySeason(season);
    }

    /**
     * Lists promotions with start dates before a specified date.
     *
     * @param date The date to compare against.
     * @return A list of promotions.
     */
    public List<Promotion> listPromotionsByInitLocalDateBefore(LocalDate date) {
        return promotionService.findByInitLocalDateBefore(date);
    }

    /**
     * Lists promotions with end dates after a specified date.
     *
     * @param date The date to compare against.
     * @return A list of promotions.
     */
    public List<Promotion> listPromotionsByEndLocalDateAfter(LocalDate date) {
        return promotionService.findByEndLocalDateAfter(date);
    }

    /**
     * Lists promotions by employee ID.
     *
     * @param employeeId The ID of the employee.
     * @return A list of promotions.
     */
    public List<Promotion> listPromotionsByEmployeeId(Long employeeId) {
        return promotionService.findByEmployeeId(employeeId);
    }

    /**
     * Deletes a promotion by ID.
     *
     * @param id The ID of the promotion to delete.
     */
    public void deletePromotion(Long id) {
        promotionService.delete(id);
    }

    /**
     * Lists all orders.
     *
     * @return A list of all orders.
     */
    public List<PurchaseOrder> listAllOrders() {
        return orderService.listAll();
    }

    /**
     * Gets an order by ID.
     *
     * @param id The ID of the order.
     * @return The order.
     */
    public PurchaseOrder getOrderById(Long id) {
        return orderService.findById(id);
    }

    /**
     * Checks if a user exists by email.
     *
     * @param email The email to check.
     * @return True if the user exists, false otherwise.
     */
    public boolean userExists(String email) {
        return personService.userExists(email);
    }

    /**
     * Lists orders by status.
     *
     * @param status The status of the orders.
     * @return A list of orders.
     */
    public List<PurchaseOrder> listOrdersByStatus(String status) {
        return orderService.findByStatus(status);
    }

    /**
     * Lists orders by customer.
     *
     * @param customer The customer associated with the orders.
     * @return A list of orders.
     */
    public List<PurchaseOrder> listOrdersByCustomer(Person customer) {
        return orderService.findByCustomer(customer);
    }

    /**
     * Deletes an employee by ID.
     *
     * @param id The ID of the employee to delete.
     */
    public void deleteEmployee(Long id) {
        personService.delete(id);
    }

    /**
     * Gets an employee by email.
     *
     * @param email The email of the employee.
     * @return The employee DTO.
     */
    public PersonDTO getEmployeeByEmail(String email) {
        return personService.findByEmail(email);
    }

    /**
     * Lists orders by date range.
     *
     * @param startLocalDate The start date.
     * @param endLocalDate   The end date.
     * @return A list of orders.
     */
    public List<PurchaseOrder> listOrdersByLocalDateRange(LocalDate startLocalDate, LocalDate endLocalDate) {
        return orderService.findByLocalDateRange(startLocalDate, endLocalDate);
    }

    /**
     * Lists orders by product ID.
     *
     * @param productId The ID of the product.
     * @return A list of orders.
     */
    public List<PurchaseOrder> listOrdersByProductId(Long productId) {
        return orderService.findByProductId(productId);
    }

    /**
     * Deletes an order by ID.
     *
     * @param id The ID of the order to delete.
     */
    public void deleteOrder(Long id) {
        orderService.delete(id);
    }

    /**
     * Lists all addresses.
     *
     * @return A list of all addresses.
     */
    public List<Address> listAllAddresses() {
        return addressService.listAll();
    }

    /**
     * Creates a new address.
     *
     * @param address The address to create.
     * @return The created address.
     */
    public Address createAddress(Address address) {
        return addressService.save(address);
    }

    /**
     * Gets an address by ID.
     *
     * @param id The ID of the address.
     * @return The address.
     */
    public Address getAddressById(Long id) {
        return addressService.findById(id);
    }

    /**
     * Lists addresses by city.
     *
     * @param city The city to search for.
     * @return A list of addresses.
     */
    public List<Address> listAddressesByCity(String city) {
        return addressService.findByCity(city);
    }

    /**
     * Lists addresses by state.
     *
     * @param state The state to search for.
     * @return A list of addresses.
     */
    public List<Address> listAddressesByState(String state) {
        return addressService.findByState(state);
    }

    /**
     * Lists addresses by country.
     *
     * @param country The country to search for.
     * @return A list of addresses.
     */
    public List<Address> listAddressesByCountry(String country) {
        return addressService.findByCountry(country);
    }

    /**
     * Lists addresses by person ID.
     *
     * @param personId The ID of the person.
     * @return A list of addresses.
     */
    public List<Address> listAddressesByPersonId(Long personId) {
        return addressService.findByPersonId(personId);
    }

    /**
     * Deletes an address by ID.
     *
     * @param id The ID of the address to delete.
     */
    public void deleteAddress(Long id) {
        addressService.delete(id);
    }
}

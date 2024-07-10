package com.innovix.usecase;

import com.innovix.dto.PersonDTO;
import com.innovix.entity.*;
import com.innovix.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Use case class for managing customer operations.
 * <p>
 * This class provides methods to handle customer-related business logic.
 * </p>
 */
@Component
public class CustomerUseCase {

    private final PersonService personService;
    private final AddressService addressService;
    private final PaymentMethodService paymentMethodService;
    private final ShoppingCartService shoppingCartService;
    private final OrderService orderService;

    @Autowired
    public CustomerUseCase(PersonService personService, AddressService addressService, PaymentMethodService paymentMethodService,
                           ShoppingCartService shoppingCartService, OrderService orderService) {
        this.personService = personService;
        this.addressService = addressService;
        this.paymentMethodService = paymentMethodService;
        this.shoppingCartService = shoppingCartService;
        this.orderService = orderService;
    }

    /**
     * Lists all customers.
     *
     * @return A list of customer DTOs.
     */
    public List<PersonDTO> listAllCustomers() {
        return personService.listAllByType(PersonType.CUSTOMER);
    }

    /**
     * Deletes a customer by ID.
     *
     * @param id The ID of the customer to delete.
     */
    public void deleteCustomer(Long id) {
        personService.delete(id);
    }

    /**
     * Registers a new customer.
     *
     * @param personDTO The customer data transfer object.
     */
    public void registerCustomer(PersonDTO personDTO) {
        personService.registerCustomer(personDTO);
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
     * Gets a customer by email.
     *
     * @param email The email of the customer.
     * @return The customer DTO.
     */
    public PersonDTO getCustomerByEmail(String email) {
        return personService.findByEmail(email);
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
     * Lists all payment methods.
     *
     * @return A list of all payment methods.
     */
    public List<PaymentMethod> listAllPaymentMethods() {
        return paymentMethodService.listAll();
    }

    /**
     * Creates a new payment method.
     *
     * @param paymentMethod The payment method to create.
     * @return The created payment method.
     */
    public PaymentMethod createPaymentMethod(PaymentMethod paymentMethod) {
        return paymentMethodService.save(paymentMethod);
    }

    /**
     * Gets a payment method by ID.
     *
     * @param id The ID of the payment method.
     * @return The payment method.
     */
    public PaymentMethod getPaymentMethodById(Long id) {
        return paymentMethodService.findById(id);
    }

    /**
     * Lists payment methods by person ID.
     *
     * @param personId The ID of the person.
     * @return A list of payment methods.
     */
    public List<PaymentMethod> listPaymentMethodsByPersonId(Long personId) {
        return paymentMethodService.findByPersonId(personId);
    }

    /**
     * Lists payment methods by payment type.
     *
     * @param paymentType The type of payment.
     * @return A list of payment methods.
     */
    public List<PaymentMethod> listPaymentMethodsByPaymentType(String paymentType) {
        return paymentMethodService.findByPaymentType(paymentType);
    }

    /**
     * Gets a payment method by card number.
     *
     * @param cardNumber The card number.
     * @return The payment method.
     */
    public PaymentMethod getPaymentMethodByCardNumber(String cardNumber) {
        return paymentMethodService.findByCardNumber(cardNumber);
    }

    /**
     * Deletes a payment method by ID.
     *
     * @param id The ID of the payment method to delete.
     */
    public void deletePaymentMethod(Long id) {
        paymentMethodService.delete(id);
    }

    /**
     * Lists all shopping carts.
     *
     * @return A list of all shopping carts.
     */
    public List<ShoppingCart> listAllShoppingCarts() {
        return shoppingCartService.listAll();
    }

    /**
     * Creates a new shopping cart.
     *
     * @param shoppingCart The shopping cart to create.
     * @return The created shopping cart.
     */
    public ShoppingCart createShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartService.save(shoppingCart);
    }

    /**
     * Gets a shopping cart by ID.
     *
     * @param id The ID of the shopping cart.
     * @return The shopping cart.
     */
    public ShoppingCart getShoppingCartById(Long id) {
        return shoppingCartService.findById(id);
    }

    /**
     * Lists shopping carts by customer.
     *
     * @param customer The customer associated with the shopping carts.
     * @return A list of shopping carts.
     */
    public List<ShoppingCart> listShoppingCartsByCustomer(Person customer) {
        return shoppingCartService.findByCustomer(customer);
    }

    /**
     * Deletes a shopping cart by ID.
     *
     * @param id The ID of the shopping cart to delete.
     */
    public void deleteShoppingCart(Long id) {
        shoppingCartService.delete(id);
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
     * Lists all orders.
     *
     * @return A list of all orders.
     */
    public List<PurchaseOrder> listAllOrders() {
        return orderService.listAll();
    }

    /**
     * Creates a new order.
     *
     * @param purchaseOrder The order to create.
     * @return The created order.
     */
    public PurchaseOrder createOrder(PurchaseOrder purchaseOrder) {
        return orderService.save(purchaseOrder);
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
}

package com.innovix.usecase;

import com.innovix.dto.*;
import com.innovix.entity.*;
import com.innovix.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

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

    public List<PersonDTO> listAllCustomers() {
        return personService.listAllByType(PersonType.CUSTOMER);
    }

    public void deleteCustomer(Long id) {
        personService.delete(id);
    }

    public void registerCustomer(PersonDTO personDTO) {
        personService.registerCustomer(personDTO);
    }

    public List<Address> listAllAddresses() {
        return addressService.listAll();
    }

    public PersonDTO getCustomerByEmail(String email) {
        return personService.findByEmail(email);
    }

    public Address createAddress(Address address) {
        return addressService.save(address);
    }

    public List<PaymentMethod> listAllPaymentMethods() {
        return paymentMethodService.listAll();
    }

    public PaymentMethod createPaymentMethod(PaymentMethod paymentMethod) {
        return paymentMethodService.save(paymentMethod);
    }

    public PaymentMethod getPaymentMethodById(Long id) {
        return paymentMethodService.findById(id);
    }

    public List<PaymentMethod> listPaymentMethodsByPersonId(Long personId) {
        return paymentMethodService.findByPersonId(personId);
    }

    public List<PaymentMethod> listPaymentMethodsByPaymentType(String paymentType) {
        return paymentMethodService.findByPaymentType(paymentType);
    }

    public PaymentMethod getPaymentMethodByCardNumber(String cardNumber) {
        return paymentMethodService.findByCardNumber(cardNumber);
    }

    public void deletePaymentMethod(Long id) {
        paymentMethodService.delete(id);
    }

    public List<ShoppingCart> listAllShoppingCarts() {
        return shoppingCartService.listAll();
    }

    public ShoppingCart createShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartService.save(shoppingCart);
    }

    public ShoppingCart getShoppingCartById(Long id) {
        return shoppingCartService.findById(id);
    }

    public List<ShoppingCart> listShoppingCartsByCustomer(Person customer) {
        return shoppingCartService.findByCustomer(customer);
    }

    public void deleteShoppingCart(Long id) {
        shoppingCartService.delete(id);
    }

    public boolean userExists(String email) {
        return personService.userExists(email);
    }


    public List<PurchaseOrder> listAllOrders() {
        return orderService.listAll();
    }

    public PurchaseOrder createOrder(PurchaseOrder purchaseOrder) {
        return orderService.save(purchaseOrder);
    }


    public List<PurchaseOrder> listOrdersByCustomer(Person customer) {
        return orderService.findByCustomer(customer);
    }


}

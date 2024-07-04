package com.innovix.usecase;

import com.innovix.entity.PersonType;
import com.innovix.dto.PersonDTO;
import com.innovix.entity.*;
import com.innovix.service.AddressService;
import com.innovix.service.OrderService;
import com.innovix.service.PaymentMethodService;
import com.innovix.service.PersonService;
import com.innovix.service.ShoppingCartService;
import com.innovix.usecase.CustomerUseCase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerUseCaseTest {

    @Mock
    private PersonService personService;

    @Mock
    private AddressService addressService;

    @Mock
    private PaymentMethodService paymentMethodService;

    @Mock
    private ShoppingCartService shoppingCartService;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private CustomerUseCase customerUseCase;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @org.junit.jupiter.api.Test
    void testListAllCustomers() {
        // Simulate data
        PersonDTO customer1 = new PersonDTO();
        customer1.setId(1L);
        PersonDTO customer2 = new PersonDTO();
        customer2.setId(2L);
        List<PersonDTO> customers = Arrays.asList(customer1, customer2);

        // Mock behavior
        when(personService.listAllByType(PersonType.CUSTOMER)).thenReturn(customers);

        // Test
        List<PersonDTO> result = customerUseCase.listAllCustomers();
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        // Verify interactions
        verify(personService, times(1)).listAllByType(PersonType.CUSTOMER);
        verifyNoMoreInteractions(personService);
    }

    @org.junit.jupiter.api.Test
    void testDeleteCustomer() {
        // Mock behavior
        doNothing().when(personService).delete(anyLong());

        // Test
        customerUseCase.deleteCustomer(1L);

        // Verify interactions
        verify(personService, times(1)).delete(1L);
        verifyNoMoreInteractions(personService);
    }

    @org.junit.jupiter.api.Test
    void testRegisterCustomer() {
        // Simulate data
        PersonDTO personDTO = new PersonDTO();
        personDTO.setEmail("test@example.com");

        // Test
        customerUseCase.registerCustomer(personDTO);

        // Verify interactions
        verify(personService, times(1)).registerCustomer(personDTO);
        verifyNoMoreInteractions(personService);
    }

    @org.junit.jupiter.api.Test
    void testListAllAddresses() {
        // Simulate data
        Address address1 = new Address();
        address1.setId(1L);
        Address address2 = new Address();
        address2.setId(2L);
        List<Address> addresses = Arrays.asList(address1, address2);

        // Mock behavior
        when(addressService.listAll()).thenReturn(addresses);

        // Test
        List<Address> result = customerUseCase.listAllAddresses();
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        // Verify interactions
        verify(addressService, times(1)).listAll();
        verifyNoMoreInteractions(addressService);
    }

    @org.junit.jupiter.api.Test
    void testGetCustomerByEmail() {
        // Simulate data
        String email = "test@example.com";
        PersonDTO personDTO = new PersonDTO();
        personDTO.setEmail(email);

        // Mock behavior
        when(personService.findByEmail(email)).thenReturn(personDTO);

        // Test
        PersonDTO result = customerUseCase.getCustomerByEmail(email);
        assertEquals(email, result.getEmail());

        // Verify interactions
        verify(personService, times(1)).findByEmail(email);
        verifyNoMoreInteractions(personService);
    }

    @org.junit.jupiter.api.Test
    void testCreateAddress() {
        // Simulate data
        Address address = new Address();
        address.setStreetName("123 Main St");

        // Mock behavior
        when(addressService.save(address)).thenReturn(address);

        // Test
        Address result = customerUseCase.createAddress(address);
        assertEquals("123 Main St", result.getStreetName());

        // Verify interactions
        verify(addressService, times(1)).save(address);
        verifyNoMoreInteractions(addressService);
    }

    @org.junit.jupiter.api.Test
    void testListAllPaymentMethods() {
        // Simulate data
        PaymentMethod paymentMethod1 = new PaymentMethod();
        paymentMethod1.setId(1L);
        PaymentMethod paymentMethod2 = new PaymentMethod();
        paymentMethod2.setId(2L);
        List<PaymentMethod> paymentMethods = Arrays.asList(paymentMethod1, paymentMethod2);

        // Mock behavior
        when(paymentMethodService.listAll()).thenReturn(paymentMethods);

        // Test
        List<PaymentMethod> result = customerUseCase.listAllPaymentMethods();
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        // Verify interactions
        verify(paymentMethodService, times(1)).listAll();
        verifyNoMoreInteractions(paymentMethodService);
    }

    @org.junit.jupiter.api.Test
    void testCreatePaymentMethod() {
        // Simulate data
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setCardNumber("1234567890123456");

        // Mock behavior
        when(paymentMethodService.save(paymentMethod)).thenReturn(paymentMethod);

        // Test
        PaymentMethod result = customerUseCase.createPaymentMethod(paymentMethod);
        assertEquals("1234567890123456", result.getCardNumber());

        // Verify interactions
        verify(paymentMethodService, times(1)).save(paymentMethod);
        verifyNoMoreInteractions(paymentMethodService);
    }

    @org.junit.jupiter.api.Test
    void testGetPaymentMethodById() {
        // Simulate data
        Long id = 1L;
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setId(id);

        // Mock behavior
        when(paymentMethodService.findById(id)).thenReturn(paymentMethod);

        // Test
        PaymentMethod result = customerUseCase.getPaymentMethodById(id);
        assertEquals(id, result.getId());

        // Verify interactions
        verify(paymentMethodService, times(1)).findById(id);
        verifyNoMoreInteractions(paymentMethodService);
    }

    @org.junit.jupiter.api.Test
    void testListPaymentMethodsByPersonId() {
        // Simulate data
        Long personId = 1L;
        PaymentMethod paymentMethod1 = new PaymentMethod();
        paymentMethod1.setId(1L);
        PaymentMethod paymentMethod2 = new PaymentMethod();
        paymentMethod2.setId(2L);
        List<PaymentMethod> paymentMethods = Arrays.asList(paymentMethod1, paymentMethod2);

        // Mock behavior
        when(paymentMethodService.findByPersonId(personId)).thenReturn(paymentMethods);

        // Test
        List<PaymentMethod> result = customerUseCase.listPaymentMethodsByPersonId(personId);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        // Verify interactions
        verify(paymentMethodService, times(1)).findByPersonId(personId);
        verifyNoMoreInteractions(paymentMethodService);
    }

    @org.junit.jupiter.api.Test
    void testListPaymentMethodsByPaymentType() {
        // Simulate data
        String paymentType = "Credit Card";
        PaymentMethod paymentMethod1 = new PaymentMethod();
        paymentMethod1.setId(1L);
        PaymentMethod paymentMethod2 = new PaymentMethod();
        paymentMethod2.setId(2L);
        List<PaymentMethod> paymentMethods = Arrays.asList(paymentMethod1, paymentMethod2);

        // Mock behavior
        when(paymentMethodService.findByPaymentType(paymentType)).thenReturn(paymentMethods);

        // Test
        List<PaymentMethod> result = customerUseCase.listPaymentMethodsByPaymentType(paymentType);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        // Verify interactions
        verify(paymentMethodService, times(1)).findByPaymentType(paymentType);
        verifyNoMoreInteractions(paymentMethodService);
    }

    @org.junit.jupiter.api.Test
    void testGetPaymentMethodByCardNumber() {
        // Simulate data
        String cardNumber = "1234567890123456";
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setCardNumber(cardNumber);

        // Mock behavior
        when(paymentMethodService.findByCardNumber(cardNumber)).thenReturn(paymentMethod);

        // Test
        PaymentMethod result = customerUseCase.getPaymentMethodByCardNumber(cardNumber);
        assertEquals(cardNumber, result.getCardNumber());

        // Verify interactions
        verify(paymentMethodService, times(1)).findByCardNumber(cardNumber);
        verifyNoMoreInteractions(paymentMethodService);
    }

    @org.junit.jupiter.api.Test
    void testDeletePaymentMethod() {
        // Mock behavior
        doNothing().when(paymentMethodService).delete(anyLong());

        // Test
        customerUseCase.deletePaymentMethod(1L);

        // Verify interactions
        verify(paymentMethodService, times(1)).delete(1L);
        verifyNoMoreInteractions(paymentMethodService);
    }

    @org.junit.jupiter.api.Test
    void testListAllShoppingCarts() {
        // Simulate data
        ShoppingCart cart1 = new ShoppingCart();
        cart1.setId(1L);
        ShoppingCart cart2 = new ShoppingCart();
        cart2.setId(2L);
        List<ShoppingCart> shoppingCarts = Arrays.asList(cart1, cart2);

        // Mock behavior
        when(shoppingCartService.listAll()).thenReturn(shoppingCarts);

        // Test
        List<ShoppingCart> result = customerUseCase.listAllShoppingCarts();
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        // Verify interactions
        verify(shoppingCartService, times(1)).listAll();
        verifyNoMoreInteractions(shoppingCartService);
    }

    @org.junit.jupiter.api.Test
    void testCreateShoppingCart() {
        // Simulate data
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCustomer(new Person());

        // Mock behavior
        when(shoppingCartService.save(shoppingCart)).thenReturn(shoppingCart);

        // Test
        ShoppingCart result = customerUseCase.createShoppingCart(shoppingCart);
        assertEquals(shoppingCart.getCustomer(), result.getCustomer());

        // Verify interactions
        verify(shoppingCartService, times(1)).save(shoppingCart);
        verifyNoMoreInteractions(shoppingCartService);
    }

    @org.junit.jupiter.api.Test
    void testGetShoppingCartById() {
        // Simulate data
        Long id = 1L;
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(id);

        // Mock behavior
        when(shoppingCartService.findById(id)).thenReturn(shoppingCart);

        // Test
        ShoppingCart result = customerUseCase.getShoppingCartById(id);
        assertEquals(id, result.getId());

        // Verify interactions
        verify(shoppingCartService, times(1)).findById(id);
        verifyNoMoreInteractions(shoppingCartService);
    }

    @org.junit.jupiter.api.Test
    void testListShoppingCartsByCustomer() {
        // Simulate data
        Person customer = new Person();
        customer.setId(1L);
        ShoppingCart cart1 = new ShoppingCart();
        cart1.setId(1L);
        ShoppingCart cart2 = new ShoppingCart();
        cart2.setId(2L);
        List<ShoppingCart> shoppingCarts = Arrays.asList(cart1, cart2);

        // Mock behavior
        when(shoppingCartService.findByCustomer(customer)).thenReturn(shoppingCarts);

        // Test
        List<ShoppingCart> result = customerUseCase.listShoppingCartsByCustomer(customer);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        // Verify interactions
        verify(shoppingCartService, times(1)).findByCustomer(customer);
        verifyNoMoreInteractions(shoppingCartService);
    }

    @org.junit.jupiter.api.Test
    void testDeleteShoppingCart() {
        // Mock behavior
        doNothing().when(shoppingCartService).delete(anyLong());

        // Test
        customerUseCase.deleteShoppingCart(1L);

        // Verify interactions
        verify(shoppingCartService, times(1)).delete(1L);
        verifyNoMoreInteractions(shoppingCartService);
    }

    @org.junit.jupiter.api.Test
    void testUserExists() {
        // Simulate data
        String email = "test@example.com";

        // Mock behavior
        when(personService.userExists(email)).thenReturn(true);

        // Test
        boolean result = customerUseCase.userExists(email);
        assertEquals(true, result);

        // Verify interactions
        verify(personService, times(1)).userExists(email);
        verifyNoMoreInteractions(personService);
    }

    @org.junit.jupiter.api.Test
    void testListAllOrders() {
        // Simulate data
        Person customer = new Person();
        customer.setId(1L);
        List<PurchaseOrder> orders = Arrays.asList(new PurchaseOrder(), new PurchaseOrder());

        // Mock behavior
        when(orderService.listAll()).thenReturn(orders);

        // Test
        List<PurchaseOrder> result = customerUseCase.listAllOrders();
        assertEquals(2, result.size());

        // Verify interactions
        verify(orderService, times(1)).listAll();
        verifyNoMoreInteractions(orderService);
    }

    @org.junit.jupiter.api.Test
    void testCreateOrder() {
        // Simulate data
        PurchaseOrder order = new PurchaseOrder();

        // Mock behavior
        when(orderService.save(order)).thenReturn(order);

        // Test
        PurchaseOrder result = customerUseCase.createOrder(order);
        assertEquals(order, result);

        // Verify interactions
        verify(orderService, times(1)).save(order);
        verifyNoMoreInteractions(orderService);
    }

    @org.junit.jupiter.api.Test
    void testListOrdersByCustomer() {
        // Simulate data
        Person customer = new Person();
        customer.setId(1L);
        List<PurchaseOrder> orders = Arrays.asList(new PurchaseOrder(), new PurchaseOrder());

        // Mock behavior
        when(orderService.findByCustomer(customer)).thenReturn(orders);

        // Test
        List<PurchaseOrder> result = customerUseCase.listOrdersByCustomer(customer);
        assertEquals(2, result.size());

        // Verify interactions
        verify(orderService, times(1)).findByCustomer(customer);
        verifyNoMoreInteractions(orderService);
    }
}

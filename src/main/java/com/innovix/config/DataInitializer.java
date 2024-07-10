package com.innovix.config;

import com.github.javafaker.Faker;
import com.innovix.entity.*;
import com.innovix.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;

/**
 * Data initializer class to populate the database with initial data.
 */
@Configuration
public class DataInitializer {

    private final AddressRepository addressRepository;
    private final CategoryRepository categoryRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final PersonRepository personRepository;
    private final ProductRepository productRepository;
    private final PromotionRepository promotionRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final StoreRepository storeRepository;
    private final Faker faker;
    private final Random random;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(AddressRepository addressRepository, CategoryRepository categoryRepository,
                           PaymentMethodRepository paymentMethodRepository, PersonRepository personRepository,
                           ProductRepository productRepository, PromotionRepository promotionRepository,
                           PurchaseOrderRepository purchaseOrderRepository, ShoppingCartRepository shoppingCartRepository,
                           StoreRepository storeRepository, PasswordEncoder passwordEncoder) {
        this.addressRepository = addressRepository;
        this.categoryRepository = categoryRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.personRepository = personRepository;
        this.productRepository = productRepository;
        this.promotionRepository = promotionRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.storeRepository = storeRepository;
        this.faker = new Faker(new Locale("pt-BR")); // Gera dados em português
        this.random = new Random();
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initData() {
        createCategories();
        createPersons();
        createAddresses();
        createPaymentMethods();
        createPromotions();
        createProducts();
        createStores();
        createShoppingCarts();
        createPurchaseOrders();
    }

    void createCategories() {
        List<Category> categories = new ArrayList<>();
        String[] categoryNames = {"Eletrônicos", "Livros", "Roupas", "Alimentos", "Móveis", "Brinquedos", "Esportes", "Saúde", "Beleza", "Automotivo"};
        for (String categoryName : categoryNames) {
            Optional<Category> existingCategory = Optional.ofNullable(categoryRepository.findByName(categoryName));
            if (existingCategory.isEmpty()) {
                Category category = new Category();
                category.setName(categoryName);
                categories.add(category);
            }
        }
        categoryRepository.saveAll(categories);
    }

    void createPersons() {
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 100; i++) { // Creating 100 persons
            Person person = new Person();
            person.setEmail(faker.internet().emailAddress());
            person.setFullName(faker.name().fullName());
            person.setCpf(faker.regexify("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}"));
            person.setPassword(passwordEncoder.encode(faker.internet().password())); // Encrypting password
            person.setPhone(faker.phoneNumber().cellPhone());
            person.setBirthday(faker.date().birthday().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
            person.setType(PersonType.values()[random.nextInt(PersonType.values().length)]);
            persons.add(person);
        }
        personRepository.saveAll(persons);
    }

    void createAddresses() {
        List<Address> addresses = new ArrayList<>();
        List<Person> persons = personRepository.findAll();
        for (Person person : persons) {
            Address address = new Address();
            address.setStreetName(faker.address().streetName());
            address.setNumber(faker.number().numberBetween(1, 1000));
            address.setUnit(faker.address().secondaryAddress());
            address.setZipCode(faker.regexify("\\d{5}-\\d{3}"));
            address.setCity(faker.address().city());
            address.setState(faker.address().state());
            address.setCountry(faker.address().country());
            address.setPersonId(person.getId());
            addresses.add(address);
        }
        addressRepository.saveAll(addresses);
    }

    void createPaymentMethods() {
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        List<Person> persons = personRepository.findAll();
        String[] paymentTypes = {"DEBITO", "CREDITO"};

        for (Person person : persons) {
            PaymentMethod paymentMethod = new PaymentMethod();
            String paymentType = paymentTypes[random.nextInt(paymentTypes.length)];

            paymentMethod.setPaymentType(paymentType);
            paymentMethod.setCardHolder(person.getFullName());
            // Gera um número de cartão com exatamente 16 dígitos
            String cardNumber = faker.number().digits(16);
            paymentMethod.setCardNumber(cardNumber);
            paymentMethod.setCardExpirationLocalDate(LocalDate.now().plusYears(3));
            paymentMethod.setCardCvv(faker.number().digits(3));
            paymentMethod.setPerson(person);
            paymentMethods.add(paymentMethod);
        }
        paymentMethodRepository.saveAll(paymentMethods);
    }

    void createPromotions() {
        List<Promotion> promotions = new ArrayList<>();
        List<Person> employees = personRepository.findByType(PersonType.EMPLOYEE);
        String[] seasons = {"Inverno", "Verão", "Primavera", "Outono"};
        String[] descriptions = {
                "Black Friday", "Cyber Monday", "Natal", "Ano Novo", "Carnaval", "Páscoa",
                "Dia das Mães", "Dia dos Pais", "Dia das Crianças", "Dia dos Namorados",
                "Férias de Verão", "Férias de Inverno", "Aniversário do Cliente"
        };

        for (Person employee : employees) {
            for (String description : descriptions) {
                for (String season : seasons) {
                    Promotion promotion = new Promotion();
                    promotion.setDescription(description);
                    promotion.setSeason(season);
                    promotion.setDuration(30);
                    promotion.setEmployee(employee);
                    promotion.setEndLocalDate(LocalDate.now().plusDays(30));
                    promotion.setFreeQuantity(5);
                    promotion.setInitLocalDate(LocalDate.now());
                    promotion.setPercentage(10.0);
                    promotion.setRequiredQuantity(2);
                    promotions.add(promotion);
                }
            }
        }
        promotionRepository.saveAll(promotions);
    }

    void createStores() {
        List<Store> stores = new ArrayList<>();
        List<Address> addresses = addressRepository.findAll();
        List<Person> employees = personRepository.findByType(PersonType.EMPLOYEE);

        if (addresses.isEmpty() || employees.isEmpty()) {
            return;
        }

        for (int i = 0; i < 20; i++) { // Creating 20 stores
            Store store = new Store();
            store.setStoreName(faker.company().name());
            store.setStoreCnpj(faker.regexify("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}"));
            store.setAddress(addresses.get(random.nextInt(addresses.size())));
            store.setEmployee(employees.get(random.nextInt(employees.size())));
            stores.add(store);
        }
        storeRepository.saveAll(stores);
    }

    void createProducts() {
        List<Product> products = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();
        List<Promotion> promotions = promotionRepository.findAll();

        if (categories.isEmpty()) {
            return;
        }

        for (int i = 0; i < 200; i++) { // Creating 200 products
            Product product = new Product();
            product.setName(faker.commerce().productName());
            product.setDescription(faker.lorem().sentence());
            product.setSize(faker.options().option("P", "M", "G", "GG")); // Tamanhos em português
            product.setMaterial(faker.commerce().material());
            Product.Dimensions dimensions = new Product.Dimensions();
            dimensions.setLength(faker.number().randomDouble(2, 10, 100));
            dimensions.setWidth(faker.number().randomDouble(2, 10, 100));
            dimensions.setHeight(faker.number().randomDouble(2, 10, 100));
            product.setDimensions(dimensions);
            product.setPrice(faker.number().randomDouble(2, 10, 1000));

            Category category = categories.get(random.nextInt(categories.size()));
            product.setCategory(category);

            if (category != null && category.getName() != null) {
                product.setImages(getImageUrlByCategory(category.getName())); // Using image URLs
            } else {
                product.setImages("https://example.com/images/default.jpg");
            }

            product.setPromotion(promotions.isEmpty() ? null : promotions.get(random.nextInt(promotions.size())));
            products.add(product);
        }
        productRepository.saveAll(products);
    }

    private String getImageUrlByCategory(String category) {
        return switch (category) {
            case "Eletrônicos" -> "https://example.com/images/electronics.jpg";
            case "Livros" -> "https://example.com/images/books.jpg";
            case "Roupas" -> "https://example.com/images/clothes.jpg";
            case "Alimentos" -> "https://example.com/images/food.jpg";
            case "Móveis" -> "https://example.com/images/furniture.jpg";
            case "Brinquedos" -> "https://example.com/images/toys.jpg";
            case "Esportes" -> "https://example.com/images/sports.jpg";
            case "Saúde" -> "https://example.com/images/health.jpg";
            case "Beleza" -> "https://example.com/images/beauty.jpg";
            case "Automotivo" -> "https://example.com/images/automotive.jpg";
            default -> "https://example.com/images/default.jpg";
        };
    }


    void createShoppingCarts() {
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        List<Person> customers = personRepository.findByType(PersonType.CUSTOMER);
        List<Product> products = productRepository.findAll();
        for (Person customer : customers) {
            for (int i = 0; i < 5; i++) { // Creating 5 shopping carts per customer
                Product product = products.get(random.nextInt(products.size()));
                ShoppingCart cart = new ShoppingCart();
                cart.setCustomer(customer);
                cart.setProductImage(product.getImages()); // Using the product image URL
                cart.setProductName(product.getName());
                cart.setProductDescription(product.getDescription());
                cart.setProductPrice(product.getPrice());
                cart.setProductQuantity(faker.number().numberBetween(1, 5));
                cart.setProductSubtotal(cart.getProductPrice() * cart.getProductQuantity());
                cart.setSubtotal(cart.getProductSubtotal());
                cart.setProduct(product);
                shoppingCarts.add(cart);
            }
        }
        shoppingCartRepository.saveAll(shoppingCarts);
    }

    void createPurchaseOrders() {
        List<PurchaseOrder> orders = new ArrayList<>();
        List<Person> customers = personRepository.findByType(PersonType.CUSTOMER);
        List<Address> addresses = addressRepository.findAll();
        List<Product> products = productRepository.findAll();
        for (Person customer : customers) {
            for (int i = 0; i < 3; i++) { // Creating 3 orders per customer
                Product product = products.get(random.nextInt(products.size()));
                PurchaseOrder order = new PurchaseOrder();
                order.setOrderLocalDate(LocalDate.now());
                order.setOrderStatus(faker.options().option("PENDENTE", "ENVIADO", "ENTREGUE"));
                order.setCustomer(customer);
                order.setAddressOrigin(addresses.get(random.nextInt(addresses.size())));
                order.setAddressDestination(addresses.get(random.nextInt(addresses.size())));
                order.setProductImage(product.getImages()); // Using the product image URL
                order.setProductName(product.getName());
                order.setProductDescription(product.getDescription());
                order.setProductPrice(BigDecimal.valueOf(product.getPrice()));
                order.setProductQuantity(faker.number().numberBetween(1, 5));
                order.setProductSubtotal(order.getProductPrice().multiply(BigDecimal.valueOf(order.getProductQuantity())));
                order.setFreeQuantity(faker.number().numberBetween(0, 2));
                order.setProduct(product);
                orders.add(order);
            }
        }
        purchaseOrderRepository.saveAll(orders);
    }
}

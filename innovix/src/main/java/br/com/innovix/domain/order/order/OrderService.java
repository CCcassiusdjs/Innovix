    package br.com.innovix.domain.order.order;

    import br.com.innovix.exception.Exceptions;
    import br.com.innovix.domain.person.PersonRepository;
    import br.com.innovix.exception.OrderNotFoundException;
    import org.springframework.beans.factory.annotation.*;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.stream.Collectors;

    @Service
    public class OrderService {

        private final OrderRepository orderRepository;
        private final PersonRepository personRepository;

        @Autowired
        public OrderService(OrderRepository orderRepository, PersonRepository personRepository) {
            this.orderRepository = orderRepository;
            this.personRepository = personRepository;
        }

        public List<OrderDTO> findAllOrders() {
            return orderRepository.findAll().stream()
                    .map(OrderDTO::fromEntity)
                    .collect(Collectors.toList());
        }

        public OrderDTO findOrderById(long id) {
            OrderEntity order = orderRepository.findById(id)
                    .orElseThrow(OrderNotFoundException::new);
            return OrderDTO.fromEntity(order);
        }

        public OrderDTO addOrder(OrderDTO orderDTO) {
            OrderEntity orderEntity = orderDTO.toEntity();
            return OrderDTO.fromEntity(orderRepository.save(orderEntity));
        }

        public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
            OrderEntity existingOrder = orderRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.OrderNotFoundException("Order not found with ID: " + id));

            existingOrder.setCompanyName(orderDTO.companyName());
            existingOrder.setSender(orderDTO.sender());
            existingOrder.setRecipient(orderDTO.recipient());
            existingOrder.setShipCost(orderDTO.shipCost());
            existingOrder.setState(orderDTO.state());
            // Assumindo que customer já existe, precisa ser recuperado antes de atualizar
            existingOrder.setCustomer(personRepository.findById(orderDTO.codCustomer()).orElseThrow());

            return OrderDTO.fromEntity(orderRepository.save(existingOrder));
        }

        public void deleteOrder(Long id) {
            OrderEntity order = orderRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.OrderNotFoundException("Order not found with ID: " + id));
            orderRepository.delete(order);
        }
    }
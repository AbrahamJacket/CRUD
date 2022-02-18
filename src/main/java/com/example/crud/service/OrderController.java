package com.example.crud.service;

import com.example.crud.dto.OrderDto;
import com.example.crud.entity.Order;
import com.example.crud.repo.OrderRepo;
import com.example.crud.repo.PersonRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
    private final OrderRepo orderRepo;

    public OrderController(OrderRepo orderRepo, PersonRepo personRepo) {
        this.orderRepo = orderRepo;
    }

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public Order saveOrder(@RequestBody Order order){
        return orderRepo.save(order);
    }

    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAllOrder(){
        return orderRepo.findAll();
    }

    @GetMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order getOrderByID(@PathVariable Long id){
        return orderRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id = " + id));
    }

    @PutMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order refreshOrder(@PathVariable("id") Long id, @RequestBody Order order){
        return orderRepo.findById(id).
                map(entity -> {
                    entity.setAddress(order.getAddress());
                    entity.setItem(order.getItem());
                    entity.setPrice(order.getPrice());
                    entity.setPerson(order.getPerson());
                    return orderRepo.save(entity);
                }).orElseThrow(() -> new EntityNotFoundException("Order not found with id = " + id));
    }
    @DeleteMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeOrderById(@PathVariable Long id){
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id = " + id));
        orderRepo.delete(order);
    }

    @DeleteMapping("/orders")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllOrder(){
        orderRepo.deleteAll();
    }

    @GetMapping("/orderdto/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto orderDto(@PathVariable Long id){
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id = " + id));
        return OrderDto.adaptToOrderDto(order);
    }
}

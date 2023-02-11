//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.driver;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public OrderService() {
    }

    public void addOrder(Order order) {
        this.orderRepository.addOrder(order);
    }

    public void addPartner(String partnerId) {
        this.orderRepository.addPartner(partnerId);
    }

    public void addOrderPartnerPair(String orderId, String partnerId) {
        this.orderRepository.addOrderPartnerPair(orderId, partnerId);
    }

    public Order getOrderById(String orderId) {
        return this.orderRepository.getOrderById(orderId);
    }

    public DeliveryPartner getPartnerById(String partnerId) {
        return this.orderRepository.getPartnerById(partnerId);
    }

    public int getOrderCountByPartnerId(String partnerId) {
        return this.orderRepository.getOrderCountByPartnerId(partnerId);
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        return this.orderRepository.getOrdersByPartnerId(partnerId);
    }

    public List<String> getAllOrders() {
        return this.orderRepository.getAllOrders();
    }

    public int getCountOfUnassignedOrders() {
        return this.orderRepository.getCountOfUnassignedOrders();
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        return this.orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(time, partnerId);
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        return this.orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
    }

    public void deletePartnerById(String partnerId) {
        this.orderRepository.deletePartnerById(partnerId);
    }

    public void deleteOrderById(String orderId) {
        this.orderRepository.deleteOrderById(orderId);
    }
}

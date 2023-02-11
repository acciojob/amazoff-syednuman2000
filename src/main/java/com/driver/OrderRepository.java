//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {
    HashMap<String, Order> orderMap = new HashMap();
    HashMap<String, DeliveryPartner> partnerMap = new HashMap();
    HashMap<String, List<Order>> partnerOrderMap = new HashMap();
    HashMap<String, DeliveryPartner> orderPartnerMap = new HashMap();

    public OrderRepository() {
    }

    public void addOrder(Order order) {
        this.orderMap.put(order.getId(), order);
    }

    public void addPartner(String partnerId) {
        DeliveryPartner deliveryPartner = new DeliveryPartner(partnerId);
        this.partnerMap.put(partnerId, deliveryPartner);
        this.partnerOrderMap.put(partnerId, new ArrayList());
    }

    public void addOrderPartnerPair(String orderId, String partnerId) {
        Order order = (Order)this.orderMap.get(orderId);
        ((List)this.partnerOrderMap.get(partnerId)).add(order);
        DeliveryPartner deliveryPartner = (DeliveryPartner)this.partnerMap.get(partnerId);
        this.orderPartnerMap.put(orderId, deliveryPartner);
    }

    public Order getOrderById(String orderId) {
        return (Order)this.orderMap.get(orderId);
    }

    public DeliveryPartner getPartnerById(String partnerId) {
        return (DeliveryPartner)this.partnerMap.get(partnerId);
    }

    public int getOrderCountByPartnerId(String partnerId) {
        return ((List)this.partnerOrderMap.get(partnerId)).size();
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        List<Order> orders = (List)this.partnerOrderMap.get(partnerId);
        List<String> list = new ArrayList();
        Iterator var4 = orders.iterator();

        while(var4.hasNext()) {
            Order order = (Order)var4.next();
            list.add(order.toString());
        }

        return list;
    }

    public List<String> getAllOrders() {
        List<String> list = new ArrayList();
        Iterator var2 = this.orderMap.keySet().iterator();

        while(var2.hasNext()) {
            String s = (String)var2.next();
            list.add(((Order)this.orderMap.get(s)).toString());
        }

        return list;
    }

    public int getCountOfUnassignedOrders() {
        return this.orderPartnerMap.size() - this.orderMap.size();
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        List<Order> list = (List)this.partnerOrderMap.get(partnerId);
        int undiliveredOrder = 0;
        int Time = Order.convertTime(time);
        Iterator var6 = list.iterator();

        while(var6.hasNext()) {
            Order order = (Order)var6.next();
            if (order.getDeliveryTime() > Time) {
                ++undiliveredOrder;
            }
        }

        return undiliveredOrder;
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        List<Order> list = (List)this.partnerOrderMap.get(partnerId);
        int lastTime = 0;
        Iterator var4 = list.iterator();

        while(var4.hasNext()) {
            Order order = (Order)var4.next();
            if (order.getDeliveryTime() > lastTime) {
                lastTime = order.getDeliveryTime();
            }
        }

        String lastDeliveryTime = "";
        lastDeliveryTime = lastDeliveryTime + String.valueOf(lastTime / 60);
        lastDeliveryTime = lastDeliveryTime + ":";
        lastDeliveryTime = lastDeliveryTime + String.valueOf(lastTime % 60);
        return lastDeliveryTime;
    }

    public void deletePartnerById(String partnerId) {
        this.partnerMap.remove(partnerId);
        Iterator var2 = ((List)this.partnerOrderMap.get(partnerId)).iterator();

        while(var2.hasNext()) {
            Order order = (Order)var2.next();
            this.orderPartnerMap.remove(order.getId());
        }

        this.partnerOrderMap.remove(partnerId);
    }

    public void deleteOrderById(String orderId) {
        DeliveryPartner deliveryPartner = (DeliveryPartner)this.orderPartnerMap.get(orderId);
        String partnerId = deliveryPartner.getId();
        Order order = (Order)this.orderMap.get(orderId);
        ((List)this.partnerOrderMap.get(partnerId)).remove(order);
        this.orderPartnerMap.remove(orderId);
        this.orderMap.remove(orderId);
    }
}

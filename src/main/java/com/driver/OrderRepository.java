package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {

    HashMap<String, Order> orderMap = new HashMap<>();
    HashMap<String, DeliveryPartner> partnerMap = new HashMap<>();
    HashMap<String, List<Order>> partnerOrderMap = new HashMap<>();
    HashMap<String, DeliveryPartner> orderPartnerMap = new HashMap<>();

    public void addOrder(Order order){
        orderMap.put(order.getId(), order);
    }

    public void addPartner(String partnerId){
        DeliveryPartner deliveryPartner = new DeliveryPartner(partnerId);
        partnerMap.put(partnerId, deliveryPartner);
        partnerOrderMap.put(partnerId, new ArrayList<>());
    }

    public void addOrderPartnerPair(String orderId, String partnerId){
        Order order = orderMap.get(orderId);
        partnerOrderMap.get(partnerId).add(order);
        DeliveryPartner deliveryPartner = partnerMap.get(partnerId);
        orderPartnerMap.put(orderId, deliveryPartner);
    }

    public Order getOrderById(String orderId){
        return orderMap.get(orderId);
    }

    public DeliveryPartner getPartnerById(String partnerId){
        return partnerMap.get(partnerId);
    }

    public int getOrderCountByPartnerId(String partnerId){
        return partnerOrderMap.get(partnerId).size();
    }

    public List<Order> getOrdersByPartnerId(String partnerId){
        return partnerOrderMap.get(partnerId);
    }

    public List<Order> getAllOrders(){
        List<Order> list = new ArrayList<>();
        for(String s : orderMap.keySet()){
            list.add(orderMap.get(s));
        }
        return list;
    }

    public int getCountOfUnassignedOrders(){
        return orderPartnerMap.size() - orderMap.size();
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId){
        List<Order> list = partnerOrderMap.get(partnerId);
        int undiliveredOrder = 0;
        int Time = Order.convertTime(time);
        for(Order order:list){
            if(order.getDeliveryTime()>Time){
                undiliveredOrder++;
            }
        }
        return undiliveredOrder;
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId){
        List<Order> list = partnerOrderMap.get(partnerId);
        int lastTime=0;
        for(Order order:list){
            if(order.getDeliveryTime()>lastTime){
                lastTime = order.getDeliveryTime();
            }
        }
        String lastDeliveryTime = "";
        lastDeliveryTime+=String.valueOf(lastTime/60);
        lastDeliveryTime+=":";
        lastDeliveryTime+=String.valueOf(lastTime%60);
        return lastDeliveryTime;
    }

    public void deletePartnerById(String partnerId){
        partnerMap.remove(partnerId);
        for(Order order:partnerOrderMap.get(partnerId)){
            orderPartnerMap.remove(order.getId());
        }
        partnerOrderMap.remove(partnerId);
    }

    public void deleteOrderById(String orderId){
        DeliveryPartner deliveryPartner = orderPartnerMap.get(orderId);
        String partnerId = deliveryPartner.getId();
        Order order = orderMap.get(orderId);
        partnerOrderMap.get(partnerId).remove(order);
        orderPartnerMap.remove(orderId);
        orderMap.remove(orderId);
    }
}

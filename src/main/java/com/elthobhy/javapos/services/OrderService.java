package com.elthobhy.javapos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.elthobhy.javapos.models.OrderDetail;
import com.elthobhy.javapos.models.OrderHeader;
import com.elthobhy.javapos.reposiotries.OrderDetailRepository;
import com.elthobhy.javapos.reposiotries.OrderHeaderRepositroy;

@Service
public class OrderService {
    @Autowired
    private OrderHeaderRepositroy orderHeaderRepositroy;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public List<OrderHeader> getAll() throws Exception {
        return orderHeaderRepositroy.findAll();
    }

    public List<OrderDetail> getAllDetail(long orderHeaderId) throws Exception {
        List<OrderDetail> data = orderDetailRepository.findByOrderHeaderId(orderHeaderId).get();
        if (data.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return data;
    }

    public OrderHeader Create(OrderHeader data) throws Exception {
        Optional<OrderHeader> exist = orderHeaderRepositroy.findById(data.getId());
        if (exist.isEmpty()) {
            // create category
            orderHeaderRepositroy.save(data);
            for (OrderDetail detail : data.getOrderDetails()) {
                detail.setOrderHeaderId(data.getId());
                detail.setCreateBy(data.getCreateBy());
                detail.setCreateDate(data.getCreateDate());
                orderDetailRepository.save(detail);
            }
            return data;
        } else {
            // cancel
            return new OrderHeader();
        }
    }
}

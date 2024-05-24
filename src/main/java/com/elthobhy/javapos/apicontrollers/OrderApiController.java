package com.elthobhy.javapos.apicontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elthobhy.javapos.models.OrderDetail;
import com.elthobhy.javapos.models.OrderHeader;
import com.elthobhy.javapos.services.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/order")
public class OrderApiController {
    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public ResponseEntity<?> get() {
        try {
            List<OrderHeader> data = orderService.getAll();
            if (data.size() > 0) {
                return new ResponseEntity<List<OrderHeader>>(data, HttpStatus.OK);
            } else
                return new ResponseEntity<List<OrderHeader>>(data, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllDetail/{orderDetailId}")
    public ResponseEntity<?> getAllDetail(@PathVariable final long orderDetailId) {
        try {
            List<OrderDetail> data = orderService.getAllDetail(orderDetailId);
            if (data.size() > 0) {
                return new ResponseEntity<List<OrderDetail>>(data, HttpStatus.OK);
            } else
                return new ResponseEntity<List<OrderDetail>>(data, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody OrderHeader data) {
        // create new order
        try {
            OrderHeader exist = orderService.create(data);
            if (exist.getId() > 0) {
                return new ResponseEntity<OrderHeader>(exist, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<String>("Order Already Exist", HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity<?> ureate(@RequestBody OrderHeader data) {
        // update order
        try {
            OrderHeader exist = orderService.update(data);
            if (exist.getId() > 0) {
                return new ResponseEntity<OrderHeader>(exist, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Order does not exist", HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

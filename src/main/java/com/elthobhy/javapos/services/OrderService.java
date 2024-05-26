package com.elthobhy.javapos.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.server.ResponseStatusException;

import com.elthobhy.javapos.models.OrderDetail;
import com.elthobhy.javapos.models.OrderHeader;
import com.elthobhy.javapos.models.Product;
import com.elthobhy.javapos.reposiotries.OrderDetailRepository;
import com.elthobhy.javapos.reposiotries.OrderHeaderRepositroy;
import com.elthobhy.javapos.reposiotries.ProductRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class OrderService {
    @Autowired
    private OrderHeaderRepositroy orderHeaderRepositroy;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductRepository productRepository;

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

    public OrderHeader create(OrderHeader data) throws Exception {
        try {
            Optional<OrderHeader> exist = orderHeaderRepositroy.findById(data.getId());
            if (exist.isEmpty()) {
                // create category
                orderHeaderRepositroy.save(data);
                for (OrderDetail detail : data.getOrderDetails()) {
                    detail.setOrderHeaderId(data.getId());
                    detail.setCreateBy(data.getCreateBy());
                    detail.setCreateDate(data.getCreateDate());
                    orderDetailRepository.save(detail);

                    Optional<Product> product = productRepository.findById(detail.getProductId());
                    if (!product.isEmpty()) {
                        // check product
                        int currentStock = product.get().getStock();
                        if (currentStock >= detail.getQty()) {
                            currentStock -= detail.getQty();
                            product.get().setStock(currentStock);

                            // save
                            productRepository.save(product.get());
                        } else {
                            throw new Exception("Create new order canceled, Product (ID = " + detail.getProductId()
                                    + ") doesn't have enough stock");
                        }
                    } else {
                        throw new Exception(
                                "Create new order canceled, " + "Product (ID = " + detail.getProductId()
                                        + ") doesn't exist");
                    }
                }
                return data;
            } else {
                // cancel
                return new OrderHeader();
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw e;
        }
    }

    public OrderHeader update(OrderHeader order) throws Exception {
        try {
            Optional<OrderHeader> exist = orderHeaderRepositroy.findById(order.getId());
            if (exist.isEmpty()) {
                return new OrderHeader();
            } else {
                // cancel
                order.setCreateBy(exist.get().getCreateBy());
                order.setCreateDate(exist.get().getCreateDate());
                order.setDeleted(exist.get().isDeleted());
                order.setUpdateDate(LocalDateTime.now());

                for (OrderDetail detailNow : order.getOrderDetails()) {
                    OrderDetail detailBefore = orderDetailRepository.findById(detailNow.getId()).get();

                    Optional<Product> product = productRepository.findById(detailNow.getProductId());
                    // check product current stockt
                    int currentStock = product.get().getStock();
                    if (product.isEmpty()) {
                        throw new Exception(
                                "Create new order canceled, " + "Product (ID = " + detailNow.getProductId()
                                        + ") doesn't exist");
                    } else {

                        // jika current order less than before
                        if (detailNow.getQty() < detailBefore.getQty()) {
                            currentStock += detailBefore.getQty() - detailNow.getQty();
                        } else {
                            if (currentStock >= (detailNow.getQty() - detailBefore.getQty())) {
                                currentStock -= detailNow.getQty() - detailBefore.getQty();
                            } else {
                                throw new Exception(
                                        "Create new order canceled, Product (ID = " + detailNow.getProductId()
                                                + ") doesn't have enough stock");
                            }
                        }
                        product.get().setStock(currentStock);
                    }
                    // update table
                    detailNow.setCreateDate(detailNow.getCreateDate());
                    detailNow.setCreateBy(detailNow.getCreateBy());
                    detailNow.setUpdateDate(LocalDateTime.now());

                    orderHeaderRepositroy.save(order);
                    productRepository.updateStock(detailNow.getProductId(), currentStock);

                    orderDetailRepository.save(detailNow);
                }
                return order;
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw e;
        }
    }

    public OrderHeader delete(long orderHeaderId, int userId) throws Exception {
        try {
            Optional<OrderHeader> existHeader = orderHeaderRepositroy.findById(orderHeaderId);
            if (existHeader.isPresent()) {
                if (existHeader.get().isCheckedOut()) {
                    throw new Exception("Order Already been Checked out");
                }
                orderHeaderRepositroy.deleteOrderHeader(orderHeaderId, userId);

                List<OrderDetail> existDetail = existHeader.get().getOrderDetails();
                if (!existDetail.isEmpty()) {
                    // delete order detail allitem
                    orderDetailRepository.deleteOrderDetail(orderHeaderId, userId, existHeader.get().getUpdateDate());

                    // update stock per product
                    for (OrderDetail detailItem : existDetail) {
                        Optional<Product> existProduct = productRepository.findById(detailItem.getProductId());
                        if (productRepository.findById(detailItem.getProductId()).isPresent()) {
                            int currentStock = existProduct.get().getStock();
                            productRepository.updateStock((existProduct.get().getId()),
                                    currentStock + detailItem.getQty());
                        } else {
                            throw new Exception(
                                    "Product dengan Id = " + existProduct.get().getId() + " Tidak ditemukan");
                        }
                    }

                }
            }
            OrderHeader result = getById(orderHeaderId);
            return result;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw e;
        }

    }

    public OrderHeader getById(Long id) throws Exception {
        return orderHeaderRepositroy.findById(id).orElse(new OrderHeader());
    }
}

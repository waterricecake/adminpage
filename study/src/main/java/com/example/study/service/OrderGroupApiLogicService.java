package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.OrderGroup;
import com.example.study.model.network.Header;
import com.example.study.model.network.reponse.OrderGroupResponse;
import com.example.study.model.network.request.OrderGroupRequest;
import com.example.study.repository.OrderGroupRepository;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderGroupApiLogicService implements CrudInterface<OrderGroupRequest, OrderGroupResponse> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Override
    public Header<OrderGroupResponse> create(Header<OrderGroupRequest> request) {

        OrderGroupRequest body = request.getData();

        OrderGroup orderGroup = OrderGroup.builder()
                .status(body.getStatus())
                .orderType(body.getOrderType())
                .revAddress(body.getRevAddress())
                .paymentType(body.getPaymentType())
                .totalPrice(body.getTotalPrice())
                .totalQuantity(body.getTotalQuantity())
                .orderAt(LocalDateTime.now())
                .user(userRepository.getReferenceById(body.getUserId()))
                .build();
        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);
        return response(newOrderGroup);
    }

    @Override
    public Header<OrderGroupResponse> read(Long id) {

        return orderGroupRepository.findById(id)
                .map(orderGroup ->
                    response(orderGroup)
                )
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<OrderGroupResponse> update(Header<OrderGroupRequest> request) {

        OrderGroupRequest body = request.getData();

        return orderGroupRepository.findById(body.getId())
                .map(orderGroup ->{
                    orderGroup
                            .setStatus(body.getStatus())
                            .setOrderType(body.getOrderType())
                            .setRevAddress(body.getRevAddress())
                            .setRevName(body.getRevName())
                            .setPaymentType(body.getPaymentType())
                            .setTotalQuantity(body.getTotalQuantity())
                            .setOrderAt(body.getOrderAt())
                            .setArrivalDate(body.getArrivalDate())
                            .setUser(userRepository.getReferenceById(body.getUserId()));
                    return orderGroup;
                })
                .map(newOrderGroup -> {
                    orderGroupRepository.save(newOrderGroup);
                    return newOrderGroup;
                })
                .map(orderGroup->response(orderGroup))
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return orderGroupRepository.findById(id)
                .map(orderGroup -> {
                    orderGroupRepository.delete(orderGroup);
                    return Header.OK();
                })
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    private Header<OrderGroupResponse> response(OrderGroup orderGroup){
        OrderGroupResponse body = OrderGroupResponse.builder()
                .id(orderGroup.getId())
                .status(orderGroup.getStatus())
                .orderType(orderGroup.getOrderType())
                .revAddress(orderGroup.getRevAddress())
                .revName(orderGroup.getRevName())
                .paymentType(orderGroup.getPaymentType())
                .totalPrice(orderGroup.getTotalPrice())
                .totalQuantity(orderGroup.getTotalQuantity())
                .orderAt(orderGroup.getOrderAt())
                .arrivalDate(orderGroup.getArrivalDate())
                .userId(orderGroup.getUser().getId())
                .build();
        return Header.OK(body);

    }
}

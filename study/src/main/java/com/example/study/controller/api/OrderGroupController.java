package com.example.study.controller.api;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.reponse.OrderGroupResponse;
import com.example.study.model.network.request.OrderGroupRequest;
import com.example.study.service.OrderGroupApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/orderGroup")
public class OrderGroupController implements CrudInterface<OrderGroupRequest, OrderGroupResponse> {

    @Autowired
    private OrderGroupApiLogicService orderGroupApiLogicService;

    @Override
    @PostMapping("")
    public Header<OrderGroupResponse> create(@RequestBody Header<OrderGroupRequest> request) {
        return orderGroupApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<OrderGroupResponse> read(@PathVariable Long id) {
        return orderGroupApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<OrderGroupResponse> update(@RequestBody Header<OrderGroupRequest> request) {

        return orderGroupApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {

        return orderGroupApiLogicService.delete(id);
    }
}

package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Interfaces.RevenueService;
import com.test.COCONSULT.ServiceIMP.RevenueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/revenue")
public class RevenueController {
/*
    private final RevenueService revenueService;

    @Autowired
    public RevenueController(RevenueService revenueService) {
        this.revenueService = revenueService;
    }

    @GetMapping("/totalByYear")
    public Map<Integer, Double> getTotalRevenueByYear() {
        return revenueService.getTotalRevenueByYear();
    }
    @GetMapping("/totalByMonth")
    public Map<String, Double> getTotalRevenueByMonth() {
        return revenueService.getTotalRevenueByMonth();
    }*/
}


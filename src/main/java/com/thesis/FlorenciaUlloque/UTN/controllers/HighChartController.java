package com.thesis.FlorenciaUlloque.UTN.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("/graficos")
public class HighChartController{

    @GetMapping
    public String barChart(Model model){
        Map<String, Integer> data = new LinkedHashMap<String, Integer>();
        data.put("Marta", 30);
        data.put("Carla", 50);
        data.put("Flor", 70);
        data.put("Lela", 90);
        data.put("Agus", 25);
        model.addAttribute("keySet", data.keySet());
        model.addAttribute("values", data.values());
        return "barChart";
    }

    @GetMapping("/pieChart")
    public String pieChart(Model model) {
        model.addAttribute("pass", 90);
        model.addAttribute("fail", 10);
        return "pieChart";

    }
}

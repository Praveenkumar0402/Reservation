package com.controller;

import com.dto.BusDto;
import com.service.BusService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/bus")
public class BusController {

    @Autowired
    BusService busservice;

    @GetMapping("/all")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public List<BusDto> getAll() {
        return busservice.findAll();
    }


    @PutMapping("/update")
    public BusDto updates(@RequestParam("id") int id, @RequestBody BusDto busdto) {
        return busservice.updateBus(id, busdto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<BusDto> remove(@RequestParam("id") int id) {
        BusDto busDto = busservice.deleteBus(id);
        return ResponseEntity.status(HttpStatus.OK).body(busDto);
    }

    @PostMapping("/create")
    public ResponseEntity<BusDto> create(@RequestBody BusDto busDto) {
        BusDto busDto1 = busservice.createBus(busDto);
        return ResponseEntity.status(HttpStatus.OK).body(busDto1);
    }


    @GetMapping("/generate")
    public void generateExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        String headerKey = "Content-Deposition";
        String headerValue = "attachment;filename=bus.xlsx";
        response.setHeader(headerKey, headerValue);
        busservice.generateExcel(response);
    }


//    @EventListener(ApplicationReadyEvent.class)
//    public void triggerMail() throws MessagingException {
//        busservice.sendEmailAttachment("",
//                "This is email subject ",
//                "This is email body","/home/downloads/generate.xls");
//    }
}

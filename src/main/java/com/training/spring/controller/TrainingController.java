package com.training.spring.controller;

import com.training.spring.entity.Product;
import com.training.spring.request.PrintReqList;
import com.training.spring.request.PrintRequest;
import com.training.spring.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:8080")
public class TrainingController {

    private final Map<String, Product> productMap = new HashMap<>();

    @GetMapping("getProducts")
    public ResponseEntity<Map<String, Product>> getProducts() {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(productMap);
    }

    @PostMapping("addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product req) {
        Product newProduct = null;
        try {
            newProduct = new Product(req.getProductCode(), req.getProductName(), req.getUnitPrice());

            productMap.put(req.getProductCode(), newProduct);

            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(newProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(newProduct);
        }
    }

    @PostMapping("deleteProduct")
    public ResponseEntity<Map<String, Product>> deleteProduct(@RequestBody Product req) {
        productMap.remove(req.getProductCode());

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(productMap);
    }

    @PostMapping("printer/printerEncode")
    public ResponseEntity<Response> printEncode (@RequestBody PrintReqList reqList) {
        int countResp = reqList.getItems().size();
        Response response = new Response();
        response.setSuccess(true);
        response.setMessage("Successfully printed " + countResp + " items");
        response.setData(reqList.getItems());

        int i = 0;
        for (PrintRequest req : reqList.getItems()) {
            i++;
            System.out.println("Print No " + i + " di lantai 1");
            System.out.println(req.getPrintDeskripsi());
            System.out.println(req.getPrintJudul());
            System.out.println(req.getRfidData());
        }

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    private static final List<String> epcList = List.of(
            "E2 80 69 15 00 00 40 12 32 C9 44 BC",
            "E2 80 69 15 00 00 40 12 32 C9 44 BD",
            "E2 80 69 15 00 00 40 12 32 C9 44 BE"
    );

    public void tes() {
        String raw = "128";

        int rawInt = Integer.parseInt(raw);
        Integer jam = 0;

        for (int i = 1; rawInt >= 60 ; i++) {
            rawInt -= 60;
            jam = i;
        }

        Integer menit = rawInt;

        String jamString = String.format("%02d:%02d", jam, menit);

    }
}

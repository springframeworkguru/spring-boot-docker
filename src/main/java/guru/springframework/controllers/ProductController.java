package guru.springframework.controllers;

import guru.springframework.model.events.PageViewEvent;
import guru.springframework.pageview.PageViewService;
import guru.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.UUID;

/**
 * Created by jt on 1/20/16.
 */
@Controller
public class ProductController {

    private ProductService productService;
    private PageViewService pageViewService;

    @Autowired
    public ProductController(ProductService productService, PageViewService pageViewService) {
        this.productService = productService;
        this.pageViewService = pageViewService;
    }

    @RequestMapping("/product/{id}")
    public String getProductById(@PathVariable Integer id, Model model){

        model.addAttribute("product", productService.getProduct(id));

        //Send Page view event
        PageViewEvent pageViewEvent = new PageViewEvent();
        pageViewEvent.setPageUrl("springframework.guru/product/" + id);
        pageViewEvent.setPageViewDate(new Date());
        pageViewEvent.setCorrelationId(UUID.randomUUID().toString());

        pageViewService.sendPageViewEvent(pageViewEvent);

        return "product";
    }
}

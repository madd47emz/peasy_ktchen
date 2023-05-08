package com.example.msrecipemanage.proxy;

import com.example.msrecipemanage.model.Review;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="ms-personalisation")
@LoadBalancerClient(name="ms-personalisation")
public interface ReviewProx {
    @GetMapping("/reviews/search/findReviewsByIdRecipe")
    public CollectionModel<Review> getReviews(@RequestParam("idr") Long idr);
}

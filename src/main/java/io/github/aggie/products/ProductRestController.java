package io.github.aggie.products;

import io.github.aggie.common.web.PagedResultTransferObject;
import io.github.aggie.common.web.UriBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RequestMapping("${apiPrefix}/products")
@RestController
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;
    private final ProductMapper productMapper;
    private final UriBuilder uriBuilder = new UriBuilder();

    @PostMapping
    public ResponseEntity<ProductTransferObject> addProduct(@Valid @RequestBody ProductTransferObject productTransferObject, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        var product = productMapper.toProduct(productTransferObject);
        var productId = productService.add(product).getId();
        var locationUri = uriBuilder.requestUriWithId(productId);
        return ResponseEntity.created(locationUri).build();
    }

    @GetMapping
    public PagedResultTransferObject<ProductTransferObject> getProducts(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize) {
        var products = productService.getAll(pageNumber, pageSize);
        return productMapper.toProductTransferObjectsPage(products);
    }

    // Check MvcConfiguration and WebInitializer config
    @RequestMapping(value = "{id}/files", method = RequestMethod.POST)
    public String submit(@PathVariable Long id, @RequestParam MultipartFile file) {
        // Save file to some kind of storage
        return "File " + file.getOriginalFilename() + " uploaded";
    }

}

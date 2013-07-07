package ws.server.sample1;

import java.util.List;

/**
 *
 * @author jupi
 */
public interface ProductService {
    List<ProductListDto> getProductList(String descLike);
    ProductDetailsDto getProductDetails(long id);
}

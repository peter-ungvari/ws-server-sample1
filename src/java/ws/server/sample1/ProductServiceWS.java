/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.server.sample1;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author jupi
 */
@WebService(serviceName = "ProductServiceWS")
public class ProductServiceWS implements ProductService {

    private ProductService productService;
    private ConnectionService connectionService;

    public ProductServiceWS() {
        connectionService = new ConnectionServiceImpl();
        productService = new ProductServiceJdbcImpl(connectionService);
    }
    
    
    @WebMethod(operationName = "getProductList")
    @Override
    public List<ProductListDto> getProductList(@WebParam(name = "partOfName") String partOfName) {
        return productService.getProductList(partOfName);
    }

    @WebMethod(operationName = "getProductDetails")
    @Override
    public ProductDetailsDto getProductDetails(@WebParam(name = "id") long id) {
        return productService.getProductDetails(id);
    }
}

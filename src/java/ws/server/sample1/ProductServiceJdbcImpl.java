package ws.server.sample1;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author ungvarip
 */
public class ProductServiceJdbcImpl implements ProductService {

    private static final Logger LOGGER = Logger.getLogger(ProductServiceJdbcImpl.class.getName());
    
    private ConnectionService connectionService;

    public ProductServiceJdbcImpl(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @Override
    public List<ProductListDto> getProductList(String descLike) {
        List<ProductListDto> result = null;
        if (descLike == null) {
            throw new IllegalArgumentException("descLike argument cannot be null.");
        }
        Connection conn = connectionService.getConnection("jdbc/sample");
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT product_id, description FROM APP.PRODUCT where description like ?");
            stmt.setString(1, MessageFormat.format("%{0}%", descLike));
            ResultSet rs = stmt.executeQuery();
            result = new ArrayList<ProductListDto>();
            while (rs.next()) {
                ProductListDto product = new ProductListDto();
                product.setId(rs.getInt("product_id"));
                product.setDesc(rs.getString("description"));
                result.add(product);
            }
        } catch (SQLException e) {
            LOGGER.warning("DB access error.");
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.warning("DB connection cannot be closed.");
            }
        }
        return result;
    }

    @Override
    public ProductDetailsDto getProductDetails(long id) {
        ProductDetailsDto result = null;
        Connection conn = connectionService.getConnection("jdbc/sample");
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT product_id, description, available, quantity_on_hand, purchase_cost FROM APP.PRODUCT where product_id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            result = new ProductDetailsDto();
            if (rs.next()) {
                result.setId(rs.getInt("product_id"));
                result.setDesc(rs.getString("description"));
                result.setAmount(rs.getBigDecimal("purchase_cost"));
                result.setCount(rs.getInt("quantity_on_hand"));
                result.setAvailable(rs.getBoolean("available"));
            }
        } catch (SQLException e) {
            LOGGER.warning("DB access error.");
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.warning("DB connection cannot be closed.");
            }
        }
        return result;
    }
}

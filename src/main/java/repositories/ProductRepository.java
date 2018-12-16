package repositories;

import models.Product;
import dto.ProductCart;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductRepository {
    private static final String SQL_INSERT = "INSERT INTO products_table (name, price, description, photo_url) values (?,?,?,?)";
    private static final String SQL_INSERT_TO_CART = "INSERT INTO carts_table (user_id, product_id) VALUES (?,?)";

    private static final String SQL_SELECT = "SELECT * from products_table where id = ?";
    private static final String SQL_SELECT_ALL = "SELECT * from products_table";
    private static final String SQL_SELECT_CART = "SELECT * FROM products_table join carts_table ON products_table.id = carts_table.product_id WHERE user_id = ?";
    private static final String SQL_DELETE_FROM_CART = "DELETE FROM carts_table where item_id = ?";


    JdbcTemplate jdbcTemplate;

    public ProductRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    RowMapper<Product> rowMapper = new RowMapper<Product>() {
        @Override
        public Product mapRow(ResultSet resultSet, int i) throws SQLException {
            return Product.builder()
                    .name(resultSet.getString("name"))
                    .description(resultSet.getString("description"))
                    .photoUrl(resultSet.getString("photo_url"))
                    .price(resultSet.getInt("price"))
                    .id(resultSet.getLong("id"))
                    .build();
        }
    };

    RowMapper<ProductCart> rowMapperCart = new RowMapper<ProductCart>() {
        @Override
        public ProductCart mapRow(ResultSet resultSet, int i) throws SQLException {
            return ProductCart.builder()
                    .name(resultSet.getString("name"))
                    .description(resultSet.getString("description"))
                    .photoUrl(resultSet.getString("photo_url"))
                    .price(resultSet.getInt("price"))
                    .id(resultSet.getLong("id"))
                    .itemId(resultSet.getLong("item_id"))
                    .build();
        }
    };

    public void save(Product product){
        jdbcTemplate.update(SQL_INSERT, product.getName(), product.getPrice(), product.getDescription(), product.getPhotoUrl());
    }
    public Product read(Long id){
        return jdbcTemplate.queryForObject(SQL_SELECT, rowMapper, id);
    }
    public List<Product> readAll(){
        return jdbcTemplate.query(SQL_SELECT_ALL, rowMapper);
    }
    public List<ProductCart> readCart(Long id){
        return jdbcTemplate.query(SQL_SELECT_CART, rowMapperCart, id);
    }
    public void deleteFromCart(Long id){
        jdbcTemplate.update(SQL_DELETE_FROM_CART, id);
    }
    public void addToCart(Long userId, Long productId){
        jdbcTemplate.update(SQL_INSERT_TO_CART, userId, productId);
    }

}
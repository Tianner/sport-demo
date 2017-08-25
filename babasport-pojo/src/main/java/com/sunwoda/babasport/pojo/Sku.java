package com.sunwoda.babasport.pojo;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Table(name = "bbs_sku")
@Entity
public class Sku implements Serializable {
    /**
     * 注释: ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;


    @Transient
    private Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * 注释: 商品ID
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 注释: 颜色ID
     */
    @Column(name = "color_id")
    private Long colorId;

    /**
     * 注释: 尺码
     */
    @Column(name = "size")
    private String size;

    /**
     * 注释: 市场价
     */
    @Column(name = "market_price")
    private Float marketPrice;

    /**
     * 注释: 售价
     */
    @Column(name = "price")
    private Float price;

    /**
     * 注释: 运费 默认10元
     */
    @Column(name = "delive_fee")
    private Float deliveFee;

    /**
     * 注释: 库存
     */
    @Column(name = "stock")
    private Integer stock;

    /**
     * 注释: 购买限制
     */
    @Column(name = "upper_limit")
    private Integer upperLimit;

    @Column(name = "create_time")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getColorId() {
        return colorId;
    }

    public void setColorId(Long colorId) {
        this.colorId = colorId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    public Float getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Float marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getDeliveFee() {
        return deliveFee;
    }

    public void setDeliveFee(Float deliveFee) {
        this.deliveFee = deliveFee;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Integer upperLimit) {
        this.upperLimit = upperLimit;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", productId=").append(productId);
        sb.append(", colorId=").append(colorId);
        sb.append(", size=").append(size);
        sb.append(", marketPrice=").append(marketPrice);
        sb.append(", price=").append(price);
        sb.append(", deliveFee=").append(deliveFee);
        sb.append(", stock=").append(stock);
        sb.append(", upperLimit=").append(upperLimit);
        sb.append(", createTime=").append(createTime);
        sb.append(", color=").append(color);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
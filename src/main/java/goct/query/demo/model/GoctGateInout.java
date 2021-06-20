package goct.query.demo.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @auther wmm
 * @date 2021/6/19 3:21
 */
@Data
public class GoctGateInout {
    private String ymd;
    private Integer headNum;
    private BigDecimal gfF;
    private BigDecimal gfE;
    private BigDecimal goF;
    private BigDecimal goE;
    private BigDecimal teu;
}

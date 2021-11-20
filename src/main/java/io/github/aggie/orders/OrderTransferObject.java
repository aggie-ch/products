package io.github.aggie.orders;

import io.github.aggie.common.web.IdTransferObject;
import lombok.Data;

import java.util.List;

@Data
public class OrderTransferObject {

    private List<IdTransferObject> products;

}

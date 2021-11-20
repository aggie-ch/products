package io.github.aggie.common.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PagedResultTransferObject<T> {

    private List<T> data;
    private int pageNumber;
    private int totalPages;
}
